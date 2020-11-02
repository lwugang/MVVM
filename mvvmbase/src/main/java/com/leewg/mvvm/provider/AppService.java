package com.leewg.mvvm.provider;

import com.leewg.mvvm.protocol.INetworkProtocol;

import java.util.HashMap;

public final class AppService {

    public INetworkProtocol getNetworkProtocol() {
        return AutoServiceLazySingleton.get(INetworkProtocol.class);
    }

    private static AppService service = new AppService();

    private HashMap<String, Object> serviceMap;

    public static AppService getService() {
        return service;
    }

    /**
     * 注册服务
     *
     * @param key
     * @param value
     */
    public void registerService(String key, Object value) {
        if (serviceMap == null) {
            serviceMap = new HashMap<String, Object>();
        }
        serviceMap.put(key, value);
    }

    /**
     * 获取APP 服务
     *
     * @param key
     * @return
     */
    public <T> T getAppService(String key) {
        return (T)serviceMap.get(key);
    }
}
