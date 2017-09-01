package com.grachro.chinapush.jpushminimum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("jPushNotification");

        JPushReceivedNotification received = new JPushReceivedNotification(bundle);
        Log.i("NotificationActivity", received.toString());



        TextView textView = (TextView) this.findViewById(R.id.textView);
        textView.setText(received.toString());
    }
}
