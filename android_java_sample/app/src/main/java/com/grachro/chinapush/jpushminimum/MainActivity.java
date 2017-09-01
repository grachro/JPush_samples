package com.grachro.chinapush.jpushminimum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        String registrationId = JPushInterface.getRegistrationID(this);

        Log.i("MainActivity", registrationId);

        TextView textView = (TextView) this.findViewById(R.id.registrationId);
        textView.setText("registrationId=" + registrationId);
    }
}
