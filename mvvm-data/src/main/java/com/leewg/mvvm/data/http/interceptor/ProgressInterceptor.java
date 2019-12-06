package com.leewg.mvvm.data.http.interceptor;

import java.io.IOException;

import com.leewg.mvvm.data.download.ProgressResponseBody;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by leewg on 2017/5/10.
 */

public class ProgressInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(new ProgressResponseBody(originalResponse.body()))
                .build();
    }
}
