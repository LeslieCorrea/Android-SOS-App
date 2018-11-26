package com.example.lesliecorrea.sosapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

    //interface
    private static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for(int i=0;i<pdus.length;i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String messageBody = smsMessage.getMessageBody();

            boolean isFound = messageBody.toLowerCase().indexOf("sos") !=-1? true: false;

            boolean running = MainActivity.IsRunning;

            if(isFound && !running) {
                Intent in = new Intent();
                in.putExtra("Message",""+messageBody);
                in.setClassName("com.example.lesliecorrea.sosapp", "com.example.lesliecorrea.sosapp.MainActivity");
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(in);
            }else if(isFound){
                //Pass the message text to interface
                mListener.messageReceived(messageBody);
            }
        }
    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }


}