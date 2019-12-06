package com.leewg.mvvm.ui.binding.viewadapter.viewgroup;

/**
 * Created by leewg on 2017/6/15.
 */

import android.databinding.ViewDataBinding;

public interface IBindingItemViewModel<V extends ViewDataBinding> {
    void injecDataBinding(V binding);
}
