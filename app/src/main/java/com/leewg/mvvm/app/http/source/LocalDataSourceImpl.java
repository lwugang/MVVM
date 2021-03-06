package com.leewg.mvvm.app.http.source;

/**
 * 本地数据源，可配合Room框架使用
 * Created by leewg on 2019/3/26.
 */
public class LocalDataSourceImpl implements ILocalDataSource {
    private volatile static LocalDataSourceImpl INSTANCE = null;

    public static LocalDataSourceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LocalDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSourceImpl();
                }
            }
        }
        return INSTANCE;
    }


    private LocalDataSourceImpl() {
        //数据库Helper构建
    }
}
