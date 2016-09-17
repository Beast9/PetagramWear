package com.anncode.aplicacioncontactos.GCM.Notifications;

import com.anncode.aplicacioncontactos.GCM.Notifications.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by isaachernandezquinonez on 12/07/16.
 */
public interface Endpoints {
    @FormUrlEncoded
    @POST(ConstantesRestAPI.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarTokenID(@Field("token")String token, @Field("idinstagram") String idinstagram);
}
