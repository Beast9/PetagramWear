package com.anncode.aplicacioncontactos.FollowUserInstagram.FollowEndPoint;

import com.anncode.aplicacioncontactos.FollowUserInstagram.FollowResponse.FollowResponse;
import com.anncode.aplicacioncontactos.restApi.ConstantesRestApi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by isaachernandezquinonez on 21/07/16.
 */
public interface EndpointFollow {
    @FormUrlEncoded
    @POST(ConstantesRestApi.URL_FOLLOW)
    Call<FollowResponse>followUser(@Path("user_id") String user_id, @Field("action") String action);
}
