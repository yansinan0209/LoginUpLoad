package com.bawei.wtest2.api;

import com.bawei.wtest2.common.Constant;
import com.bawei.wtest2.entity.Result;
import com.bawei.wtest2.entity.UserEntity;
import com.bawei.wtest2.entity.UserInfo;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Create by ysn
 * TIME: 2019/7/11
 * Doing:  存放注解的接口层
 *         管理接口
 */
public interface ApiServer {

    //注册
    @FormUrlEncoded
    @POST(Constant.REGISTER_URL)
    Observable<Result> register(@Field("phone") String phone,
                                @Field("pwd") String pwd);

    //登录
    @FormUrlEncoded
    @POST(Constant.LOGIN_URL)
    Observable<Result<UserInfo>> login(@Field("phone") String phone,
                                       @Field("pwd") String pwd);

    //根据ID查询用户信息
    @GET(Constant.USER_URL)
    Observable<Result<UserEntity>> findUser(@Header("userId") String userId,
                                            @Header("sessionId") String sessionId);


    //上传头像
    @Multipart
    @POST(Constant.UPICON_URL)
    Observable<Result> upIcon(@Header("userId") String userId,
                              @Header("sessionId") String sessionId,
                              @Part MultipartBody.Part file);

}
