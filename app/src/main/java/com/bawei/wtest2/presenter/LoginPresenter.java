package com.bawei.wtest2.presenter;

import com.bawei.wtest2.api.ApiServer;
import com.bawei.wtest2.contract.ImplView;
import com.bawei.wtest2.model.BaseModel;

import io.reactivex.Observable;

/**
 * Create by ysn
 * TIME: 2019/7/12
 * Doing:
 */
public class LoginPresenter extends BaseModel{

    public LoginPresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(ApiServer apiServer, Object... args) {
        return apiServer.login(
                String.valueOf(args[0]),
                String.valueOf(args[1]));
    }
}
