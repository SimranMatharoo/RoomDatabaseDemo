package com.example.roomdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UpdateTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_update_task);
    }
}