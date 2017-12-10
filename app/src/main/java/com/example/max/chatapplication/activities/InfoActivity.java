package com.example.max.chatapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.max.chatapplication.R;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_programm);
    }

    public void backToMenuFromInfo(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
