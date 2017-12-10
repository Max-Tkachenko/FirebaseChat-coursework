package com.example.max.chatapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.max.chatapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.max.chatapplication.R.layout.menu_activity;

public class MenuActivity extends Activity{

    TextView name;
    TextView surname;
    TextView years;
    TextView country;
    TextView city;
    CheckBox checkBox;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(menu_activity);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        name = (TextView) findViewById(R.id.nameText);
        surname = (TextView) findViewById(R.id.surnameText);
        years = (TextView) findViewById(R.id.oldText);
        country = (TextView) findViewById(R.id.countryText);
        city = (TextView) findViewById(R.id.cityText);
        imageView = (ImageView) findViewById(R.id.androidImage);

        startProfile();

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    imageView.setImageResource(R.drawable.android_anon);

                    name.setText("ANONYMOUS");
                    surname.setText("*****");
                    years.setText("*****");
                    country.setText("*****");
                    city.setText("*****");
                } else {
                    imageView.setImageResource(R.drawable.android_norm);
                    startProfile();
                }
            }
        });
    }

    public void goToChat(View view){
        Intent intent = new Intent(this, ChatActivity.class);

        intent.putExtra("name", name.getText().toString());
        startActivity(intent);
    }

    public void startProfile(){
        try {
            String filename = "nameFile";
            BufferedReader br0 = new BufferedReader(new InputStreamReader(openFileInput(filename)));
            String strName = "";
            while ((strName = br0.readLine()) != null) {
                name.setText(strName);
            }

            String filesur = "surnameFile";
            BufferedReader br1 = new BufferedReader(new InputStreamReader(openFileInput(filesur)));
            String strSurname = "";
            while ((strSurname = br1.readLine()) != null) {
                surname.setText(strSurname);
            }

            String fileyears = "yearsFile";
            BufferedReader br2 = new BufferedReader(new InputStreamReader(openFileInput(fileyears)));
            String strYears = "";
            while ((strYears = br2.readLine()) != null) {
                years.setText(strYears);
            }

            String filecountry = "countryFile";
            BufferedReader br3 = new BufferedReader(new InputStreamReader(openFileInput(filecountry)));
            String strCountry = "";
            while ((strCountry = br3.readLine()) != null) {
                country.setText(strCountry);
            }

            String filecity = "cityFile";
            BufferedReader br4 = new BufferedReader(new InputStreamReader(openFileInput(filecity)));
            String strCity = "";
            while ((strCity = br4.readLine()) != null) {
                city.setText(strCity);
            }
        }
        catch (Resources.NotFoundException ex) {
        }
        catch (IOException e) {
            name.setText("input name");
            surname.setText("input surname");
            years.setText("input age");
            country.setText("input country");
            city.setText("input city");

            e.printStackTrace();
        }
    }

    public void editProfile(View view){
        if(checkBox.isChecked()){
            Snackbar.make(view, "Ви не можете редагувати профіль в анонімному режимі!", Snackbar.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, EditProfileActivity.class);

            intent.putExtra("nameP", name.getText().toString());
            intent.putExtra("surnameP", surname.getText().toString());
            intent.putExtra("yearsP", years.getText().toString());
            intent.putExtra("countryP", country.getText().toString());
            intent.putExtra("cityP", city.getText().toString());

            startActivity(intent);
        }
    }

    public void viewFriends(View view){
        Intent intent = new Intent(this, ViewFriendsActivity.class);
        startActivity(intent);
    }

    public void viewInfo(View view){
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    public void viewNotifications(View view){
        Intent intent = new Intent(this, NotificationsActivity.class);
        intent.putExtra("nameForNote", name.getText().toString());
        startActivity(intent);
    }
}
