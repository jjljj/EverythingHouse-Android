package com.crossy.app.everythinghouse.acts;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.utils.ViewUtil;
import com.crossy.app.everythinghouse.utils.launch.TabHostActivity;

public class EveryHouseActsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_house_acts);

        initActionBar();
    }

    @Override
    protected void onResume(){
        super.onResume();
        initActionBar();
    }

    private void initActionBar(){
        ActionBar actionBar = TabHostActivity.actionBar;
        ViewUtil.customizeActionBar(actionBar, R.layout.actionbar_every_house_acts);
        View view = actionBar.getCustomView();
    }
}
