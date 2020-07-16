package com.primalmatrics.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;


import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.primalmatrics.R;
import com.primalmatrics.activity.MainActivity;
import com.primalmatrics.activity.SigninSignupActivity;
import com.primalmatrics.utils.AppConfigTags;
import com.primalmatrics.utils.AppConfigURL;
import com.primalmatrics.utils.CallBacks;
import com.primalmatrics.utils.LoginDetailsPref;
import com.primalmatrics.utils.NetworkConnection;
import com.primalmatrics.utils.Utils;
import com.primalmatrics.utils.VolleyApi;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;


public class LoginFragment extends Fragment implements CallBacks {

    CoordinatorLayout clMain;
    LinearLayout llSignin;



    public static LoginFragment newInstance () {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_signin, container, false);
        initView (rootView);
        initBundle ();
        initData ();
        initAdapter ();
        initListener ();



        return rootView;
    }

    private void initView (View rootView) {
        clMain=(CoordinatorLayout)rootView.findViewById(R.id.clMain);
        llSignin=(LinearLayout) rootView.findViewById(R.id.llSignin);


    }

    private void initData () {


        
    }
    
    private void initBundle () {
        Bundle bundle = this.getArguments ();
    }
    
    private void initListener () {
        llSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
    
    private void initAdapter () {


    }


  /*  private void getApplicationList() {
        if (NetworkConnection.isNetworkAvailable(getActivity())) {
            //  swipeRefreshLayout.setRefreshing(true);
            Utils.showProgressDialog(getActivity(), progressDialog, getResources().getString(R.string.progress_dialog_text_please_wait), true);
            Map<String, String> params = new Hashtable<String, String>();
            params.put(AppConfigTags.COMPANY_ID, loginDetailsPref.getStringPref(getActivity(), LoginDetailsPref.COMPANY_ID));
            params.put(AppConfigTags.USER_ID, loginDetailsPref.getStringPref(getActivity(), LoginDetailsPref.USER_ID));
            params.put(AppConfigTags.CSRF_TOKEN, loginDetailsPref.getStringPref(getActivity(), LoginDetailsPref.CSRF_TOKEN));
            params.put(AppConfigTags.KIND, "Service");
            VolleyApi.getInstance(getActivity()).hitApiPostWithParams(getActivity(), this, AppConfigURL.GET_APPLICATION_LIST, params, AppConfigTags.GET_APPLICATION_LIST_CODE);
        } else {
            Utils.showSnackBar(getActivity(), clMain, getResources().getString(R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_go_to_settings), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent dialogIntent = new Intent(Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
            });
        }
    }
*/

    @Override
    public void onSuccess(String response, int requestCode) {

    /*    switch (requestCode) {
            case AppConfigTags.GET_APPLICATION_LIST_CODE:
                progressDialog.dismiss();
                Utils.showLog(Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                if (response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        boolean error = jsonObj.getBoolean(AppConfigTags.ERROR);
                        String message = jsonObj.getString(AppConfigTags.MESSAGE);
                        if (!error) {
                            applicationLists.clear();

                            JSONArray jsonArray = jsonObj.getJSONArray(AppConfigTags.DATA);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                applicationLists.add(new QCApplicationList(
                                        jsonObject.getString(AppConfigTags.ID),
                                        jsonObject.getString(AppConfigTags.NAME),
                                        jsonObject.getString(AppConfigTags.KIND),
                                        jsonObject.getString(AppConfigTags.STATE))

                                );
                            }

                            if (jsonArray.length() == 0) {
                                llNoResult.setVisibility(View.VISIBLE);
                                rbApplicationList.setVisibility(View.GONE);
                            } else {
                                llNoResult.setVisibility(View.GONE);
                                rbApplicationList.setVisibility(View.VISIBLE);
                            }
                            qcApplicationListAdapter.notifyDataSetChanged();
                        } else {
                            Utils.showSnackBar(getActivity(), clMain, message, Snackbar.LENGTH_LONG, null, null);
                        }
                    } catch (Exception e) {
                        Utils.showSnackBar(getActivity(), clMain, getResources().getString(R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);
                        e.printStackTrace();
                    }
                } else {
                    Utils.showSnackBar(getActivity(), clMain, getResources().getString(R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);
                    Utils.showLog(Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                }


                break;

        }
    }

     */
    }
    @Override
    public void onError(VolleyError error) {
       // progressDialog.dismiss();
        Utils.showSnackBar(getActivity(), clMain, getResources().getString(R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);




    }}