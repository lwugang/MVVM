package com.leewg.mvvm.ui.list;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leewg.mvvm.BR;
import com.leewg.mvvm.base.BaseFragment;
import com.leewg.mvvm.tools.NetworkUtil;
import com.leewg.mvvm.ui.R;
import com.leewg.mvvm.ui.databinding.LayoutBaseRefreshBinding;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;


/**
 * @author liwugang
 * @title
 * @date 2019-08-12
 * @email 13480020053@163.com
 */
public abstract class BaseListFragment<VM extends BaseListViewModel>
        extends BaseFragment<LayoutBaseRefreshBinding, VM> implements OnRefreshLoadmoreListener, BaseListViewModel.OnBindExtraListener {

    SmartRefreshLayout refreshLayout;

    @Override
    public int initVariableId() {
        return BR.model;
    }

    @Override
    public <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> cls) {
        BaseListViewModel baseListViewModel = (BaseListViewModel)super.createViewModel(fragment, cls);
        baseListViewModel.bindingItem(getItemVariableId(),getItemLayoutResId(),this);
        return (T)baseListViewModel;
    }

    protected abstract int getItemVariableId();

    protected abstract int getItemLayoutResId();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.layout_base_refresh;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        refreshLayout = binding.refreshLayout;
        // 加载成功
        viewModel.loadSuccess.observe(this, itemLists -> {
            setDataChange(itemLists);
        });
        // 加载失败
        viewModel.loadError.observe(this, aBoolean -> {
            setListError(null);
        });
        // 通知界面刷新
        viewModel.notifyViewRefresh.observe(this, aBoolean -> {
            startRefresh();
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshLayout.setOnRefreshLoadmoreListener(this);
        if (isAutoRefresh()) {
            refreshLayout.autoRefresh();
        }
    }

    public void disableRefreshAndLoadMore() {
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
    }


    public void disableRefresh() {
        refreshLayout.setEnableRefresh(false);
    }


    protected boolean isAutoRefresh() {
        return true;
    }

    /**
     * 开始刷新
     */
    public void startRefresh() {
        refreshLayout.autoRefresh();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        if (!NetworkUtil.isNetworkAvailable(getContext())) {
            return;
        }
        viewModel.refresh();
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        viewModel.next();
    }

    /**
     * 通知数据改变
     *
     * @param datas
     */
    protected void setDataChange(List datas) {
        setDataChange(datas, true);
    }


    /**
     * 通知数据改变
     *
     * @param datas
     */
    protected void setDataChange(List datas, boolean showNoDataTips) {
        if (viewModel.isLoadMore()) {
            viewModel.addItems(datas);
            refreshLayout.finishLoadmore(500, true);
        } else {
            viewModel.setItems(datas);
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 设置异常
     */
    protected void setListError(Throwable throwable) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void onBindExtra(ItemBinding itemBinding) {

    }
}