package com.example.drugapp;





import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import java.util.Calendar;

import de.fhbielefeld.swe.medikamentenapp.R;

public class NotificationMed extends ContextWrapper {
    public static final String channelID = "Erinnerung";
    public static final String channelName = "Erinnerung Medikament einzunehmen";

    private NotificationManager mManager;

    public NotificationMed(Context base)
    {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification()
    {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Erinnerung!")
                .setContentText("Sie müssen Ihre Medizin einnehmen!")
                .setSmallIcon(R.drawable.ic_stat_name);
    }

    public void setAlarm(int hour, int min)
    {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, min);
        c.set(Calendar.SECOND, 0);

        //startAlarm(c);
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotifyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        //am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pendingIntent );
        am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        //Toast.makeText(this, "Benachrichtigung eingestellt", Toast.LENGTH_SHORT).show();
    }
}
