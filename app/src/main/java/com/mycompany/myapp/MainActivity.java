package com.mycompany.myapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new StartupHelper().onStartup(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView buildTimeView = (TextView) findViewById(R.id.build_time);
        buildTimeView.setText("Build time: " + BuildConfig.BUILD_TIME);

        TextView gitShaView = (TextView) findViewById(R.id.git_sha);
        gitShaView.setText("Git SHA: " + BuildConfig.GIT_SHA);
    }
}
