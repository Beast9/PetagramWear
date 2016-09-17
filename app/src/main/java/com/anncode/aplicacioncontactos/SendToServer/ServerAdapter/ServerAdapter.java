package com.anncode.aplicacioncontactos.SendToServer.ServerAdapter;

import com.anncode.aplicacioncontactos.GCM.Notifications.ConstantesRestAPI;
import com.anncode.aplicacioncontactos.SendToServer.ServerEndpoint.EndpointServer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by isaachernandezquinonez on 16/07/16.
 */
public class ServerAdapter {
    public EndpointServer ConecionAServidor(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndpointServer.class);
    }
}
