package com.bawei.wtest2.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.wtest2.R;
import com.bawei.wtest2.contract.ImplView;
import com.bawei.wtest2.entity.Result;
import com.bawei.wtest2.presenter.RegisterPresenter;
import com.blankj.utilcode.util.RegexUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.lib_core.BaseActivity;

public class RegisterActivity extends BaseActivity implements ImplView {


    @BindView(R.id.iv_name)
    ImageView ivName;
    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.r1_name)
    RelativeLayout r1Name;
    @BindView(R.id.iv_iphone)
    ImageView ivIphone;
    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.r1_phone)
    RelativeLayout r1Phone;
    @BindView(R.id.iv_password)
    ImageView ivPassword;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.eye_password)
    ImageView eyePassword;
    @BindView(R.id.r1_pwd)
    RelativeLayout r1Pwd;
    @BindView(R.id.regar_sms)
    TextView regarSms;
    @BindView(R.id.btn_rigister)
    Button btnRigister;

    private RegisterPresenter registerPresenter;
    public boolean flag;//设置密码是否可见 标识符

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //绑定
        ButterKnife.bind(this);
    }

    /*
     *  以下都是实现接口然后生成的方法
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        //实例化p层
        registerPresenter = new RegisterPresenter(this);
    }

    @Override
    protected void initData() {

    }
    //防止内存溢出
    @Override
    protected void destoryData() {
        if (registerPresenter != null){
            registerPresenter.unBind();
            registerPresenter = null;
        }
    }

    //注册成功的回调
    @Override
    public void success(Result result) {
        showToast(result.getMessage());
    }

    //注册失败的回调
    @Override
    public void fail(Result result) {
        showToast(result.getMessage());
    }

    /*
     *
     * 生成的点击事件
     *
     */
    @OnClick({R.id.eye_password, R.id.btn_rigister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eye_password:
                //设置密码的显示和隐藏
                if (flag) {
                    //隐藏密码
                    registerPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = false;
                }else{
                    //显示密码
                    registerPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = true;
                }
                break;

            case R.id.btn_rigister:
                String phone = registerPhone.getText().toString().trim();
                String pwd = registerPassword.getText().toString().trim();
                //非空判断
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)){
                    showToast("手机号或密码不能为空！！！");
                }//正则判断
                else if (!RegexUtils.isMobileExact(phone)) {
                    showToast("请输入正确的手机号！！");
                }else{
                    registerPresenter.requestData(phone,pwd);
                }
                break;
        }
    }


}
