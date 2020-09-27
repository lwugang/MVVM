package com.leewg.mvvm.app.http.source;

import com.leewg.mvvm.app.LoginBean;
import com.leewg.mvvm.data.BaseResponse;
import com.leewg.mvvm.data.http.source.IDataSource;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author liwugang
 * @title
 * @date 2020-09-27
 * @email 13480020053@163.com
 */
public interface IHttpDataSource extends IDataSource {

    Observable<BaseResponse<LoginBean>> login(Map<String, String> data);
}
