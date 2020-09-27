package com.leewg.mvvm.app.http;

import com.leewg.mvvm.app.LoginBean;
import com.leewg.mvvm.data.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author liwugang
 * @title
 * @date 2020-09-27
 * @email 13480020053@163.com
 */
public interface AppApiService {
    @POST("out/login")
    Observable<BaseResponse<LoginBean>> login(@Body Map<String, String> data);
}
