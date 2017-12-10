package com.example.max.chatapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.max.chatapplication.R;
import com.example.max.chatapplication.classes.ChatMessage;
import com.example.max.chatapplication.other_classes.GoogleSearch;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends Activity{

    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<ChatMessage> adapter;
    public LinearLayout chat_activity;
    private FloatingActionButton buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        buttonSend = (FloatingActionButton)findViewById(R.id.btnSend);
        chat_activity = (LinearLayout)findViewById(R.id.chat_activity);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.editText);
                String textMessage = input.getText().toString();

                if(textMessage.length() == 0){
                    Snackbar.make(view, "Введіть текст повідомлення!", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    FirebaseDatabase.getInstance().getReference("/messages").push()
                            .setValue(new ChatMessage(textMessage, getIntent().getStringExtra("name")));
                    input.setText("");
                }
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .build(), SIGN_IN_REQUEST_CODE);
        } else {
            displayChat();
        }
    }

    private void displayChat() {

        final ListView listMessages = (ListView)findViewById(R.id.listView);
        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.item, FirebaseDatabase.getInstance().getReference("/messages")) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {

                TextView textMessage, author, timeMessage;
                textMessage = (TextView)v.findViewById(R.id.tvMessage);
                author = (TextView)v.findViewById(R.id.tvAuthor);
                timeMessage = (TextView)v.findViewById(R.id.tvTime);

                textMessage.setText(model.getTextMessage());
                if(model.getAuthor() == getIntent().getStringExtra("name")){
                    textMessage.setBackgroundResource(R.drawable.bubble_a);
                    textMessage.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                else {
                    textMessage.setBackgroundResource(R.drawable.bubble_b);
                    textMessage.setTextColor(getResources().getColor(R.color.friendChatText));
                }
                author.setText(model.getAuthor());
                timeMessage.setText(DateFormat.format("(HH:mm)", model.getTimeMessage()));
            }
        };
        listMessages.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                Snackbar.make(chat_activity, "Вход выполнен", Snackbar.LENGTH_SHORT).show();
                displayChat();
            } else {
                Snackbar.make(chat_activity, "Вход не выполнен", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void backToMenuActivity(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void openGoogleSearch(View view){
        Intent intent = new Intent(this, GoogleSearch.class);
        startActivity(intent);
    }

    public void openCamera(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CAMERA_BUTTON);
        intent.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_CAMERA));
        sendOrderedBroadcast(intent, null);
    }

    public void openPhone(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }
}