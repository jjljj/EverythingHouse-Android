package com.crossy.app.everythinghouse.utils.user;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.utils.ViewUtil;

public class EveryHouseUserActivity extends Activity {

    private BootstrapButton buttonGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_house_user);

        initView();

        registerListener();

        initActionBar();
    }

    private void initView(){
        buttonGoLogin = (BootstrapButton)findViewById(R.id.buttonGoLogin);
    }

    private void registerListener(){
        buttonGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EveryHouseUserActivity.this,EveryHouseLoginActivity.class));
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
