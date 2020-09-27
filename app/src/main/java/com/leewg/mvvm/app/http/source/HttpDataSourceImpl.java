package com.leewg.mvvm.app.http.source;

import com.leewg.mvvm.app.http.AppApiService;
import com.leewg.mvvm.app.LoginBean;
import com.leewg.mvvm.data.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by leewg on 2019/3/26.
 */
public class HttpDataSourceImpl implements IHttpDataSource {
    private AppApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(AppApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    private HttpDataSourceImpl(AppApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseResponse<LoginBean>> login(Map<String, String> data) {
        return apiService.login(data);
    }
}
