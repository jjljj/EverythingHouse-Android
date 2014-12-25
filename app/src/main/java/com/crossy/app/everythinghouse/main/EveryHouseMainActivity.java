package com.crossy.app.everythinghouse.main;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.orders.send.EveryHouseOrderSendActivity;
import com.crossy.app.everythinghouse.utils.ViewUtil;
import com.crossy.app.everythinghouse.utils.launch.TabHostActivity;
import com.crossy.app.everythinghouse.utils.pulltozoomview.PullToZoomScrollViewEx;

public class EveryHouseMainActivity extends Activity {

    private String TAG = "ljj";

    private final int REQUEST_CODE_SEND_ORDER = 1000;

    private PullToZoomScrollViewEx scrollViewEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_house_main);

        initView();

        initActionBar();
    }

    @Override
    protected void onResume(){
        super.onResume();
        initActionBar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_SEND_ORDER:
                break;
        }
    }

    private void initView(){
        scrollViewEx = (PullToZoomScrollViewEx)findViewById(R.id.scroll_view);
        scrollViewEx.getRootView().findViewById(R.id.tv_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick -->");
            }
        });
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollViewEx.setHeaderLayoutParams(localObject);

    }

    private void initActionBar(){
        ActionBar actionBar = TabHostActivity.actionBar;
        ViewUtil.customizeActionBar(actionBar, R.layout.actionbar_every_house_main);
        View view = actionBar.getCustomView();
        view.findViewById(R.id.layout_actionbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(EveryHouseMainActivity.this, EveryHouseOrderSendActivity.class),
                        REQUEST_CODE_SEND_ORDER);
            }
        });
    }
}
