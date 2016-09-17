package com.anncode.aplicacioncontactos.restApi;

import com.anncode.aplicacioncontactos.MascotaResponse;
import com.anncode.aplicacioncontactos.restApi.model.ContactoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_RECENT_MEDIA_USER)
    public Call<MascotaResponse> getUserRecentMedia(@Path("user_id") String userId,
                                                    @Query("access_token") String accessToken);

    @GET(ConstantesRestApi.URL_SEARCH_USERS)
    public Call<MascotaResponse> search(@Query("q") String query, @Query("access_token") String accessToken);

}
