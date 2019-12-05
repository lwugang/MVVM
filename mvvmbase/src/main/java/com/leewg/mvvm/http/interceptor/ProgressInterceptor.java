package com.leewg.mvvm.http.interceptor;

import java.io.IOException;

import com.leewg.mvvm.http.download.ProgressResponseBody;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by goldze on 2017/5/10.
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
