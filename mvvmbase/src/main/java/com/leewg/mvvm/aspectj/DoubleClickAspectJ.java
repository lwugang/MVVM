package com.leewg.mvvm.aspectj;

import android.view.View;

import com.leewg.mvvm.R;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 防止点击时间重复执行
 */
@Aspect
public class DoubleClickAspectJ {
    public static final int DELAY = 1000;  //连击事件间隔

    @Around("execution(* android.view.View.OnClickListener.onViewClick(..))")
    public void onClickListener(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object o = proceedingJoinPoint.getArgs()[0];
        if (o == null) {
            proceedingJoinPoint.proceed();
            return;
        }
        View view = (View) o;
        long currentTime = System.currentTimeMillis();
        long lastClickTime = 0;
        if (view.getTag(R.id.dataBinding) != null) {
            lastClickTime = (long) view.getTag(R.id.dataBinding);
        }
        if (currentTime - lastClickTime > DELAY) {  //判断时间差
            lastClickTime = currentTime;  //记录最后一次点击时间
            proceedingJoinPoint.proceed();
        }
        view.setTag(R.id.dataBinding, lastClickTime);
    }

    @Around("execution(@com.leewg.mvvm.aspectj.SingleClick * *..*.*(..))")
    public void onSingleClick(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        onClickListener(proceedingJoinPoint);
    }

    @Around("execution(* android.widget.AdapterView$OnItemClickListener.onItemClick(..))")
    public void onItemClickListener(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object o = proceedingJoinPoint.getArgs()[1];
        if (o == null) {
            proceedingJoinPoint.proceed();
            return;
        }
        View view = (View) o;
        long currentTime = System.currentTimeMillis();
        long lastClickTime = 0;
        if (view.getTag(R.id.dataBinding) != null) {
            lastClickTime = (long) view.getTag(R.id.dataBinding);
        }
        if (currentTime - lastClickTime > DELAY) {  //判断时间差
            lastClickTime = currentTime;  //记录最后一次点击时间
            proceedingJoinPoint.proceed();
        }
        view.setTag(R.id.dataBinding, lastClickTime);
    }
}
