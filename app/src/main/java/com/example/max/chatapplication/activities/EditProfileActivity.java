package com.example.max.chatapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.example.max.chatapplication.R;
import com.example.max.chatapplication.classes.User;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class EditProfileActivity extends Activity {

    EditText name;
    EditText surname;
    EditText years;
    EditText country;
    EditText city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_layout);

        name = (EditText) findViewById(R.id.editNameQ);
        surname = (EditText) findViewById(R.id.editSurnameQ);
        years = (EditText) findViewById(R.id.editYearsQ);
        country = (EditText) findViewById(R.id.editCountryQ);
        city = (EditText) findViewById(R.id.editCityQ);

        name.setText(getIntent().getStringExtra("nameP"));
        surname.setText(getIntent().getStringExtra("surnameP"));
        years.setText(getIntent().getStringExtra("yearsP"));
        country.setText(getIntent().getStringExtra("countryP"));
        city.setText(getIntent().getStringExtra("cityP"));

        name.setSelection(name.getText().length());
        surname.setSelection(surname.getText().length());
        years.setSelection(years.getText().length());
        country.setSelection(country.getText().length());
        city.setSelection(city.getText().length());
    }

    public void backToMenuFromEdit(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void saveChanges(View view){
        try {
            BufferedWriter bwName = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("nameFile", MODE_PRIVATE)));
            bwName.write(name.getText().toString());
            bwName.close();

            BufferedWriter bwSur = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("surnameFile", MODE_PRIVATE)));
            bwSur.write(surname.getText().toString());
            bwSur.close();

            BufferedWriter bwYears = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("yearsFile", MODE_PRIVATE)));
            bwYears.write(years.getText().toString());
            bwYears.close();

            BufferedWriter bwCountry = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("countryFile", MODE_PRIVATE)));
            bwCountry.write(country.getText().toString());
            bwCountry.close();

            BufferedWriter bwCity = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("cityFile", MODE_PRIVATE)));
            bwCity.write(city.getText().toString());
            bwCity.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseDatabase.getInstance().getReference("users/").push()
                .setValue(new User(name.getText().toString(), surname.getText().toString(),
                        years.getText().toString(), country.getText().toString(), city.getText().toString()));

        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
