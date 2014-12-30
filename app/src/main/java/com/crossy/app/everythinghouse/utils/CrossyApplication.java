package com.crossy.app.everythinghouse.utils;

import android.app.Application;

/**
 * Created by ljj on 2014/11/29.
 */
public class CrossyApplication extends Application {

    private static CrossyApplication instance;

    public static void init(Application application){
        instance = (CrossyApplication) application;
    }

    public static CrossyApplication getInstance(){
        return instance;
    }
}
