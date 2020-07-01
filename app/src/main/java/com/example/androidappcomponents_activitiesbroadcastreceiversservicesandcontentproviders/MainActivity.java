package com.example.androidappcomponents_activitiesbroadcastreceiversservicesandcontentproviders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_activity(View view)
    {
        Intent intent = new Intent(this,MyActivity.class);
        startActivity(intent);
    }

    public void btn_broadcastReceiver(View view)
    {
        Intent intent = new Intent(this,MyBroadcastReceiver.class);
        startActivity(intent);
    }

    public void btn_services(View view)
    {
        Intent intent = new Intent(this,MyServices.class);
        startActivity(intent);
    }

    public void btn_contentProvider(View view)
    {
        Intent intent = new Intent(this,MyContentProvider.class);
        startActivity(intent);
    }

    public void btn_webService(View view)
    {
        Intent intent = new Intent(this,WebServiceActivity.class);
        startActivity(intent);
    }
}
