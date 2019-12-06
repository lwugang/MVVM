package com.leewg.mvvm.ui.list;

import android.app.Application;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leewg.mvvm.BR;
import com.leewg.mvvm.base.BaseFragment;
import com.leewg.mvvm.ui.R;
import com.leewg.mvvm.ui.databinding.LayoutBaseRefreshBinding;
import com.leewg.mvvm.utils.NetworkUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;



/**
 * @author liwugang
 * @title
 * @date 2019-08-12
 * @email 13480020053@163.com
 */
public abstract class BaseListFragment<VM extends BaseListViewModel>
        extends BaseFragment<LayoutBaseRefreshBinding, VM> implements OnRefreshLoadmoreListener {
    static final int STATE_NORMAL = 0x0;
    static final int STATE_REFRESH = 0x1;
    static final int STATE_LOAD_MORE = 0x2;

    static final int PAGE_SIZE = 20;
    int currentPage = 1;
    int currentState = STATE_NORMAL;

    SmartRefreshLayout refreshLayout;

    @Override
    public int initVariableId() {
        return BR.model;
    }

    @Override
    public VM initViewModel() {
        try {
            Type type = getClass().getGenericSuperclass();
            Class modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            return (VM) modelClass.getConstructor(Application.class, int.class, int.class).newInstance(getActivity().getApplication(),
                    getItemId(), getItemLayoutResId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.initViewModel();
    }

    protected abstract int getItemId();

    protected abstract int getItemLayoutResId();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.layout_base_refresh;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        refreshLayout = binding.refreshLayout;
        viewModel.itemListLiveData.observe(this, itemLists -> {
            setDataChange((List) itemLists);
        });
        viewModel.loadError.observe(this, aBoolean -> {
            setListError(null);
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

    /**
     * 是否处于刷新
     *
     * @return
     */
    protected boolean isRefresh() {
        return currentState == STATE_REFRESH;
    }

    /**
     * 是否处于加载更多
     *
     * @return
     */
    protected boolean isLoadMore() {
        return currentState == STATE_LOAD_MORE;
    }

    /**
     * 加载更多
     *
     * @param currentPage
     */
    protected abstract void onLoadMore(int currentPage);

    /**
     * 开始刷新
     *
     * @param currentPage
     */
    protected abstract void onRefresh(int currentPage);

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        if (!NetworkUtil.isNetworkAvailable(getContext())) {
            return;
        }
        currentState = STATE_REFRESH;
        currentPage = 1;
        onRefresh(currentPage);
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
//        if (viewModel.getItems().size() >= getTotalCount()) {
//            refreshlayout.finishLoadmore(500, true);
//            return;
//        }
        currentState = STATE_LOAD_MORE;
        currentPage++;
        onLoadMore(currentPage);
    }

    /**
     * 获取总数
     *
     * @return
     */
    protected abstract int getTotalCount();

    public static int getPageSize() {
        return PAGE_SIZE;
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
        if (isLoadMore()) {
            viewModel.addItems(datas);
            refreshLayout.finishLoadmore(500,true);
        } else {
            viewModel.setItems(datas);
            refreshLayout.finishRefresh();
        }
        currentState = STATE_NORMAL;
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

    public int getCurrentPage() {
        return currentPage;
    }
}