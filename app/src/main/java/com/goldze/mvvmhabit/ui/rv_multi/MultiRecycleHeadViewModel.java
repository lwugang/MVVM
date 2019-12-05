package com.goldze.mvvmhabit.ui.rv_multi;

import android.support.annotation.NonNull;

import com.leewg.mvvm.base.BaseViewModel;
import com.leewg.mvvm.base.MultiItemViewModel;
import com.leewg.mvvm.binding.command.BindingAction;
import com.leewg.mvvm.binding.command.BindingCommand;
import com.leewg.mvvm.utils.ToastUtils;

/**
 * Create Author：goldze
 * Create Date：2019/01/25
 * Description：
 */

public class MultiRecycleHeadViewModel extends MultiItemViewModel {

    public MultiRecycleHeadViewModel(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("我是头布局");
        }
    });
}
