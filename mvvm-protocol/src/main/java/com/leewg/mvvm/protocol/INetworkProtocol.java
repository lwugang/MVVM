package com.leewg.mvvm.protocol;

/**
 * @author liwugang
 * @title
 * @date 2020-09-25
 * @email 13480020053@163.com
 */
public interface INetworkProtocol {
    <T> T createRetrofitApi(String url,Class<T> service);

    <T> T createRetrofitApi(String url,boolean useCache,Class<T> service);
}
