package com.leewg.mvvm.app.http;

import android.support.annotation.NonNull;

import com.leewg.mvvm.app.LoginBean;
import com.leewg.mvvm.app.http.source.IHttpDataSource;
import com.leewg.mvvm.app.http.source.ILocalDataSource;
import com.leewg.mvvm.base.BaseModel;
import com.leewg.mvvm.data.BaseResponse;
import com.leewg.mvvm.data.http.source.ApiRepository;
import com.leewg.mvvm.protocol.IApiRepository;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author liwugang
 * @title
 * @date 2020-09-27
 * @email 13480020053@163.com
 */
public class AppApiRepository extends BaseModel implements IApiRepository {
    private static AppApiRepository INSTANCE;
    private final IHttpDataSource httpDataSource;
    private final ILocalDataSource localDataSource;

    protected AppApiRepository(@NonNull IHttpDataSource httpDataSource, @NonNull ILocalDataSource localDataSource) {
        this.httpDataSource = httpDataSource;
        this.localDataSource = localDataSource;
    }

    public static AppApiRepository getInstance(IHttpDataSource httpDataSource, ILocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (ApiRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppApiRepository(httpDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 用户登录
     *
     * @param data
     * @return
     */
    public Observable<BaseResponse<LoginBean>> login(Map<String, String> data) {
        return httpDataSource.login(data);
    }

}
