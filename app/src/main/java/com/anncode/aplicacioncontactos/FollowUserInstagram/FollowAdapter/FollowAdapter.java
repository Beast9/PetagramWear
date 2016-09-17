package com.anncode.aplicacioncontactos.FollowUserInstagram.FollowAdapter;

import com.anncode.aplicacioncontactos.FollowUserInstagram.FollowEndPoint.EndpointFollow;
import com.anncode.aplicacioncontactos.restApi.ConstantesRestApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by isaachernandezquinonez on 21/07/16.
 */
public class FollowAdapter {
    public EndpointFollow ConexionFollow(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndpointFollow.class);
    }
}
