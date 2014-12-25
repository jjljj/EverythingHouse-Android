package com.crossy.app.everythinghouse.utils.launch;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.crossy.app.everythinghouse.R;
import com.crossy.app.everythinghouse.acts.EveryHouseActsActivity;
import com.crossy.app.everythinghouse.main.EveryHouseMainActivity;
import com.crossy.app.everythinghouse.orders.EveryHouseOrderActivity;

public class TabHostActivity extends TabActivity {

    private final int TAB_SPEC_NUM = 3;

    public static final String CURRENT_TAB = "current_tab";
    private static final String[] TAB_SPEC = new String[]{"main","order","act"};
    private static final String[] TAB_SPEC_NAME = new String[]{"首页","接单","活动"};
    private Class[] activities = new Class[]
            {EveryHouseMainActivity.class, EveryHouseOrderActivity.class, EveryHouseActsActivity.class};
    private static final int[] drawablesSelected = new int[]{R.drawable.everything_house_tab_main_selected,
            R.drawable.everything_house_tab_order_selected,R.drawable.everything_house_tab_acts_selected};
    private static final int[] drawablesUnselected = new int[]{R.drawable.everything_house_tab_main_unselected,
            R.drawable.everything_house_tab_order_unselected,R.drawable.everything_house_tab_acts_unselected};

    public static ActionBar actionBar;

    //Tabs
    private TabHost tabHost;
    private int currentTab = 0;
    private TabHost.TabSpec[] tabSpec = new TabHost.TabSpec[TAB_SPEC_NUM];
    private ImageView[] imageView = new ImageView[TAB_SPEC_NUM];
    private TextView[] textView = new TextView[TAB_SPEC_NUM];
    private View[] indicator = new View[TAB_SPEC_NUM];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        currentTab = getIntent().getIntExtra(CURRENT_TAB, 0);

        actionBar = getActionBar();

        initTabs();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //It seems the index activity and the content activity received the keydown action together.
        //So I banned the onKedDown of Backforward here to make index activity silent.
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initTabs() {
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        if(tabHost!=null){
            Log.i("ljj","null");
        }

        for(int i=0;i<TAB_SPEC_NUM;++i){
            indicator[i] = getLayoutInflater().inflate(R.layout.item_tab_index,null);
            imageView[i] = (ImageView)indicator[i].findViewById(R.id.iv_tab_icon);
            textView[i] = (TextView)indicator[i].findViewById(R.id.tv_tab_title);

            textView[i].setText(TAB_SPEC_NAME[i]);

            tabSpec[i] = tabHost.newTabSpec(TAB_SPEC[i])
                    .setIndicator(indicator[i])
                    .setContent(new Intent(TabHostActivity.this, activities[i]));
            tabHost.addTab(tabSpec[i]);
        }

        initUnselected();

        if(currentTab!=0){
            tabHost.setCurrentTab(currentTab);
        }

        imageView[currentTab].setImageResource(drawablesSelected[currentTab]);
        textView[currentTab].setTextColor(getResources().getColor(R.color.app_color));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                initUnselected();
                for(int i=0;i<TAB_SPEC_NUM;++i){
                    if(!s.equals(TAB_SPEC[i])){
                        continue;
                    }
                    imageView[i].setImageResource(drawablesSelected[i]);
                    textView[i].setTextColor(getResources().getColor(R.color.app_color));
                }
            }
        });
    }

    private void initUnselected(){
        for(int i=0;i<TAB_SPEC_NUM;++i){
            imageView[i].setImageResource(drawablesUnselected[i]);
            textView[i].setTextColor(getResources().getColor(R.color.app_color_gray));
        }
    }
}
