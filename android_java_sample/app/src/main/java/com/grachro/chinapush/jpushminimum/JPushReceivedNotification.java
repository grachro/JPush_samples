package com.grachro.chinapush.jpushminimum;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

class JPushReceivedNotification {

    public final static String LOG_TAG =  "JPushReceivedNotifi";

    private int notificationId;
    private boolean connectionChange;
    private String messsageId;
    private String notificationTitle;
    private String alertType;
    private String alert;
    private String bigText;
    private Map<String, String> extra = new HashMap<>();
    private Map<String, String> more = new HashMap<>();


    public JPushReceivedNotification(Bundle bundle) {


        for(String key:bundle.keySet()) {
            Log.i(LOG_TAG, "bundle key=" + key);
        }


        notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
        connectionChange = bundle.getBoolean(JPushInterface.EXTRA_CONNECTION_CHANGE);
        loadExtra(bundle);


        for (String key : bundle.keySet()) {

            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);

            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                connectionChange = bundle.getBoolean(JPushInterface.EXTRA_CONNECTION_CHANGE);

            } else if (key.equals(JPushInterface.EXTRA_MSG_ID)) {
                messsageId = bundle.getString(JPushInterface.EXTRA_MSG_ID);

            } else if (key.equals(JPushInterface.EXTRA_NOTIFICATION_TITLE)) {
                notificationTitle = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);

            } else if (key.equals(JPushInterface.EXTRA_ALERT_TYPE)) {
                alertType = bundle.getString(JPushInterface.EXTRA_ALERT_TYPE);

            } else if (key.equals(JPushInterface.EXTRA_ALERT)) {
                alert = bundle.getString(JPushInterface.EXTRA_ALERT);

            } else if (key.equals(JPushInterface.EXTRA_BIG_TEXT)) {
                bigText = bundle.getString(JPushInterface.EXTRA_BIG_TEXT);

            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                loadExtra(bundle);
            } else {
                more.put(key, bundle.getString(key));
            }
        }

    }

    private void loadExtra(Bundle bundle) {
        String jsonString = bundle.getString(JPushInterface.EXTRA_EXTRA);
        try {
            JSONObject json = new JSONObject(jsonString);

            for (Iterator<String> it = json.keys(); it.hasNext(); ) {
                String extKey = it.next();
                String val = json.optString(extKey);
                extra.put(extKey, val);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Get message extra JSON error!", e);
        }
    }

    public int getNotificationId() {
        return notificationId;
    }

    public boolean isConnectionChange() {
        return connectionChange;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public Map<String, String> getMore() {
        return more;
    }

    @Override
    public String toString() {
        return "JPushReceivedInfo{" +
                "notificationId=" + notificationId +
                ", connectionChange=" + connectionChange +
                ", messsageId=" + messsageId +
                ", notificationTitle='" + notificationTitle + '\'' +
                ", alertType=" + alertType +
                ", alert='" + alert + '\'' +
                ", bigText='" + bigText + '\'' +
                ", extra=" + extra +
                ", more=" + more +
                '}';
    }
}
