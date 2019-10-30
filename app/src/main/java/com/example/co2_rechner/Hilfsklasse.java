package com.example.co2_rechner;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

public class Hilfsklasse {

    public static boolean wirdIntentUnterstuetzt(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = intent.resolveActivity(packageManager);

        if (componentName == null) {
            Log.w("TAG4LOGGING",
                    "Nicht-unterstützer Intent: ACTION=" + intent.getAction() +
                            ", DATA=" + intent.getDataString());

            return false;
        } else {
            return false;
        }


    }
}
