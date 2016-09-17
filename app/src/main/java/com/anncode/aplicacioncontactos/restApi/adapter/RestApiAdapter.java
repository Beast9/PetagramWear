package com.anncode.aplicacioncontactos.restApi.adapter;

import com.anncode.aplicacioncontactos.MascotaResponse;
import com.anncode.aplicacioncontactos.deserializador.MascotaRecentMediaDeserializador;
import com.anncode.aplicacioncontactos.deserializador.MascotaSearchDeserializador;
import com.anncode.aplicacioncontactos.restApi.ConstantesRestApi;
import com.anncode.aplicacioncontactos.restApi.EndpointsApi;
import com.anncode.aplicacioncontactos.restApi.deserializador.ContactoDeserializador;
import com.anncode.aplicacioncontactos.restApi.model.ContactoResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {
    public EndpointsApi establecerConexionRestApiInstagram(Gson gson) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        return retrofit.create(EndpointsApi.class);

    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class,new MascotaRecentMediaDeserializador());

        return   gsonBuilder.create();
    }


    public Gson construyeGsonDeserializadorSearch(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class,new MascotaSearchDeserializador());

        return   gsonBuilder.create();
    }
}
