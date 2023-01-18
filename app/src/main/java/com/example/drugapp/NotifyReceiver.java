package com.example.drugapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class NotifyReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        NotificationMed nm = new NotificationMed(context);
        NotificationCompat.Builder nb = nm.getChannelNotification();
        nm.getManager().notify(1, nb.build());
    }
}

