package com.leewg.mvvm.data.http.source;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.leewg.mvvm.base.BaseModel;
import com.leewg.mvvm.data.http.source.local.LocalDataSource;

/**
 * MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repository）
 */
public class ApiRepository extends BaseModel implements HttpDataSource, LocalDataSource {
    private volatile static ApiRepository INSTANCE = null;
    private final HttpDataSource mHttpDataSource;

    private final LocalDataSource mLocalDataSource;

    private ApiRepository(@NonNull HttpDataSource httpDataSource,
                          @NonNull LocalDataSource localDataSource) {
        this.mHttpDataSource = httpDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static ApiRepository getInstance(HttpDataSource httpDataSource,
                                            LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (ApiRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApiRepository(httpDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

}
