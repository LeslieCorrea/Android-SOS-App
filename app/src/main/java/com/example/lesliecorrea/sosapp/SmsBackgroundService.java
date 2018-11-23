package com.example.lesliecorrea.sosapp;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class SmsBackgroundService extends Service {

    private SmsReceiver smsReceiver = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Create an IntentFilter instance.
        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        // Set broadcast receiver priority.
        intentFilter.setPriority(100);

        smsReceiver = new SmsReceiver();

        // Register the broadcast receiver with the intent filter object.
        registerReceiver(smsReceiver,intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

