package com.anncode.aplicacioncontactos.LikeInstagram.Adapter;

import com.anncode.aplicacioncontactos.LikeInstagram.EndpointLike.EndpointLike;
import com.anncode.aplicacioncontactos.restApi.ConstantesRestApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by isaachernandezquinonez on 15/07/16.
 */
public class LikeAdapter {
    public EndpointLike establecerConeccion(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndpointLike.class);
    }
}
