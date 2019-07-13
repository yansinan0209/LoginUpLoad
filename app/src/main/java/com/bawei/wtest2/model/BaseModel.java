package com.bawei.wtest2.model;

import com.bawei.wtest2.api.ApiServer;
import com.bawei.wtest2.contract.ImplView;
import com.bawei.wtest2.entity.Result;
import com.bawei.wtest2.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Create by ysn
 * TIME: 2019/7/12
 * Doing:相当于BasePresenter  十一二个抽象类，因为他是要被继承的
 */
public abstract class BaseModel {

    //首先要声明契约类中的(DataCall)ImplView
    private ImplView implView;
    //它的构造方法
    public BaseModel(ImplView implView) {
        this.implView = implView;
    }

    //定义一个展示数据的方法
    public void requestData(Object...args){
        //单例模式引出注解层
        ApiServer apiServer = RetrofitUtil.getInstance().create(ApiServer.class);
        //调用getModel()方法
        getModel(apiServer,args).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        if ("0000".equals(result.getStatus())) {
                            implView.success(result);
                        } else {
                            implView.fail(result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    //定义一个model层的方法
    protected abstract Observable getModel(ApiServer apiServer,Object...args);

    //避免内存泄漏
    public void unBind(){
        if (implView != null){
            implView = null;
        }
    }


}
