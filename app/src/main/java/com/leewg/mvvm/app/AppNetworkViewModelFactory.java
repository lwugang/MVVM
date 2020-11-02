package com.leewg.mvvm.app;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.leewg.mvvm.app.http.AppApiRepository;
import com.leewg.mvvm.app.http.AppApiService;
import com.leewg.mvvm.app.http.source.HttpDataSourceImpl;
import com.leewg.mvvm.app.http.source.IHttpDataSource;
import com.leewg.mvvm.app.http.source.ILocalDataSource;
import com.leewg.mvvm.app.http.source.LocalDataSourceImpl;
import com.leewg.mvvm.provider.AppService;

import java.lang.reflect.Constructor;

/**
 * Created by leewg on 2018/9/30.
 */
public class AppNetworkViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    public static final String URL = "https://uat-bc.jtexpress.com.cn/bc/"; //网络请求地址

    private static volatile AppNetworkViewModelFactory INSTANCE;

    private final Application mApplication;

    private final AppApiRepository mRepository;

    public static AppNetworkViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (AppNetworkViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppNetworkViewModelFactory(application, provideRepository());
                }
            }
        }
        return INSTANCE;
    }

    public AppApiRepository getRepository() {
        return mRepository;
    }

    static AppApiRepository provideRepository() {
        //网络API服务
        AppApiService apiService = AppService.getService().getNetworkProtocol().createRetrofitApi(URL, AppApiService.class);
        //网络数据源
        IHttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);
        //本地数据源
        ILocalDataSource localDataSource = LocalDataSourceImpl.getInstance();
        //两条分支组成一个数据仓库
        return AppApiRepository.getInstance(httpDataSource, localDataSource);
    }

    private AppNetworkViewModelFactory(Application application, AppApiRepository repository) {
        mApplication = application;
        mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> cls) {
        try {
            Constructor constructor = cls.getConstructor(Application.class, AppApiRepository.class);
            return (T) constructor.newInstance(mApplication, mRepository);
        } catch (NoSuchMethodException e) {
            try {
                return (T) cls.getConstructor(Application.class).newInstance(mApplication);
            } catch (Exception e1) {
                throw new IllegalArgumentException(e1);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
