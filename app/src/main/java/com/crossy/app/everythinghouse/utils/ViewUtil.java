package com.crossy.app.everythinghouse.utils;

import android.app.ActionBar;
import android.view.View;

/**
 * Created by Atomu on 2014/10/18.
 */
public class ViewUtil {
    private static String TAG = "ViewUtil";

    public static void customizeActionBar(ActionBar actionBar, int resId) {
        actionBar.setCustomView(resId);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    /**
     * 获取焦点, 调整布局
     */
    static public void requestFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.requestFocusFromTouch();
    }

}
