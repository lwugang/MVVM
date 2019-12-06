package com.leewg.mvvm.data;

import com.leewg.mvvm.data.http.service.ApiService;
import com.leewg.mvvm.data.http.source.ApiRepository;
import com.leewg.mvvm.data.http.source.HttpDataSource;
import com.leewg.mvvm.data.http.source.HttpDataSourceImpl;
import com.leewg.mvvm.data.http.source.local.LocalDataSource;
import com.leewg.mvvm.data.http.source.local.LocalDataSourceImpl;
import com.leewg.mvvm.data.utils.RetrofitClient;


/**
 * 注入全局的数据仓库，可以考虑使用Dagger2。（根据项目实际情况搭建，千万不要为了架构而架构）
 * Created by leewg on 2019/3/26.
 */
public class Injection {
    public static ApiRepository provideRepository() {
        //网络API服务
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        //网络数据源
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);
        //本地数据源
        LocalDataSource localDataSource = LocalDataSourceImpl.getInstance();
        //两条分支组成一个数据仓库
        return ApiRepository.getInstance(httpDataSource, localDataSource);
    }
}
