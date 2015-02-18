package com.mycompany.myapp;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import static android.os.PowerManager.ACQUIRE_CAUSES_WAKEUP;
import static android.os.PowerManager.FULL_WAKE_LOCK;
import static android.os.PowerManager.ON_AFTER_RELEASE;
import static android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

public class StartupHelper {
    public void onStartup(Activity activity) {
        riseAndShine(activity);
    }

    // https://gist.github.com/JakeWharton/f50f3b4d87e57d8e96e9
    // https://github.com/andnexus/android-testing/commit/b75ee2cbe312aa9eeeb3e28bba14d979e96eef7d
    private void riseAndShine(Activity activity) {
        KeyguardManager keyguardManager = (KeyguardManager) activity.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardLock keyguardLock = keyguardManager.newKeyguardLock(MainActivity.class.getSimpleName());
        keyguardLock.disableKeyguard();

        activity.getWindow().addFlags(FLAG_SHOW_WHEN_LOCKED);

        PowerManager power = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
        WakeLock wakeLock = power.newWakeLock(FULL_WAKE_LOCK | ACQUIRE_CAUSES_WAKEUP | ON_AFTER_RELEASE, "wakeup!");
        wakeLock.acquire();
        wakeLock.release();
    }
}
