package com.primalmatrics.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class CompanyDetailPref {
    public static String COMPANY_NAME = "company_name";
    public static String COMPANY_EMAIL = "company_email";
    public static String COMPANY_CONTACT = "company_contact";
    public static String COMPANY_ADDRESS = "company_address";
    public static String HOME_PAGE = "home_page";
    public static String ABOUT_US = "about_us";

    private static CompanyDetailPref appDataPref;
    private String APP_DATA = "SFS_APP_DATA";

    public static CompanyDetailPref getInstance() {
        if (appDataPref == null)
            appDataPref = new CompanyDetailPref();
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
