package com.goldze.mvvmhabit.ui.rv_multi;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.leewg.mvvm.base.MultiItemViewModel;

import com.leewg.mvvm.binding.command.BindingAction;
import com.leewg.mvvm.binding.command.BindingCommand;
import com.leewg.mvvm.utils.ToastUtils;

/**
 * Create Author：goldze
 * Create Date：2019/01/25
 * Description：
 */

public class MultiRecycleLeftItemViewModel extends MultiItemViewModel<MultiRecycleViewModel> {
    public ObservableField<String> text = new ObservableField<>("");

    public MultiRecycleLeftItemViewModel(@NonNull MultiRecycleViewModel viewModel, String text) {
        super(viewModel);
        this.text.set(text);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //拿到position
            int position = viewModel.observableList.indexOf(MultiRecycleLeftItemViewModel.this);
            ToastUtils.showShort("position：" + position);
        }
    });
}
