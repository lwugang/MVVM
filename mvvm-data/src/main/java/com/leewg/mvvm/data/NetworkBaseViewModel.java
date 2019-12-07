package com.leewg.mvvm.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.leewg.mvvm.base.BaseModel;
import com.leewg.mvvm.base.BaseViewModel;
import com.leewg.mvvm.base.IBaseViewModel;
import com.leewg.mvvm.bus.event.SingleLiveEvent;
import com.leewg.mvvm.data.http.source.ApiRepository;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by leewg on 2017/6/15.
 */
public class NetworkBaseViewModel extends BaseViewModel<ApiRepository> {

    public NetworkBaseViewModel(@NonNull Application application, ApiRepository repository) {
        super(application, repository);
    }

    /**
     * 获取数据仓库
     *
     * @return
     */
    public ApiRepository getRepository() {
        return super.getModel();
    }
}
