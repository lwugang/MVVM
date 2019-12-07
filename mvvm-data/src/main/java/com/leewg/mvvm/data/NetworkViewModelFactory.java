package com.leewg.mvvm.data;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;

import com.leewg.mvvm.data.http.source.ApiRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by leewg on 2018/9/30.
 */
public class NetworkViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile NetworkViewModelFactory INSTANCE;

    private final Application mApplication;

    private final ApiRepository mRepository;

    public static NetworkViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (NetworkViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NetworkViewModelFactory(application, Injection.provideRepository());
                }
            }
        }
        return INSTANCE;
    }

    private NetworkViewModelFactory(Application application, ApiRepository repository) {
        mApplication = application;
        mRepository = repository;
    }

    public ApiRepository getRepository() {
        return mRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) createViewModel(cls, mApplication, mRepository);
    }

    ViewModel createViewModel(Class cls, Application application, ApiRepository repository) {
        try {
            Constructor constructor = cls.getConstructor(Application.class, ApiRepository.class);
            return (ViewModel) constructor.newInstance(application, repository);
        } catch (NoSuchMethodException e) {
            try {
                return (ViewModel) cls.getConstructor(Application.class).newInstance(application);
            } catch (Exception e1) {
                throw new IllegalArgumentException(e1);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
