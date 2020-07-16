package com.primalmatrics.dialogFragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;

import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.primalmatrics.R;
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


public class QuestDetailDialogFragment extends DialogFragment implements CallBacks {
    OnDialogResultListener onDialogResultListener;
    private CoordinatorLayout clMain;
    private ImageView ivCancel;
    private TextView tvTitle;








    public static QuestDetailDialogFragment newInstance(Bundle bundle) {
        QuestDetailDialogFragment fragment = new QuestDetailDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        Window window = getDialog().getWindow();
        window.getAttributes().windowAnimations = R.style.DialogAnimation;

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_fragment_quest_detail, container, false);
        initView(root);
        initData();
        initBundle();
        initListener();

        return root;
    }

    private void initView(View rootView) {
        clMain = (CoordinatorLayout)rootView.findViewById( R.id.clMain );
        ivCancel = (ImageView)rootView.findViewById( R.id.ivCancel );
        tvTitle = (TextView)rootView.findViewById( R.id.tvTitle );

    }

    private void initBundle() {
        Bundle bundle = this.getArguments();




    }

    private void initData() {

    }

    private void initListener() {
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

    }

   /* public boolean validation() {
        boolean isTrue = true;

        if (etNewPassword == null || TextUtils.isEmpty(etNewPassword.getText().toString().trim())) {
            etNewPassword.requestFocus();
            etNewPassword.setError(getResources().getString(R.string.please_enter_new_password));
            isTrue = false;
        }
        if (etConfirmPassword == null || TextUtils.isEmpty(etConfirmPassword.getText().toString().trim())) {
            etConfirmPassword.requestFocus();
            etConfirmPassword.setError(getResources().getString(R.string.please_enter_password));
            isTrue = false;
        }
        if (!TextUtils.isEmpty(etNewPassword.getText().toString().trim()) && !TextUtils.isEmpty(etConfirmPassword.getText().toString().trim())) {
            if (!etNewPassword.getText().toString().trim().equals(etConfirmPassword.getText().toString().trim())) {
                etNewPassword.requestFocus();
                etNewPassword.setError(getResources().getString(R.string.password_not_matched));
                etConfirmPassword.requestFocus();
                etConfirmPassword.setError(getResources().getString(R.string.password_not_matched));
                Utils.showToast(getActivity(), getResources().getString(R.string.password_not_matched), true);
                isTrue = false;
            }
        }

        return isTrue;
    }*/




    public void setOnDialogResultListener(OnDialogResultListener listener) {
        this.onDialogResultListener = listener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDialogResultListener != null) {
            // onDialogResultListener.onNegativeResult();
            onDialogResultListener.onPositiveResult();
            dialog.cancel();
        }
    }

    @Override
    public void onSuccess(String response, int requestCode) {
        switch (requestCode) {




        }
    }

    @Override
    public void onError(VolleyError error) {

        if (getActivity() != null && isAdded())
            Utils.showSnackBar(getActivity(), clMain, getResources().getString(R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);
    }


    public interface OnDialogResultListener {
        public abstract void onPositiveResult();

        public abstract void onNegativeResult();
    }


}