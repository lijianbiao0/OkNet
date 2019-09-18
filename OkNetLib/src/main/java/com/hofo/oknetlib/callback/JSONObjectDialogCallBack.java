package com.hofo.oknetlib.callback;

import android.app.Activity;
import android.app.ProgressDialog;

import com.hofo.oknetlib.dialog.DialogManager;

public class JSONObjectDialogCallBack extends JSONObjectCallBack {

    private ProgressDialog mProgressDialog;

    public JSONObjectDialogCallBack(Activity activity) {
        mProgressDialog = DialogManager.getInstance().getLoadingDialog(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        mProgressDialog.show();
    }


    @Override
    public void onFinish() {
        super.onFinish();
        mProgressDialog.dismiss();
    }
}
