package com.example.max.chatapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.max.chatapplication.R;
import com.example.max.chatapplication.other_classes.SamplePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HelloAppActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<View>();

        View page = inflater.inflate(R.layout.hello_layoutone, null);
        pages.add(page);

        page = inflater.inflate(R.layout.hello_layouttwo, null);
        pages.add(page);

        page = inflater.inflate(R.layout.hello_layoutthree, null);
        pages.add(page);

        page = inflater.inflate(R.layout.hello_layoutthfou, null);
        pages.add(page);

        page = inflater.inflate(R.layout.hello_layoutfour, null);
        pages.add(page);

        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        setContentView(viewPager);
    }

    public void goToMenu(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
