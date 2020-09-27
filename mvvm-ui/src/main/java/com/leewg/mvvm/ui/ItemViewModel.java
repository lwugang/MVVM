package com.leewg.mvvm.ui;

import android.support.annotation.NonNull;

import com.leewg.mvvm.base.BaseViewModel;

/**
 * ItemViewModel
 * Created by leewg on 2018/10/3.
 */

public class ItemViewModel<VM extends BaseViewModel> {
    protected VM viewModel;

    public ItemViewModel(@NonNull VM viewModel) {
        this.viewModel = viewModel;
    }
}
