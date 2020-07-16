package com.primalmatrics.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.primalmatrics.R;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class VolleyApi {

   private CallBacks callBacks;
   private ProgressDialog progressDialog;
   private static VolleyApi volleyApi;
   private Activity activity;


    public VolleyApi(Activity activity){
       this.activity=activity;
      progressDialog=new ProgressDialog(activity);
    }

   public static VolleyApi getInstance(Activity activity){
       if(volleyApi==null) {
            volleyApi = new VolleyApi(activity);
       }
       return volleyApi;
   }



    //call this method when you have to pass raw parameters
    public void hitApiPostwithBody (final Activity activity, final CallBacks callBacks, final String url, final Map<String, String> parameters, final int requestCode, final String body) {
        this.callBacks=null;
        this.callBacks=callBacks;
        if (NetworkConnection.isNetworkAvailable (activity)) {
          //  Utils.showProgressDialog (activity, progressDialog, activity.getResources ().getString (R.string.progress_dialog_text_please_wait), true);
            Utils.showLog (Log.INFO, "" + AppConfigTags.URL,url, true);
            StringRequest strRequest1 = new StringRequest(Request.Method.POST, url,
                    new com.android.volley.Response.Listener<String> () {
                        @Override
                        public void onResponse (String response) {
                            progressDialog.dismiss ();
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                  callBacks.onSuccess(response,requestCode);
                                  //  progressDialog.dismiss ();
                                } catch (Exception e) {
                                   // progressDialog.dismiss ();
                                   // Utils.showSnackBar (activity, clMain, activity.getResources ().getString (R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                    e.printStackTrace ();
                                }
                            } else {
                              //  progressDialog.dismiss ();
                           //     Utils.showSnackBar (OrderDetailActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                            //progressDialog.dismiss();
                        }
                    },
                    new com.android.volley.Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                          //  progressDialog.dismiss ();
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString (), true);
                            callBacks.onError(error);
//                                Utils.showToast(getActivity(), getResources().getString(R.string.snackbar_text_error_occurred), false);
                          //  Utils.showSnackBar (OrderDetailActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                        }
                    }) {
                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String>();
                    params=parameters;
                    Utils.showLog (Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params.toString (), true);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders () throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put (AppConfigTags.HEADER_API_KEY, Constants.api_key);
                    Utils.showLog (Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    Utils.showLog(Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, body, true);
                    return body.getBytes();
                }

                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
            };
            Utils.sendRequest (strRequest1, 60);
        } /*else {
            progressDialog.dismiss ();
//                Utils.showToast(getActivity(), getResources().getString(R.string.snackbar_text_no_internet_connection_available), false);
            Utils.showSnackBar (OrderDetailActivity.this, clMain, getResources ().getString (R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_go_to_settings), new View.OnClickListener () {
                @Override
                public void onClick (View v) {
                    Intent dialogIntent = new Intent (Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity (dialogIntent);
                }
            });
        }*/

    }

    //call this method when you have to pass parameters using params method
    public void hitApiPostWithParams (final Activity activity, final CallBacks callBacks, final String url, final Map<String, String> parameters, final int requestCode) {
        this.callBacks=null;
        this.callBacks=callBacks;
        if (NetworkConnection.isNetworkAvailable (activity)) {
            Utils.showLog (Log.INFO, "" + AppConfigTags.URL,url, true);
            StringRequest strRequest1 = new StringRequest(Request.Method.POST, url,
                    new com.android.volley.Response.Listener<String> () {
                        @Override
                        public void onResponse (String response) {
                            progressDialog.dismiss ();
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    callBacks.onSuccess(response,requestCode);
                                } catch (Exception e) {
                                 //   progressDialog.dismiss ();
                                    // Utils.showSnackBar (activity, clMain, activity.getResources ().getString (R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                    e.printStackTrace ();
                                }
                            } else {
                             //   progressDialog.dismiss ();
                                //     Utils.showSnackBar (OrderDetailActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                            //progressDialog.dismiss();
                        }
                    },
                    new com.android.volley.Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                           // progressDialog.dismiss ();
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString (), true);
                            callBacks.onError(error);
//                                Utils.showToast(getActivity(), getResources().getString(R.string.snackbar_text_error_occurred), false);
                            //  Utils.showSnackBar (OrderDetailActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                        }
                    }) {
                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String>();
                    params=parameters;
                    Utils.showLog (Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params.toString (), true);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders () throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put (AppConfigTags.HEADER_API_KEY, Constants.api_key);
                    Utils.showLog (Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }

            };
            Utils.sendRequest (strRequest1, 60);
        } /*else {
            progressDialog.dismiss ();
//                Utils.showToast(getActivity(), getResources().getString(R.string.snackbar_text_no_internet_connection_available), false);
            Utils.showSnackBar (OrderDetailActivity.this, clMain, getResources ().getString (R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_go_to_settings), new View.OnClickListener () {
                @Override
                public void onClick (View v) {
                    Intent dialogIntent = new Intent (Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity (dialogIntent);
                }
            });
        }*/

    }

    public void hitApiGet(final Activity activity, final CallBacks callBacks, final String url, final int requestCode) {
        this.callBacks=null;
        this.callBacks=callBacks;
        if (NetworkConnection.isNetworkAvailable (activity)) {
            Utils.showProgressDialog (activity, progressDialog, activity.getResources ().getString (R.string.progress_dialog_text_please_wait), true);
            Utils.showLog (Log.INFO, "" + AppConfigTags.URL,url, true);
            StringRequest strRequest1 = new StringRequest(Request.Method.GET, url,
                    new com.android.volley.Response.Listener<String> () {
                        @Override
                        public void onResponse (String response) {
                            progressDialog.dismiss ();
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    callBacks.onSuccess(response,requestCode);
                                } catch (Exception e) {
                                 //   progressDialog.dismiss ();
                                    // Utils.showSnackBar (activity, clMain, activity.getResources ().getString (R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                    e.printStackTrace ();
                                }
                            } else {
                               // progressDialog.dismiss ();
                                //     Utils.showSnackBar (OrderDetailActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }

                        }
                    },
                    new com.android.volley.Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                            //progressDialog.dismiss ();
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString (), true);
                            callBacks.onError(error);
//                                Utils.showToast(getActivity(), getResources().getString(R.string.snackbar_text_error_occurred), false);
                            //  Utils.showSnackBar (OrderDetailActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                        }
                    });
            Utils.sendRequest (strRequest1, 60);
        }
    }

}
