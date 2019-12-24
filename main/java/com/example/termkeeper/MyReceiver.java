package com.example.termkeeper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {
    static int notificationId;
    String channel_id="test";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("key"), Toast.LENGTH_LONG);

        createNotificationChannel(context, channel_id);
        Notification notification = new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("key"))
                .setContentTitle(intent.getStringExtra("key2")).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId++,notification);



        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

    }

    private void createNotificationChannel(Context context, String channel_id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "notification channel";
            String description = "notification channel description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channel_id, name, importance);
            channel.setDescription(description);
            //Register channel with the system
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

    }
}
