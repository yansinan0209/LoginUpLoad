package com.bawei.wtest2.util;

import com.bawei.wtest2.common.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by ysn
 * TIME: 2019/7/11
 * Doing:网络请求工具类
 */
public class RetrofitUtil {

    private static RetrofitUtil instance;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    //1.私有化构造方法
    private RetrofitUtil(){
        //打印日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    //2.动态代理模式
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }

    //3.单例模式之双重锁架构
    public static RetrofitUtil getInstance(){

        if (instance == null){
            synchronized (RetrofitUtil.class){
                if (instance == null){
                    instance = new RetrofitUtil();
                }
            }
        }
        return instance;
    }
}
