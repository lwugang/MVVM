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
import com.leewg.mvvm.data.utils.RxUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by leewg on 2017/6/15.
 */
public class NetworkBaseViewModel<T extends BaseModel> extends BaseViewModel<T> {

    public NetworkBaseViewModel(@NonNull Application application, T repository) {
        super(application, repository);
    }

    /**
     * 订阅事件
     *
     * @param observable
     * @param onNext
     * @param onError
     * @param <T>
     */
    protected <T> void subscribe(final boolean showLoading, final boolean isCheckNetwork, final Observable<BaseResponse<T>> observable, final Consumer<T> onNext, final Consumer<Throwable> onError) {
        observable.compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .subscribe(new ApiDisposableObserver<T>() {
                    @Override
                    public void onStart(boolean success) {
                        addSubscribe(this);
                        if (showLoading && success) {
                            showDialog();
                        }
                    }

                    @Override
                    protected boolean isCheckNetwork() {
                        return isCheckNetwork;
                    }

                    @Override
                    public void onResult(T t) {
                        removeSubscribe(this);
                        if (isDestroyed()) {
                            return;
                        }
                        dismissDialog();
                        if (onNext != null) {
                            try {
                                onNext.accept(t);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        removeSubscribe(this);
                        if (isDestroyed()) {
                            return;
                        }
                        dismissDialog();
                        super.onError(e);
                        if (onError != null) {
                            try {
                                onError.accept(e);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }

    /**
     * 订阅事件
     *
     * @param observable
     * @param onNext
     * @param <T>
     */
    protected <T> void subscribe(Observable<BaseResponse<T>> observable, Consumer<T> onNext) {
        subscribe(false, true, observable, onNext, null);
    }

    /**
     * 订阅事件,不会检查网络情况
     *
     * @param observable
     * @param onNext
     * @param <T>
     */
    protected <T> void subscribeOffline(Observable<BaseResponse<T>> observable, Consumer<T> onNext) {
        subscribe(false, false, observable, onNext, null);
    }

    /**
     * 订阅事件
     *
     * @param observable
     * @param onNext
     * @param <T>
     */
    protected <T> void subscribeWithLoading(Observable<BaseResponse<T>> observable, Consumer<T> onNext) {
        subscribe(true, true, observable, onNext, null);
    }

    /**
     * 订阅事件,不会检查网络情况
     *
     * @param observable
     * @param onNext
     * @param <T>
     */
    protected <T> void subscribeOfflineWithLoading(Observable<BaseResponse<T>> observable, Consumer<T> onNext) {
        subscribe(true, false, observable, onNext, null);
    }

    /**
     * 订阅事件
     *
     * @param observable
     * @param onNext
     * @param <T>
     */
    protected <T> void subscribeWithLoading(Observable<BaseResponse<T>> observable, Consumer<T> onNext, Consumer<Throwable> onError) {
        subscribe(true, true, observable, onNext, onError);
    }

    /**
     * 订阅事件
     *
     * @param observable
     * @param onNext
     * @param <T>
     */
    protected <T> void subscribeOfflineWithLoading(Observable<BaseResponse<T>> observable, Consumer<T> onNext, Consumer<Throwable> onError) {
        subscribe(true, false, observable, onNext, onError);
    }
}
