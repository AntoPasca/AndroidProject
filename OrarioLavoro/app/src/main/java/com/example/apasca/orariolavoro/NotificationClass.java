package com.example.apasca.orariolavoro;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class NotificationClass extends BroadcastReceiver {

    private final String CHANNEL_ID = "com.example.apasca.orariolavoro.notify";
    public Bundle extras;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        extras = intent.getExtras();
        PendingIntent contentIntent = PendingIntent.getActivity( context, 0,
                new Intent( context, Main.class ), 0 );
        NotificationCompat.Builder notification = new NotificationCompat.Builder( context, CHANNEL_ID )
                .setChannelId( CHANNEL_ID )
            .setSmallIcon( R.drawable.smallicon )
            .setContentTitle( "Orario di uscita " + extras.getString( "orauscitaH" ) +":" + extras.getString( "orauscitaM" ) )
            .setContentText( "Mancano pochi minuti all'uscita");
        notification.setContentIntent( contentIntent );
        notification.setDefaults( Notification.DEFAULT_SOUND );
        notification.setAutoCancel( true );
        NotificationManager mNotificationManager;
        mNotificationManager = (NotificationManager) context.getSystemService( Context.NOTIFICATION_SERVICE );
        mNotificationManager.notify( 1, notification.build() );
    }
}
