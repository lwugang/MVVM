package com.leewg.mvvm.data.http.source;

import android.support.annotation.NonNull;

import com.leewg.mvvm.base.BaseModel;

/**
 * MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repository）
 */
public class ApiRepository extends BaseModel implements IDataSource {
    private volatile static ApiRepository INSTANCE = null;
    private final IDataSource mHttpDataSource;

    private final IDataSource mLocalDataSource;

    protected ApiRepository(@NonNull IDataSource httpDataSource,
                            @NonNull IDataSource localDataSource) {
        this.mHttpDataSource = httpDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static ApiRepository getInstance(IDataSource httpDataSource,
                                            IDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (ApiRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApiRepository(httpDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }


    public IDataSource getHttpDataSource() {
        return mHttpDataSource;
    }

    public IDataSource getLocalDataSource() {
        return mLocalDataSource;
    }
}
