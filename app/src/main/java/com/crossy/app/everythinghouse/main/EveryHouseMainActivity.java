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
import android.widget.IconTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.orders.send.EveryHouseOrderSendActivity;
import com.crossy.app.everythinghouse.utils.StickyScrollView;
import com.crossy.app.everythinghouse.utils.ViewUtil;
import com.crossy.app.everythinghouse.utils.launch.TabHostActivity;
import com.crossy.app.everythinghouse.utils.message.EveryHouseMessageListActivity;
import com.crossy.app.everythinghouse.utils.pulltozoomview.PullToZoomScrollViewEx;
import com.crossy.app.everythinghouse.utils.settings.SettingsActivity;
import com.crossy.app.everythinghouse.utils.user.EveryHouseUserActivity;

public class EveryHouseMainActivity extends Activity {

    private String TAG = "ljj";

    private final int REQUEST_CODE_SEND_ORDER = 1000;

    private PullToZoomScrollViewEx scrollViewEx;
    private IconTextView textViewMessage;
    private IconTextView textViewSetting;
    private IconTextView textViewAccount;

    private StickyScrollView stickyScrollView;
    private IconTextView textViewMyEntrustMore;
    private IconTextView textViewMyOrderMore;
    private IconTextView textViewMyCommentMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_house_main);

        initView();

        registerListener();

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
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollViewEx.setHeaderLayoutParams(localObject);

        textViewMessage = (IconTextView)scrollViewEx.getHeaderView().findViewById(R.id.textViewMessage);
        textViewSetting = (IconTextView)scrollViewEx.getHeaderView().findViewById(R.id.textViewSetting);
        textViewAccount = (IconTextView)scrollViewEx.getHeaderView().findViewById(R.id.textViewAccount);

        stickyScrollView = (StickyScrollView)scrollViewEx.getRootView().findViewById(R.id.stickyScrollView);

        textViewMyEntrustMore = (IconTextView)scrollViewEx.getRootView().findViewById(R.id.textViewMyEntrustMore);
        textViewMyOrderMore = (IconTextView)scrollViewEx.getRootView().findViewById(R.id.textViewMyOrderMore);
        textViewMyCommentMore = (IconTextView)scrollViewEx.getRootView().findViewById(R.id.textViewMyCommentMore);

    }

    private void registerListener(){
        textViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EveryHouseMainActivity.this, EveryHouseMessageListActivity.class));
            }
        });
        textViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EveryHouseMainActivity.this, SettingsActivity.class));
            }
        });
        textViewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EveryHouseMainActivity.this, EveryHouseUserActivity.class));
            }
        });
        textViewMyEntrustMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        textViewMyOrderMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        textViewMyCommentMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
