package com.example.max.chatapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.max.chatapplication.R;
import com.example.max.chatapplication.classes.User;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class ViewFriendsActivity extends Activity{

    private FirebaseListAdapter<User> adapterUsers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_activity);

        displayFriends();
    }

    public void backToMenuFromFriends(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void displayFriends(){
        final ListView listUsers = (ListView)findViewById(R.id.listFriends);
        adapterUsers = new FirebaseListAdapter<User>(this, User.class, R.layout.friend_item, FirebaseDatabase.getInstance().getReference("/users")) {
            @Override
            protected void populateView(View v, User model, int position) {

                TextView name, age, city;
                name = (TextView)v.findViewById(R.id.viewName);
                age = (TextView)v.findViewById(R.id.viewAge);
                city = (TextView)v.findViewById(R.id.viewCity);

                name.setText("  " + model.getName() + " " + model.getSurname());
                age.setText(" (вік - " + model.getYears() + ")");
                city.setText("  " + model.getCountry() + ", " + model.getCity());
            }
        };
        listUsers.setAdapter(adapterUsers);
    }
}
