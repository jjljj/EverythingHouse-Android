package com.crossy.app.everythinghouse.utils.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.crossy.app.everythinghouse.R;


public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Intent intent = new Intent(this,TabHostActivity.class);
        startActivity(intent);
        finish();
    }

}
