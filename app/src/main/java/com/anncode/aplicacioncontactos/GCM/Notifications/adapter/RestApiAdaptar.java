package com.anncode.aplicacioncontactos.GCM.Notifications.adapter;

import com.anncode.aplicacioncontactos.GCM.Notifications.ConstantesRestAPI;
import com.anncode.aplicacioncontactos.GCM.Notifications.Endpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by isaachernandezquinonez on 12/07/16.
 */
public class RestApiAdaptar {
    public Endpoints establecerConexionesRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Endpoints.class);
    }
}
