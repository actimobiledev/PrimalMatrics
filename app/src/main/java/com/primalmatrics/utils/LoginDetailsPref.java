package com.primalmatrics.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class LoginDetailsPref {
    public static String LOGIN_ID = "log_id";
    public static String USER_ID = "user_id";
    public static String LOGIN_NAME = "name";
    public static String ROLE = "role";
    public static String COMPANY_NAME = "company_name";
    public static String COMPANY_ID = "company_id";
    public static String CSRF_TOKEN = "csrf_token";
    public static String VENDOR_FIREBASE_ID = "firebase_id";
    
    private static LoginDetailsPref loginDetailsPref;
    private String SFS_DETAILS = "SFS_DETAILS";

    public static LoginDetailsPref getInstance() {
        if (loginDetailsPref == null)
            loginDetailsPref = new LoginDetailsPref();
        return loginDetailsPref;
    }

    private SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(SFS_DETAILS, Context.MODE_PRIVATE);
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
