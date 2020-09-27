package com.leewg.mvvm.app;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.leewg.mvvm.app.http.AppApiRepository;
import com.leewg.mvvm.command.BindingAction;
import com.leewg.mvvm.command.BindingCommand;

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
        @Override
        public void call() {
            Map<String, String> map = new HashMap<>();
            map.put("account", userName.get().trim());
            map.put("macAddr", MD5Utils.MD5Encode("123132121"));
            map.put("password", MD5Utils.MD5Encode(password.get().trim(), "utf8"));
            subscribeWithLoading(getModel().login(map), data -> {
                Log.e("-----------", "call: " + data);
            });
        }
    });


}
