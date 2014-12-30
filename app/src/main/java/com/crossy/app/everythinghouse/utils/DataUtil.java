package com.crossy.app.everythinghouse.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.crossy.app.everythinghouse.utils.api.API_SPF;

/**
 * Created by ljj on 2014/11/29.
 * 数据工具：
 * 1.SharePreference对简单数据的存储的封装
 */
public class DataUtil {
    private static CrossyApplication application;

    private static SharedPreferences getSharedPreferences(String spfName) {
        return application.getSharedPreferences(spfName, Context.MODE_PRIVATE);
    }

    public static void init() {
        application = CrossyApplication.getInstance();
    }

    public static String getString(String spfName, String key) {
        return getSharedPreferences(spfName).getString(key, "");
    }

    public static String getString(String spfName, String key, String defaultValue) {
        return getSharedPreferences(spfName).getString(key, defaultValue);
    }

    public static long getLong(String spfName, String key, long defValue) {
        return getSharedPreferences(spfName).getLong(key, defValue);
    }

    public static boolean getBoolean(String spfName, String key, boolean defVal) {
        return getSharedPreferences(spfName).getBoolean(key, defVal);
    }

    /**
     * 下面这种成对接口主要是同步异步的差别, force后缀的是立即生效
     */
    public static void putString(String spfName, String key, String value) {
        getSharedPreferences(spfName).edit().putString(key, value).apply();
    }

    public static void putStringForce(String spfName, String key, String value) {
        getSharedPreferences(spfName).edit().putString(key, value).commit();
    }

    public static void putLong(String spfName, String key, long value) {
        getSharedPreferences(spfName).edit().putLong(key, value).apply();
    }

    public static void putLongForce(String spfName, String key, long value) {
        getSharedPreferences(spfName).edit().putLong(key, value).commit();
    }

    public static void putBoolean(String spfName, String key, boolean value) {
        getSharedPreferences(spfName).edit().putBoolean(key, value).apply();
    }

    public static void putBooleanForce(String spfName, String key, boolean value) {
        getSharedPreferences(spfName).edit().putBoolean(key, value).commit();
    }

    public static void clearAll() {
        clearSpf(API_SPF.NAME_DEFAULT);
        clearSpf(API_SPF.NAME_USER);
    }

    public static void clearSpf(String spfName) {
        getSharedPreferences(spfName).edit().clear().commit();
    }
}
