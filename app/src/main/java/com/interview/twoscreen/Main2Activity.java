package com.interview.twoscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.interview.twoscreen.apis.User;

public class Main2Activity extends AppCompatActivity {

    private TextView phoneTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        phoneTextview = findViewById(R.id.phone_textview);

        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("user");

//        Toast.makeText(this,user.email,Toast.LENGTH_SHORT).show();

        phoneTextview.setText(user.phone);

    }
}
