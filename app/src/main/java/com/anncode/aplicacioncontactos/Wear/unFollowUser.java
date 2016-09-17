package com.anncode.aplicacioncontactos.Wear;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.anncode.aplicacioncontactos.FollowUserInstagram.FollowAdapter.FollowAdapter;
import com.anncode.aplicacioncontactos.FollowUserInstagram.FollowEndPoint.EndpointFollow;
import com.anncode.aplicacioncontactos.FollowUserInstagram.FollowResponse.FollowResponse;
import com.anncode.aplicacioncontactos.restApi.JsonKeys;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by isaachernandezquinonez on 22/07/16.
 */
public class unFollowUser extends BroadcastReceiver{

    Context context;
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY = "UNFOLLOW";
        String inten = intent.getAction();

        if (ACCION_KEY.equals(inten)){
            Toast.makeText(context,"Follow",Toast.LENGTH_SHORT).show();
            unfollow();
        }
    }
    public void unfollow(){
        SharedPreferences preps = context.getSharedPreferences("datosPersonales", Context.MODE_PRIVATE);
        String idUsuario = preps.getString(JsonKeys.USER_ID, "");
        final String nombreusuario = preps.getString(JsonKeys.USER_FULL_NAME, "");
        FollowResponse followResponse = new FollowResponse();
        FollowAdapter followAdapter = new FollowAdapter();
        EndpointFollow endpointFollow = followAdapter.ConexionFollow();
        Call<FollowResponse> followResponseCall = endpointFollow.followUser(idUsuario, "unfollow");
        followResponseCall.enqueue(new Callback<FollowResponse>() {
            @Override
            public void onResponse(Call<FollowResponse> call, Response<FollowResponse> response) {
                Toast.makeText(context,"Seguiste a " + nombreusuario, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<FollowResponse> call, Throwable t) {
                Toast.makeText(context,"ha ocurrido un error ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
