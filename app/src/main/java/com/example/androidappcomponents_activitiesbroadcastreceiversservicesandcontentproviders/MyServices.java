package com.example.androidappcomponents_activitiesbroadcastreceiversservicesandcontentproviders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_services);
    }

    public void stop_music(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        stopService(intent);
    }

    public void play_music(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);
    }
}