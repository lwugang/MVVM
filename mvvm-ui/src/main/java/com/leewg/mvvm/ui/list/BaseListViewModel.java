package com.leewg.mvvm.ui.list;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.leewg.mvvm.base.BaseModel;
import com.leewg.mvvm.base.BaseViewModel;

import java.util.Collections;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

public class BaseListViewModel<T extends BaseModel> extends BaseViewModel<T> {
    public interface OnBindExtraListener{
        void onBindExtra(ItemBinding itemBinding);
    }
    public BaseListViewModel(@NonNull Application application, int variableId,
                             @LayoutRes int layoutRes) {
        this(application,variableId,layoutRes,null);
    }

    public BaseListViewModel(@NonNull Application application,final int variableId,
                             @LayoutRes final int layoutRes,final OnBindExtraListener onBindExtraListener) {
        super(application);
        itemBinding = ItemBinding.of(new OnItemBind<T>() {
            @Override
            public void onItemBind(ItemBinding itemBinding, int position, T item) {
                itemBinding.set(variableId, layoutRes);
                if(onBindExtraListener!=null){
                    onBindExtraListener.onBindExtra(itemBinding);
                }
                onBindExtra(itemBinding);
            }
        });
    }

    /**
     * 绑定扩展参数
     * @param itemBinding
     */
    protected void onBindExtra(ItemBinding itemBinding) {

    }

    public ObservableList<T> items = new ObservableArrayList<>();
    public ItemBinding<T> itemBinding;

    public MutableLiveData<List> itemListLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadError = new MutableLiveData<>();

    private int totalPage = 1;
    private int currentPage = 1;

    public final void refresh() {
        currentPage = 1;
        load(currentPage);
    }

    public final void next() {
        if (currentPage > totalPage) {
            itemListLiveData.setValue(Collections.emptyList());
            return;
        }
        currentPage++;
        load(currentPage);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    protected void load(int currentPage) {
    }

    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    public void addItems(List<T> items) {
        this.items.addAll(items);
    }

    public ObservableList<T> getItems() {
        return items;
    }
}
