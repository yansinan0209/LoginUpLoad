package com.bawei.wtest2.presenter;

import com.bawei.wtest2.api.ApiServer;
import com.bawei.wtest2.contract.ImplView;
import com.bawei.wtest2.model.BaseModel;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * Create by ysn
 * TIME: 2019/7/12
 * Doing:
 */
public class UpIconPresenter extends BaseModel{

    public UpIconPresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(ApiServer apiServer, Object... args) {
        return apiServer.upIcon(
                String.valueOf(args[0]),
                String.valueOf(args[1]),
                (MultipartBody.Part) args[2]);
    }
}
