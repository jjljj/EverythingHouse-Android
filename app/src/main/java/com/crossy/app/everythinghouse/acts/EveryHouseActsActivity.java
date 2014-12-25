package com.crossy.app.everythinghouse.acts;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.utils.ViewUtil;
import com.crossy.app.everythinghouse.utils.launch.TabHostActivity;

public class EveryHouseActsActivity extends Activity {

    private LinearLayout layoutAct1;
    private LinearLayout layoutAct2;
    private LinearLayout layoutAct3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_house_acts);

        initView();

        registerListener();

        initActionBar();
    }

    @Override
    protected void onResume(){
        super.onResume();
        initActionBar();
    }

    private void initView(){
        layoutAct1 = (LinearLayout)findViewById(R.id.layoutAct1);
        layoutAct2 = (LinearLayout)findViewById(R.id.layoutAct2);
        layoutAct3 = (LinearLayout)findViewById(R.id.layoutAct3);
    }

    private void registerListener(){
        layoutAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layoutAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layoutAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initActionBar(){
        ActionBar actionBar = TabHostActivity.actionBar;
        ViewUtil.customizeActionBar(actionBar, R.layout.actionbar_every_house_acts);
        View view = actionBar.getCustomView();
    }
}
