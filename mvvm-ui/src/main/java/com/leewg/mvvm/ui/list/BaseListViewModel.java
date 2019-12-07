package com.leewg.mvvm.ui.list;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.leewg.mvvm.data.NetworkBaseViewModel;
import com.leewg.mvvm.data.http.source.ApiRepository;

import java.util.Collections;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

public class BaseListViewModel extends NetworkBaseViewModel {
    public interface OnBindExtraListener {
        void onBindExtra(ItemBinding itemBinding);
    }

    public BaseListViewModel(@NonNull Application application, ApiRepository repository) {
        super(application, repository);
    }

    public void bindingItem(final int variableId,
                              @LayoutRes final int layoutRes,
                            final OnBindExtraListener onBindExtraListener){
        itemBinding = ItemBinding.of(new OnItemBind<Object>() {
            @Override
            public void onItemBind(ItemBinding itemBinding, int position, Object item) {
                itemBinding.set(variableId, layoutRes);
                if (onBindExtraListener != null) {
                    onBindExtraListener.onBindExtra(itemBinding);
                }
                onBindExtra(itemBinding);
            }
        });
    }

    /**
     * 绑定扩展参数
     *
     * @param itemBinding
     */
    protected void onBindExtra(ItemBinding itemBinding) {

    }

    /**
     * 数据集
     */
    public ObservableList items = new ObservableArrayList<>();
    public ItemBinding<?> itemBinding;


    /**
     * 通知界面开始刷新
     */
    public MutableLiveData<Boolean> notifyViewRefresh = new MutableLiveData<>();
    /**
     * 加载成功监听
     */
    public MutableLiveData<List> loadSuccess = new RefreshStateLiveData(this);
    /**
     * 加载失败监听
     */
    public MutableLiveData<Boolean> loadError = new RefreshStateLiveData(this);

    private int totalPage = 1;
    private int currentPage = 1;


    static final int STATE_NORMAL = 0x0;
    static final int STATE_REFRESH = 0x1;
    static final int STATE_LOAD_MORE = 0x2;
    static final int PAGE_SIZE = 20;
    volatile int currentState = STATE_NORMAL;

    /**
     * 通知界面开始刷新
     */
    protected final void notifyViewRefresh() {
        notifyViewRefresh.setValue(true);
    }

    /**
     * 是否处于刷新状态
     *
     * @return
     */
    public final boolean isRefresh() {
        return currentState == STATE_REFRESH;
    }

    /**
     * 是否处于加载更多状态
     *
     * @return
     */
    public final boolean isLoadMore() {
        return currentState == STATE_REFRESH;
    }

    /**
     * 刷新数据
     */
    public final void refresh() {
        if (currentState == STATE_NORMAL)
            return;
        currentPage = 1;
        currentState = STATE_REFRESH;
        load(currentPage);
    }

    /**
     * 加载下一页数据
     */
    public final void next() {
        if (currentPage > totalPage) {
            loadSuccess.setValue(Collections.emptyList());
            return;
        }
        if (currentState == STATE_NORMAL)
            return;
        currentState = STATE_LOAD_MORE;
        currentPage++;
        load(currentPage);
    }

    public final void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public final void setItems(List<?> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    public final void addItems(List<?> items) {
        this.items.addAll(items);
    }

    public final ObservableList<?> getItems() {
        return items;
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    /**
     * 开始加载数据
     *
     * @param currentPage
     */
    protected void load(int currentPage) {

    }

    /**
     * 刷新状态还原
     *
     * @param <T>
     */
    final class RefreshStateLiveData<T> extends MutableLiveData<T> {
        BaseListViewModel viewModel;

        public RefreshStateLiveData(BaseListViewModel viewModel) {
            this.viewModel = viewModel;
        }

        @Override
        public void setValue(T value) {
            viewModel.currentPage = STATE_NORMAL;
            super.setValue(value);
        }

        @Override
        public void postValue(T value) {
            viewModel.currentPage = STATE_NORMAL;
            super.postValue(value);
        }
    }
}
