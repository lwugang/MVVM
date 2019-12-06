package com.leewg.mvvm.base;

import android.support.annotation.NonNull;

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
