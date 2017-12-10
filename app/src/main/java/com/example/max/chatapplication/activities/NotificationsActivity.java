package com.example.max.chatapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.max.chatapplication.R;
import com.example.max.chatapplication.classes.Notification;
import com.example.max.chatapplication.classes.User;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class NotificationsActivity extends Activity {

    private FirebaseListAdapter<Notification> adapterNotes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_layout);

        displayNotes();
    }

    public void backToMenuFromNote(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void sendNote(View view){
        EditText textStatus = (EditText) findViewById(R.id.editText3);
        EditText text = (EditText) findViewById(R.id.editText4);

        FirebaseDatabase.getInstance().getReference("/notifications").push()
                .setValue(new Notification(text.getText().toString(), getIntent().getStringExtra("nameForNote"),
                        textStatus.getText().toString()));
        textStatus.setText("");
        text.setText("");
    }

    public void displayNotes(){
        final ListView listNotes = (ListView) findViewById(R.id.listOfNotes);
        adapterNotes = new FirebaseListAdapter<Notification>(this, Notification.class, R.layout.item_note, FirebaseDatabase.getInstance().getReference("/notifications")) {
            @Override
            protected void populateView(View v, Notification model, int position) {

                TextView status, author, text;
                status = (TextView)v.findViewById(R.id.statusNote);
                author = (TextView)v.findViewById(R.id.timeNote);
                text = (TextView)v.findViewById(R.id.textNote);

                String time = (DateFormat.format("(HH:mm)", model.getTime())).toString();

                status.setText(model.getStatus());
                author.setText(model.getAuthor() + " " + time);
                text.setText("       " + model.getText());
            }
        };
        listNotes.setAdapter(adapterNotes);
    }
}
