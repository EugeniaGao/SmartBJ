package com.jing.www.smartbj.utils;

import com.jing.www.smartbj.bean.NewsCenterTabBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Eugenia Gao on 2020/6/3.
 * Describe:使用Retrofit联网框架,网络接口
 */
public interface ServiceApi {

    String base_url = "http://v.juhe.cn/toutiao/index";

    //登录接口
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("appWxMini/login")
    Call<NewsCenterTabBean.TopNewsBean> getTopNews(@Body RequestBody body);
}
