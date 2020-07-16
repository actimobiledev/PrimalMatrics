package com.primalmatrics.utils;

import com.android.volley.VolleyError;

public interface CallBacks {
    public void onSuccess(String response, int requestCode);
    public void onError(VolleyError error);
}
