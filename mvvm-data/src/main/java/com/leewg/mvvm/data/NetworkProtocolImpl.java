package com.leewg.mvvm.data;

import android.app.Application;

import com.google.auto.service.AutoService;
import com.leewg.mvvm.data.http.source.ApiRepository;
import com.leewg.mvvm.data.utils.RetrofitClient;
import com.leewg.mvvm.protocol.IApiRepository;
import com.leewg.mvvm.protocol.INetworkProtocol;

/**
 * @author liwugang
 * @title
 * @date 2020-09-27
 * @email 13480020053@163.com
 */
@AutoService(INetworkProtocol.class)
public class NetworkProtocolImpl implements INetworkProtocol {

    @Override
    public <T> T createRetrofitApi(String url, Class<T> service) {
        return RetrofitClient.create(url, service);
    }

    @Override
    public <T> T createRetrofitApi(String url, boolean useCache, Class<T> service) {
        return RetrofitClient.create(url, useCache, service);
    }
}
