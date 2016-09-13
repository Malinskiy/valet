package com.malinskiy.valet;

import com.malinskiy.valet.model.Blacklist;

import android.app.IActivityController;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;

import java.lang.reflect.Method;

import de.robv.android.xposed.XposedBridge;


public class ValetActivityController extends IActivityController.Stub {

    private static String TAG = ValetActivityController.class.getSimpleName();
    private final Blacklist blacklist;

    public ValetActivityController(Blacklist blacklist) {
        this.blacklist = blacklist;
    }

    public void install() {
        setActivityController(this);
    }

    public void uninstall() {
        setActivityController(null);
    }

    @Override public boolean activityStarting(Intent intent, String pkg) throws RemoteException {
        Log.i(TAG, "activityStarting: " + pkg + " " + intent.toString());

        return !blacklist.contains(pkg);
    }

    @Override public boolean activityResuming(String pkg) throws RemoteException {
        Log.e(TAG, "activityResuming: " + pkg);

        return !blacklist.contains(pkg);
    }

    @Override public boolean appCrashed(String processName,
                                        int pid,
                                        String shortMsg,
                                        String longMsg,
                                        long timeMillis,
                                        String stackTrace) throws RemoteException {

        Log.e(TAG, "appCrashed: " + processName + ":" + pid + " " + shortMsg + " " + longMsg + " " + stackTrace);
        // return false to prevent the system dialog from appearing
        return false;
    }

    @Override public int appNotResponding(String processName, int pid, String processStats) throws RemoteException {
        Log.e(TAG, "appNotResponding: " + processName + ":" + pid + " " + processStats);
        // return -1 to kill the ANR-ing app immediately and prevent the system dialog from appearing
        return -1;
    }

    @Override public int systemNotResponding(String msg) throws RemoteException {
        Log.e(TAG, "systemNotResponding: " + msg);
        // return -1 to let the system continue with its normal kill
        return -1;
    }

    private void setActivityController(IActivityController activityController) {
        try {
            Class<?> aClass = Class.forName("android.app.ActivityManagerNative");
            Method getDefault = aClass.getMethod("getDefault");
            Object activityManagerInstance = getDefault.invoke(null);
            Method setMethod = activityManagerInstance.getClass().getMethod("setActivityController", IActivityController.class);
            setMethod.invoke(activityManagerInstance, activityController);
            Log.d(TAG, "Installed custom IActivityController");
        } catch (Throwable e) {
            Log.e(TAG, "Failed to install custom IActivityController", e);
        }
    }
}