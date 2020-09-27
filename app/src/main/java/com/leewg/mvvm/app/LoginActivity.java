package com.leewg.mvvm.app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.leewg.mvvm.app.databinding.ActivityLoginBinding;
import com.leewg.mvvm.base.BaseActivity;


/**
 * @author liwugang
 * @title
 * @date 2020-09-22
 * @email 13480020053@163.com
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    public <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(this, AppNetworkViewModelFactory.getInstance(getApplication())).get(cls);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.model;
    }
}
