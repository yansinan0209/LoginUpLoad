package com.bawei.wtest2.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.wtest2.R;
import com.bawei.wtest2.contract.ImplView;
import com.bawei.wtest2.entity.Result;
import com.bawei.wtest2.entity.UserInfo;
import com.bawei.wtest2.presenter.LoginPresenter;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.lib_core.BaseActivity;

public class LoginActivity extends BaseActivity implements ImplView<UserInfo> {


    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.eye_password)
    ImageView eyePassword;
    @BindView(R.id.quick_register)
    TextView quickRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;

    //声明登录的p层
    private LoginPresenter loginPresenter;
    //设置密码是否可见
    public boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //绑定
        ButterKnife.bind(this);
    }

    /*
     * 以下都是实现抽机类然后重写的方法
     */
    @Override
    protected int getLayoutId() {
        //加载布局
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //实例化login的p层
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {

    }
    //防止内存溢出的方法
    @Override
    protected void destoryData() {
        if (loginPresenter != null){
            loginPresenter.unBind();
            loginPresenter = null;
        }
    }

    //登录成功的回调
    @Override
    public void success(Result result) {
        UserInfo userInfo = (UserInfo) result.getResult();
        String userId = userInfo.getUserId();
        String sessionId = userInfo.getSessionId();
        SPUtils.getInstance().put("userId",userId);
        SPUtils.getInstance().put("sessionId",sessionId);
        //登录成功跳转MainActivity
        intent(MainActivity.class);
        finish();
    }
    //登录失败的回调
    @Override
    public void fail(Result result) {
        showToast(result.getMessage());

    }

    /*
     *生成的点击事件
     */
    @OnClick({R.id.login_phone, R.id.login_password, R.id.eye_password, R.id.quick_register, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_phone:
                break;
            case R.id.login_password:
                break;
            case R.id.eye_password:
                //设置密码的显示和隐藏
                if (flag){
                    //密码隐藏
                    loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = false;
                }else{
                    //密码显示
                    loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = true;
                }
                break;
            case R.id.quick_register:
                intent(RegisterActivity.class);
                break;
            case R.id.btn_login:
                //保存手机号和登录密码
                String phone = loginPhone.getText().toString().trim();
                String pwd = loginPassword.getText().toString().trim();
                //非空判断
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)){
                    showToast("手机号和密码不能为空！！！");
                }//正则表达式
                else if(!RegexUtils.isMobileExact(phone)){
                    showToast("请输入正确的手机号");
                }else{
                    loginPresenter.requestData(phone,pwd);
                }
                break;
        }
    }
}
