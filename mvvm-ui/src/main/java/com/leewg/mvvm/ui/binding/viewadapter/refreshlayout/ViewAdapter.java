package com.leewg.mvvm.ui.binding.viewadapter.refreshlayout;


import android.databinding.BindingAdapter;

import com.leewg.mvvm.command.BindingCommand;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


/**
 * Created by leewg on 2017/6/16.
 * TwinklingRefreshLayout列表刷新的绑定适配器
 */
public class ViewAdapter {

    @BindingAdapter(value = {"onRefreshCommand", "onLoadMoreCommand"}, requireAll = false)
    public static void onRefreshAndLoadMoreCommand(SmartRefreshLayout layout, final BindingCommand
            onRefreshCommand, final BindingCommand onLoadMoreCommand) {
        if (onRefreshCommand != null) {
            layout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    if (onRefreshCommand != null) {
                        onRefreshCommand.execute();
                    }
                }
            });
        }
        if (onLoadMoreCommand != null) {
            layout.setOnLoadmoreListener(new OnLoadmoreListener() {
                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    if (onLoadMoreCommand != null) {
                        onLoadMoreCommand.execute();
                    }
                }
            });
        }
    }
}
