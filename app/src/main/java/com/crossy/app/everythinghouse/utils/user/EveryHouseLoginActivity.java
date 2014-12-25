package com.crossy.app.everythinghouse.utils.user;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.utils.ViewUtil;

public class EveryHouseLoginActivity extends Activity {

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
