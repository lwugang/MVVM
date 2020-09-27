package com.leewg.mvvm.data.utils;

import android.content.Context;
import android.text.TextUtils;

import com.leewg.mvvm.data.cookie.CookieJarImpl;
import com.leewg.mvvm.data.cookie.store.PersistentCookieStore;
import com.leewg.mvvm.data.http.interceptor.BaseInterceptor;
import com.leewg.mvvm.data.http.interceptor.CacheInterceptor;
import com.leewg.mvvm.data.http.interceptor.logging.Level;
import com.leewg.mvvm.data.http.interceptor.logging.LoggingInterceptor;
import com.leewg.mvvm.tools.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leewg on 2017/5/10.
 * RetrofitClient封装单例类, 实现网络请求
 */
public class RetrofitClient {
    //超时时间
    private static final int DEFAULT_TIMEOUT = 10;
    //缓存时间
    private static final int CACHE_TIMEOUT = 10 * 1024 * 1024;

    private static Context mContext = Utils.getContext();

    private File httpCacheDirectory;

    private Retrofit retrofit;

    private final static Map<String, Object> retrofitCache = new HashMap<>(4);

    /**
     * 创建Api接口实例
     *
     * @param url      请求地址
     * @param useCache 是否需要缓存
     * @param service  接口服务
     * @param <T>
     * @return
     */
    public static <T> T create(final String url, boolean useCache, final Class<T> service) {
        Object obj = retrofitCache.get(url);
        if (obj == null) {
            RetrofitClient retrofitClient = new RetrofitClient(url, useCache, null);
            obj = retrofitClient.retrofit.create(service);
            retrofitCache.put(url, obj);
        }
        return (T) obj;
    }

    /**
     * 创建Api接口实例
     *
     * @param url
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T create(final String url, final Class<T> service) {
        return create(url, false, service);
    }

    private RetrofitClient(String url, boolean useCache, Map<String, String> headers) {
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(mContext.getCacheDir(), "http_cache");
        }
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (useCache) {
            builder.cache(new Cache(httpCacheDirectory, CACHE_TIMEOUT))
                    .addInterceptor(new CacheInterceptor(mContext));
        }
        OkHttpClient okHttpClient = builder
                .cookieJar(new CookieJarImpl(new PersistentCookieStore(mContext)))
                .addInterceptor(new BaseInterceptor(headers))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new LoggingInterceptor
                                .Builder()//构建者模式
//                        .loggable(BuildConfig.DEBUG) //是否开启日志打印
                                .setLevel(Level.BASIC) //打印的等级
                                .log(Platform.INFO) // 打印类型
                                .request("Request") // request的Tag
                                .response("Response")// Response的Tag
                                .addHeader("log-header", "I am the log request header.") // 添加打印头, 注意 key 和 value 都不能是中文
                                .build()
                )
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    /**
     * /**
     * execute your customer API
     * For example:
     * MyApiService service =
     * RetrofitClient.getInstance(MainActivity.this).create(MyApiService.class);
     * <p>
     * RetrofitClient.getInstance(MainActivity.this)
     * .execute(service.lgon("name", "password"), subscriber)
     * * @param subscriber
     */
    public static <T> T execute(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return null;
    }
}
