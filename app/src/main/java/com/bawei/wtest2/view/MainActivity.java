package com.bawei.wtest2.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.wtest2.R;
import com.bawei.wtest2.contract.ImplView;
import com.bawei.wtest2.entity.Result;
import com.bawei.wtest2.entity.UserEntity;
import com.bawei.wtest2.presenter.UpIconPresenter;
import com.bawei.wtest2.presenter.UserPresenter;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.lib_core.BaseActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends BaseActivity implements ImplView {

    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.r1_image)
    RelativeLayout r1Image;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.r1_name)
    RelativeLayout r1Name;
    @BindView(R.id.user_sex)
    TextView userSex;
    @BindView(R.id.r1_sex)
    RelativeLayout r1Sex;
    @BindView(R.id.tv_camera)
    TextView tvCamera;
    @BindView(R.id.tv_ablum)
    TextView tvAblum;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tool_bar)
    RelativeLayout toolBar;

    private UserPresenter userPresenter;
    private String userId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定
        ButterKnife.bind(this);
    }

    /*
     * 实现接口重写的方法
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        userPresenter = new UserPresenter(this);
    }

    @Override
    protected void initData() {
        //查询用户信息
        userId = SPUtils.getInstance().getString("userId", "");
        sessionId = SPUtils.getInstance().getString("sessionId", "");
        userPresenter.requestData(userId, sessionId);
    }

    @Override
    protected void destoryData() {
        if (userPresenter != null) {
            userPresenter.unBind();
            userPresenter = null;
        }
    }


    /*
     * 头像的点击事件
    */
    @OnClick({R.id.user_icon, R.id.tv_camera, R.id.tv_ablum, R.id.tv_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_icon:
                //点击头像，显示底部状态栏底部状态栏
                toolBar.setVisibility(View.VISIBLE);
                break;

            case R.id.tv_camera:
                //点击相机功能
                PictureSelector.create(MainActivity.this)
                        .openCamera(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(4)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .imageFormat(PictureMimeType.JPEG)// 拍照保存图片格式后缀,默认jpeg
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .enableCrop(true)// 是否裁剪 true or false
                        .compress(true)// 是否压缩 true or false
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;

            case R.id.tv_ablum:
                //点击相册
                PictureSelector.create(MainActivity.this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(4)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .imageFormat(PictureMimeType.JPEG)// 拍照保存图片格式后缀,默认jpeg
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .enableCrop(true)// 是否裁剪 true or false
                        .compress(true)// 是否压缩 true or false
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;

            case R.id.tv_cancle:
                //点击取消，隐藏
                toolBar.setVisibility(View.GONE);
                break;
        }
    }

    /*
    * 查询用户信息
    * 成功的回调
    */
    @Override
    public void success(Result result) {
        UserEntity userEntity = (UserEntity) result.getResult();
        String nickName = userEntity.getNickName();//获得用户昵称
        String headPic = userEntity.getHeadPic();//获得用户头像
        //初始化用户信息
        userName.setText(nickName);
        //头像Glide处理
        Glide.with(this).load(headPic)
                .apply(RequestOptions.centerCropTransform()
                        .placeholder(R.drawable.wait)//占位图
                        .error(R.drawable.btn_close))//错误图
                        .into(userIcon);

    }

    /*
    * 失败的回调
    */
    @Override
    public void fail(Result result) {
        showToast(result.getMessage());
    }

    /*
    * 总回调结果
    */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList != null && selectList.size() > 0){
                        String compressPath = selectList.get(0).getCompressPath();//获取路径
                        File file = new File(compressPath);
                        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
                        MultipartBody.Part part = MultipartBody.Part.createFormData(
                                "image",file.getName(),body);
                        //上传头像
                        new UpIconPresenter(new ImplView() {
                            @Override
                            public void success(Result result) {
                                String headPath = result.getHeadPath();
                                Glide.with(MainActivity.this).load(headPath)
                                        .apply(RequestOptions.centerCropTransform()
                                        .placeholder(R.drawable.wait)//占位图
                                        .error(R.drawable.btn_close))//错位图
                                .into(userIcon);
                            }

                            @Override
                            public void fail(Result result) {
                                showToast(result.getMessage());
                            }
                        }).requestData(userId,sessionId,part);
                    }
                    break;
            }
        }

    }

}
