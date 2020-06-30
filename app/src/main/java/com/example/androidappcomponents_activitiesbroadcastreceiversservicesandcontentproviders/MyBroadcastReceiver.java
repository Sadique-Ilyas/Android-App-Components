package com.example.androidappcomponents_activitiesbroadcastreceiversservicesandcontentproviders;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyBroadcastReceiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_broadcast_receiver);

        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
           int level = intent.getIntExtra("level",0);
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress(level);
            TextView textView = findViewById(R.id.batteryLevel);
            textView.setText("Battery Level "+ level +"%");
        }
    };
}