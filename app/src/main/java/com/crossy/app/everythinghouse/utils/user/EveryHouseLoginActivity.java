package com.crossy.app.everythinghouse.utils.user;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.utils.DataUtil;
import com.crossy.app.everythinghouse.utils.HttpUtil;
import com.crossy.app.everythinghouse.utils.ViewUtil;
import com.crossy.app.everythinghouse.utils.api.API_SPF;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class EveryHouseLoginActivity extends Activity {

    private final String loginPostUrl = "http://59.78.46.141/login";

    private BootstrapEditText editTextUsername;
    private BootstrapEditText editTextPassword;
    private BootstrapButton buttonLogin;
    private TextView textViewRegister;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_house_login);

        initView();

        registerListener();

        initActionBar();
    }

    private void initView(){
        editTextUsername = (BootstrapEditText)findViewById(R.id.editTextUsername);
        editTextPassword = (BootstrapEditText)findViewById(R.id.editTextPassword);
        buttonLogin = (BootstrapButton)findViewById(R.id.buttonLogin);
        textViewRegister = (TextView)findViewById(R.id.textViewRegister);
    }

    private void registerListener(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = editTextUsername.getText().toString();
                password = editTextPassword.getText().toString();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        HttpUtil httpUtil = new HttpUtil();
                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                        nameValuePairs.add(new BasicNameValuePair("username",username));
                        nameValuePairs.add(new BasicNameValuePair("password",password));
                        nameValuePairs.add(new BasicNameValuePair("is_remember_me","1"));
                        httpUtil.post(loginPostUrl, nameValuePairs,false);
                        DefaultHttpClient defaultHttpClient = httpUtil.getDefaultHttpClient();
                        List<Cookie> cookies = defaultHttpClient.getCookieStore().getCookies();
                        if (!cookies.isEmpty()) {
                            Cookie cookie = cookies.get(0);
                            Log.i("login",cookie.getName()+"="+cookie.getValue());
                            DataUtil.putString(API_SPF.NAME_USER,API_SPF.USER_SESSION_EVERY_HOUSE,cookie.getName()+"="+cookie.getValue());
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                }.start();

            }
        });
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(EveryHouseLoginActivity.this);

                dialog.show();
            }
        });
    }

    private void initActionBar(){
        ActionBar actionBar = getActionBar();
        ViewUtil.customizeActionBar(actionBar, R.layout.actionbar_every_house_login);
        View view = actionBar.getCustomView();
        view.findViewById(R.id.layout_actionbar_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
