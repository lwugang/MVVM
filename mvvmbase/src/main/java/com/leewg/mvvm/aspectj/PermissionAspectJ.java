package com.leewg.mvvm.aspectj;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.leewg.mvvm.base.AppManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import io.reactivex.functions.Consumer;

/**
 * 权限请求
 */
@Aspect
public class PermissionAspectJ {

    @Around("execution(@com.leewg.mvvm.aspectj.Permission * *..*.*(..))")
    public void checkPermission(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Permission permission = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getAnnotation(Permission.class);
        if (permission == null)
            return;
        Object obj = proceedingJoinPoint.getThis();
        RxPermissions rxPermissions = getRxPermissions(obj);
        if (rxPermissions == null) {
            throw new RuntimeException("rxPermissions 无法初始化，请在Activity/Fragment/View中使用");
        }
        rxPermissions.request(permission.value())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            try {
                                proceedingJoinPoint.proceed();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    @Nullable
    private RxPermissions getRxPermissions(Object obj) {
        RxPermissions rxPermissions = null;
        if (obj instanceof FragmentActivity) {
            rxPermissions = new RxPermissions((FragmentActivity) obj);
        } else if (obj instanceof Fragment) {
            rxPermissions = new RxPermissions((Fragment) obj);
        } else if (obj instanceof View) {
            rxPermissions = new RxPermissions((FragmentActivity) ((View) obj).getContext());
        } else {
            rxPermissions = new RxPermissions((FragmentActivity) AppManager.getAppManager().currentActivity());
        }
        return rxPermissions;
    }
}
