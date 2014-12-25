package com.crossy.app.everythinghouse.orders.send;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.utils.ViewUtil;

public class EveryHouseOrderSendActivity extends Activity {

    private Button buttonPickTime;
    private BootstrapEditText editTextLocation;
    private BootstrapEditText editTextMoney;
    private BootstrapEditText editTextContent;
    private CheckBox checkBoxAnonymous;

    private String time;
    private String location;
    private String money;
    private String content;
    private boolean isAnonymous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_house_order_send);

        initView();

        registerListener();

        initActionBar();
    }

    private void initView(){
        buttonPickTime = (Button)findViewById(R.id.buttonPickTime);
        editTextLocation = (BootstrapEditText)findViewById(R.id.editTextLocation);
        editTextMoney = (BootstrapEditText)findViewById(R.id.editTextMoney);
        editTextContent = (BootstrapEditText)findViewById(R.id.editTextContent);
        checkBoxAnonymous = (CheckBox)findViewById(R.id.checkboxAnonymous);

        buttonPickTime.setText("0000-00-00 00:00:00(点击修改)");
    }

    private void registerListener(){
        buttonPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initActionBar(){
        ActionBar actionBar = getActionBar();
        ViewUtil.customizeActionBar(actionBar, R.layout.actionbar_every_house_order_send);
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
