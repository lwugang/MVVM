package com.leewg.mvvm.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.leewg.mvvm.app.http.AppApiRepository;
import com.leewg.mvvm.data.NetworkBaseViewModel;

/**
 * @author liwugang
 * @title
 * @date 2020-09-27
 * @email 13480020053@163.com
 */
public class AppNetworkBaseViewModel extends NetworkBaseViewModel<AppApiRepository> {

    public AppNetworkBaseViewModel(@NonNull Application application, AppApiRepository model) {
        super(application, model);
    }


}
