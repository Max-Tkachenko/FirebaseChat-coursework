package com.example.max.chatapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.max.chatapplication.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        final Intent intent = new Intent(this, HelloAppActivity.class);

        Thread splash_time = new Thread() {

            public void run() {
                try {
                    int SplashTimer = 0;
                    while (SplashTimer < 6500) {
                        sleep(100);
                        SplashTimer = SplashTimer + 100;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                    startActivity(intent);
                }

            }
        };
        splash_time.start();
    }
}
