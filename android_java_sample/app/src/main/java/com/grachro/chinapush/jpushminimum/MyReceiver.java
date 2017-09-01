package com.grachro.chinapush.jpushminimum;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

public class MyReceiver extends BroadcastReceiver {

    private static void logInfo(Object o) {
        Log.i("MyReceiver", o == null ? "null" : o.toString());
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String intentAction = intent.getAction();


        if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intentAction)) {
            Bundle bundle = intent.getExtras();
            JPushReceivedNotification received = new JPushReceivedNotification(bundle);
            logInfo("action is ACTION_NOTIFICATION_RECEIVED::" + received);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intentAction)) {
            Bundle bundle = intent.getExtras();
            JPushReceivedNotification received = new JPushReceivedNotification(bundle);
            logInfo("action is ACTION_NOTIFICATION_OPENED::" + received);

            Intent nextIntent = new Intent(context, NotificationActivity.class);
            nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
            nextIntent.putExtra("jPushNotification", bundle);

            context.startActivity(nextIntent);

        } else {
            logInfo("action is " + intentAction);
        }

    }

}
