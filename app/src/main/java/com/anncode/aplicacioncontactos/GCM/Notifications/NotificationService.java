package com.anncode.aplicacioncontactos.GCM.Notifications;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;

import android.util.Log;

import com.anncode.aplicacioncontactos.MainActivity;
import com.anncode.aplicacioncontactos.Wear.photosActivity;
import com.anncode.recyclerviewfragments.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;
/**
 * Created by isaachernandezquinonez on 12/07/16.
 */
public class NotificationService extends FirebaseMessagingService {
    public static final String TAG = "Firebase";
    public static final int NOTIFICATION_ID = 001;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        Intent i = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,i,PendingIntent.FLAG_ONE_SHOT);
        // tipo de dato que recibe las notificaciones para dirigir cuando sea recibida la notificacion
        Intent iFollow = new Intent();
        iFollow.setAction("FOLLOW");

        Intent iunFollow = new Intent();
        iFollow.setAction("UNFOLLOW");

        Intent iProfile = new Intent(this,MainActivity.class);


        Intent iProfileUserLike = new Intent(this,photosActivity.class);


        PendingIntent pendingIntentFollow = PendingIntent.getActivity(this,0,iFollow,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentunFollow = PendingIntent.getActivity(this,0,iunFollow,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentiProfile = PendingIntent.getActivity(this,0,iProfile,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentiProfileUserLike = PendingIntent.getActivity(this,0,iProfileUserLike,PendingIntent.FLAG_UPDATE_CURRENT);

        // URI para sonido para sonido de notificacion
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        // Trabajaremos las notificacion,

        NotificationCompat.Action actionFollow = new NotificationCompat.Action.Builder
                (R.drawable.ic_full_user,
                        "Seguir a usuario",
                        pendingIntentFollow).build();
        NotificationCompat.Action actionunFollow = new NotificationCompat.Action.Builder
                (R.drawable.ic_full_user_unfollow,
                        "Dejar de seguir al usuario",
                        pendingIntentunFollow).build();
        NotificationCompat.Action actionProfile = new NotificationCompat.Action.Builder
                (R.drawable.ic_full_user_profile,
                        "Ver mi perfil",
                        pendingIntentiProfile).build();
        NotificationCompat.Action actionProfileAnother = new NotificationCompat.Action.Builder
                (R.drawable.ic_full_heart,
                        "Ver perfil del usuario",
                        pendingIntentiProfileUserLike).build();




        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.user_location_48)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
// Wearable-only actions.
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender().
                setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(),R.drawable.bk_androidwear_material))
                .setGravity(Gravity.CENTER_VERTICAL);
        wearableExtender.addAction(new NotificationCompat.Action.Builder(R.drawable.ic_full_user, "Seguir a usuario", pendingIntentFollow).build());
        wearableExtender.addAction(new NotificationCompat.Action.Builder(R.drawable.ic_full_user_unfollow,"Dejar de seguir al usuario", pendingIntentunFollow).build());
        wearableExtender.addAction(new NotificationCompat.Action.Builder(R.drawable.ic_full_user_profile,"Ver mi perfil", pendingIntentiProfile).build());
        wearableExtender.addAction(new NotificationCompat.Action.Builder(R.drawable.ic_full_heart, "Ver perfil del usuario", pendingIntentiProfileUserLike).build());
        notification.extend(wearableExtender);
        // Daremos mas atributos a la notificacion
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID,notification.build());
    }
}