package com.crossy.app.everythinghouse.utils.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.utils.CrossyApplication;
import com.crossy.app.everythinghouse.utils.DataUtil;


public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        initApp();

        Intent intent = new Intent(this,TabHostActivity.class);
        startActivity(intent);
        finish();
    }

    private void initApp(){
        CrossyApplication.init(getApplication());
        DataUtil.init();
    }

}
