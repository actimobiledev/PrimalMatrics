package com.primalmatrics.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class AppDataPref {
    public static final String INTRO_SCREEN = "intro_screen";
    public static String HOME_PROMOTIONS = "home_promotions";
    public static String HOME_MY_PROFILE = "home_my_profile";
    public static String LOGIN_KEY = "login_key";
    public static String FIRE_BASE_ID = "fire_base_id";
    public static String LOGIN_TYPE = "login_type";
    public static String FARM_FIELD = "farm_Field";
    public static String STATUS = "status";
    private static AppDataPref appDataPref;
    private String APP_DATA = "SFS_APP_DATA";

    public static AppDataPref getInstance() {
        if (appDataPref == null)
            appDataPref = new AppDataPref();
        return appDataPref;
    }

    private SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(APP_DATA, Context.MODE_PRIVATE);
    }

    public String getStringPref(Context context, String key) {
        return getPref(context).getString(key, "");
    }

    public int getIntPref(Context context, String key) {
        return getPref(context).getInt(key, 0);
    }

    public boolean getBooleanPref(Context context, String key) {
        return getPref(context).getBoolean(key, false);
    }

    public void putBooleanPref(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putStringPref(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putIntPref(Context context, String key, int value) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }
}
