package com.anncode.aplicacioncontactos.LikeInstagram.EndpointLike;

import com.anncode.aplicacioncontactos.LikeInstagram.Response.LikeResponse;
import com.anncode.aplicacioncontactos.restApi.ConstantesRestApi;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by isaachernandezquinonez on 15/07/16.
 */
public interface EndpointLike {
    @POST(ConstantesRestApi.URL_POST_LIKES)
    Call<LikeResponse> darLike(@Path("idMedia") String idMedia);

}
