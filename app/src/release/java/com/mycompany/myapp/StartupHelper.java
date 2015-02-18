package com.mycompany.myapp;

import android.app.Activity;
import android.widget.Toast;

public class StartupHelper {
    public void onStartup(Activity activity) {
        Toast.makeText(activity, "Turning on the crash reporter!", Toast.LENGTH_LONG).show();
    }
}
