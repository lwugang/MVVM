package com.leewg.mvvm.app;

import android.Manifest;
import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.leewg.mvvm.app.http.AppApiRepository;
import com.leewg.mvvm.aspectj.Permission;
import com.leewg.mvvm.command.BindingAction;
import com.leewg.mvvm.command.BindingCommand;
import com.leewg.mvvm.tools.MD5Utils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liwugang
 * @title
 * @date 2020-09-22
 * @email 13480020053@163.com
 */
public class LoginViewModel extends AppNetworkBaseViewModel {
    public LoginViewModel(@NonNull Application application, AppApiRepository model) {
        super(application, model);
    }

    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    /**
     * 登录
     */
    public BindingCommand loginCommand = new BindingCommand(new BindingAction() {

        @Permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        @Override
        public void call() {
            Map<String, String> map = new HashMap<>();
            subscribeWithLoading(getModel().login(map), data -> {
                Log.e("-----------", "call: " + data);
            });
        }
    });


}
