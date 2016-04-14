package com.atguigu.time.module_mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.time.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class UserRegistActivity extends Activity {
    /*
    手机号
     */
    @ViewInject(R.id.ed_phone)
    private EditText ed_phone;

    /*
    密码
     */
    @ViewInject(R.id.et_password)
    private EditText et_password;

    /*
    验证码
     */
    @ViewInject(R.id.et_verification)
    private EditText et_verification;

    /*
    获取验证码
     */
    @ViewInject(R.id.btn_getVerification)
    private Button btn_getVerification;

    /*
    是否显示密码
     */
    @ViewInject(R.id.cb_isshow)
    private CheckBox cb_isshow;

    /*
    退出按钮
     */
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    @ViewInject(R.id.btn_submit)
    private Button btn_submit;
    private String number;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_regist);
        x.view().inject(this);

        SMSSDK.registerEventHandler(ev); //注册短信回调监听
        setListener();
    }


    /**
     * 设置各种按钮的监听
     */
    private void setListener() {

        btn_getVerification.setOnClickListener(new View.OnClickListener() {//获取验证码
            @Override
            public void onClick(View v) {
                number = ed_phone.getText().toString();
                if (TextUtils.isEmpty(number)) {
                    showDailog("号码不能为空");
                } else {
                    SMSSDK.getVerificationCode("+86", number);
                    showDailog("验证码发送成功！");
                }
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {//提交验证码
            @Override
            public void onClick(View v) {
                String security = et_verification.getText().toString();
                if (!TextUtils.isEmpty(security) && number != number && et_password.getText().toString() != number) {
                    dialog = ProgressDialog.show(UserRegistActivity.this, null, "正在验证...", false, true);
                    //提交短信验证码
                    SMSSDK.submitVerificationCode("+86", number, security);//国家号，手机号码，验证码
                    Toast.makeText(UserRegistActivity.this, "提交了注册信息:" + number, Toast.LENGTH_SHORT).show();
                } else {
                    showDailog("请完善注册信息！");
                }
            }
        });

        cb_isshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//设置密码是否显示
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {//退出
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showDailog(String text) {
        new AlertDialog.Builder(this)
                .setTitle(text)
                .setPositiveButton("确定", null)
                .show();
    }

    /**
     * 短信验证的回调监听
     */
    private EventHandler ev = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) { //回调完成
                //提交验证码成功,如果验证成功会在data里返回数据。data数据类型为HashMap<number,code>
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Log.e("TAG", "提交验证码成功" + data.toString());
                    HashMap<String, Object> mData = (HashMap<String, Object>) data;
                    String country = (String) mData.get("country");//返回的国家编号
                    String phone = (String) mData.get("phone");//返回用户注册的手机号

                    Log.e("TAG", country + "====" + phone);

                    if (phone.equals(number)) {
                        runOnUiThread(new Runnable() {//更改ui的操作要放在主线程，实际可以发送hander
                            @Override
                            public void run() {
                                showDailog("恭喜你！通过验证");
                                dialog.dismiss();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showDailog("验证失败");
                                dialog.dismiss();
                            }
                        });
                    }

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {//获取验证码成功
                    Log.e("TAG", "获取验证码成功");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表

                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };
}
