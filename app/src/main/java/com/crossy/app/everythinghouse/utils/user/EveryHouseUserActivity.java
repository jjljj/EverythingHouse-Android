package com.crossy.app.everythinghouse.utils.user;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.utils.DataUtil;
import com.crossy.app.everythinghouse.utils.ViewUtil;
import com.crossy.app.everythinghouse.utils.api.API_EVERYTHING_HOUSE;
import com.crossy.app.everythinghouse.utils.api.API_SPF;

public class EveryHouseUserActivity extends Activity {

    private final int REQUEST_CODE_LOGIN = 1000;

    private BootstrapButton buttonGoLogin;
    private BootstrapEditText editTextWebHost;
    private BootstrapButton buttonWebHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_house_user);

        initView();

        registerListener();

        initActionBar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_LOGIN:
                if(resultCode == RESULT_OK){
                    buttonGoLogin.setText("已登录");
                }else{
                    buttonGoLogin.setText("登陆失败，请重试");
                }

                break;
        }
    }

    private void initView(){
        buttonGoLogin = (BootstrapButton)findViewById(R.id.buttonGoLogin);
        if(!DataUtil.getString(API_SPF.NAME_USER,API_SPF.USER_SESSION_EVERY_HOUSE,"").equals("")){
            buttonGoLogin.setText("已登录");
        }
        editTextWebHost = (BootstrapEditText)findViewById(R.id.editTextWebHost);
        buttonWebHost = (BootstrapButton)findViewById(R.id.buttonSaveWebHost);
        if(!DataUtil.getString(API_EVERYTHING_HOUSE.SPF_NAME,API_EVERYTHING_HOUSE.SPF_KEY_WEB_HOST,"").equals("")){
            editTextWebHost.setText(DataUtil.getString(API_EVERYTHING_HOUSE.SPF_NAME,API_EVERYTHING_HOUSE.SPF_KEY_WEB_HOST,""));
        }
    }

    private void registerListener(){
        buttonGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(EveryHouseUserActivity.this, EveryHouseLoginActivity.class), REQUEST_CODE_LOGIN);
            }
        });
        buttonWebHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataUtil.putString(API_EVERYTHING_HOUSE.SPF_NAME,API_EVERYTHING_HOUSE.SPF_KEY_WEB_HOST,editTextWebHost.getText().toString());
            }
        });
    }

    private void initActionBar(){
        ActionBar actionBar = getActionBar();
        ViewUtil.customizeActionBar(actionBar, R.layout.actionbar_every_house_user);
        View view = actionBar.getCustomView();
        view.findViewById(R.id.layout_actionbar_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        view.findViewById(R.id.layout_actionbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
