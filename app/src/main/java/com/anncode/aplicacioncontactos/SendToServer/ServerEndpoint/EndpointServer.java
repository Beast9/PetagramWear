package com.anncode.aplicacioncontactos.SendToServer.ServerEndpoint;

import com.anncode.aplicacioncontactos.GCM.Notifications.ConstantesRestAPI;
import com.anncode.aplicacioncontactos.SendToServer.ServerResponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by isaachernandezquinonez on 16/07/16.
 */
public interface EndpointServer {
    @FormUrlEncoded
    @POST(ConstantesRestAPI.POST_SERVER_NOTIFICACIOn)
    Call<ServerResponse> sendNotification(@Field("id_foto_instagram") String id_foto_instagram,
                                          @Field("id_usuario_instagram") String id_usuario_instagram,
                                          @Field("id_dispositivo") String id_dispositivo);
}
