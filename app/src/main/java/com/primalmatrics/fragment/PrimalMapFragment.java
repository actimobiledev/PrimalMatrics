package com.primalmatrics.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.primalmatrics.R;
import com.primalmatrics.dialogFragment.QuestDetailDialogFragment;
import com.primalmatrics.utils.AppConfigTags;
import com.primalmatrics.utils.CallBacks;
import com.primalmatrics.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PrimalMapFragment extends Fragment implements CallBacks {
    CoordinatorLayout clMain;
    LinearLayout llMain;
    FloatingActionButton fbAddMore;
    int count=1;
    View addView;
    private int mYear, mMonth, mDay;
    String date = "";
    TextView tvStartDate;
    TextView tvEndDate;



    public static PrimalMapFragment newInstance(boolean b) {
        PrimalMapFragment fragment = new PrimalMapFragment();
        Bundle args = new Bundle();
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_primal_map, container, false);
        initView (rootView);
        initBundle ();
        initData ();
        initAdapter ();
        initListener ();
        getDynamicView(1);



        return rootView;
    }

    private void initView (View rootView) {
        clMain=(CoordinatorLayout)rootView.findViewById(R.id.clMain);
        llMain=(LinearLayout)rootView.findViewById(R.id.llMain);
        fbAddMore=(FloatingActionButton)rootView.findViewById(R.id.fbAddMore);
        tvStartDate=(TextView) rootView.findViewById(R.id.tvStartDate);
        tvEndDate=(TextView) rootView.findViewById(R.id.tvEndDate);

    }

    private void initData () {


        
    }
    
    private void initBundle () {
        Bundle bundle = this.getArguments ();
    }
    
    private void initListener () {
        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickupDate(tvStartDate);
            }
        });
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickupDate(tvEndDate);
            }
        });
        fbAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list=new ArrayList<>();
                list.add("Add Goal");
                list.add("Add Quest");
                new MaterialDialog.Builder(getActivity())
                        .items(list)
                        .title("Select Type")
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which){
                                    case 0:
                                        count=count+1;
                                         getDynamicView(count);
                                        break;
                                    case 1:
                                        Bundle bundle = new Bundle();
                                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                        QuestDetailDialogFragment questDetailDialogFragment = QuestDetailDialogFragment.newInstance(bundle);
                                        questDetailDialogFragment.setOnDialogResultListener(new QuestDetailDialogFragment.OnDialogResultListener() {
                                            @Override
                                            public void onPositiveResult() {
                                            }
                                            @Override
                                            public void onNegativeResult() {
                                            }
                                        });
                                        questDetailDialogFragment.show(ft, "");
                                        questDetailDialogFragment.setArguments(bundle);
                                        dialog.dismiss();

                                        break;
                                }
                            }
                        })
                        .show();
            }
        });


    }
    
    private void initAdapter () {


    }

    private void getDynamicView(int count) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addView = layoutInflater.inflate(R.layout.dynamic_view_primal_map, null);
        layoutParams.setMargins(0, 50, 0, 0);

        EditText etGoal = (EditText) addView.findViewById(R.id.etGoal);
        EditText etHabit = (EditText) addView.findViewById(R.id.etHabit);
        EditText etWeight = (EditText) addView.findViewById(R.id.etWieght);
        EditText etTarget = (EditText) addView.findViewById(R.id.etTarget);








        addView.setId(count);
        llMain.addView(addView, layoutParams);


       /* ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llMain.removeView((View) view.getParent());
            }
        });*/






    }

    private void pickupDate(final TextView etPickupDate) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                etPickupDate.setText(String.format("%02d", monthOfYear + 1) + "/" + String.format("%02d", dayOfMonth) + "/" + year);
                date = etPickupDate.getText().toString().trim();
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
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