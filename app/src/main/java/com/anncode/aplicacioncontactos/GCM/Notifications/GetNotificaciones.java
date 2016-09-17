package com.anncode.aplicacioncontactos.GCM.Notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.anncode.aplicacioncontactos.GCM.Notifications.adapter.RestApiAdaptar;
import com.anncode.aplicacioncontactos.GCM.Notifications.model.UsuarioResponse;

import com.anncode.aplicacioncontactos.restApi.JsonKeys;
import com.anncode.recyclerviewfragments.R;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNotificaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_notificaciones);
    }
    public void EnviarDatos(View v){
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("TAG",token);
        enviarTokenRegistroInstagram(token);
    }
    private void enviarTokenRegistroInstagram(String token){


        SharedPreferences preps = getSharedPreferences("datosPersonales", Context.MODE_PRIVATE);

        String fullName = preps.getString(JsonKeys.USER, "self");
        Log.d("token", token);
        Log.d("idInstgram",fullName);
        RestApiAdaptar RestApiAdaptar = new RestApiAdaptar();
        Endpoints endpoints = RestApiAdaptar.establecerConexionesRestAPI();
        // Enviamos token a el servidor
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenID(token,fullName);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                //Log.d("ID_FIREBASE",usuarioResponse.getIdinstagram());
                //Log.d("TOKEN_FIREBASE",usuarioResponse.getToken());
                TextView contenedorRequest = (TextView) findViewById(R.id.textView);
                contenedorRequest.setText("idFirebase : "+ usuarioResponse.getId() +"\n"+"id Instagram :" + usuarioResponse.getIdinstagram()+"\n"+ "token:  "+ usuarioResponse.getTokenDevices());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }
}
