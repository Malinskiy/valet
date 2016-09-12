package com.malinskiy.valet;

import com.malinskiy.valet.model.Blacklist;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Valet implements IXposedHookLoadPackage {

    public static final String TAG = Valet.class.getSimpleName();

    @Override public void handleLoadPackage(XC_LoadPackage.LoadPackageParam packageParam) throws Throwable {
        Log.i(TAG, "handleLoadPackage for " + packageParam.packageName);

        //TODO: double check that com.android.providers.settings is found on most Android devices
        if ("com.android.providers.settings".equals(packageParam.packageName)) {
            new ValetActivityController(new Blacklist()).install();
        }
    }
}
