package com.hofo.oknetlib.dialog;

import android.app.Activity;
import android.app.ProgressDialog;

import java.lang.ref.SoftReference;

public class DialogManager {

    private ProgressDialog mProgressDialog;
    private SoftReference<Activity> mActivitySoftReference;

    private DialogManager() {
    }

    public static DialogManager getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public ProgressDialog getLoadingDialog(Activity activity) {
        if (activity != null) {
            if (mActivitySoftReference == null) {
                mActivitySoftReference = new SoftReference<>(activity);
                initDialog();
            } else if (mActivitySoftReference.get() != activity) {
                initDialog();
            }
        }
        return mProgressDialog;
    }


    private void initDialog() {
        Activity activitySoftReference;

        if (mActivitySoftReference != null && (activitySoftReference = mActivitySoftReference.get()) != null) {
            mProgressDialog = new ProgressDialog(activitySoftReference);
            mProgressDialog.setMessage("正在加载...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
    }


    private static class SingletonInstance {
        private static final DialogManager INSTANCE = new DialogManager();
    }
}