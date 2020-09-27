package com.leewg.mvvm.provider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author liwugang
 * @title
 * @date 2020-09-27
 * @email 13480020053@163.com
 */
public final class AutoServiceLazySingleton {
    private final static HashMap<Class, Object> SINGLETON = new HashMap<>();

    public static <T> T get(Class<T> service) {
        Object o = SINGLETON.get(service);
        if (o != null) {
            return (T) o;
        }
        Iterator<T> iterator = ServiceLoader.load(service).iterator();
        if (iterator.hasNext()) {
            T value = iterator.next();
            SINGLETON.put(service, value);
            return value;
        }
        return null;
    }
}
