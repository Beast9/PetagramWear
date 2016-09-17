package com.anncode.aplicacioncontactos.GCM.Notifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by isaachernandezquinonez on 12/07/16.
 */
public class NotificacionIDTokenService extends FirebaseInstanceIdService {
    public static final String TAG = "FIREBASE-TOKEN";
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }
    private void enviarTokenRegistro(String token){
        Log.d(TAG, token);
    }
}