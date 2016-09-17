package com.anncode.aplicacioncontactos.deserializador;
import android.util.Log;

import com.anncode.aplicacioncontactos.Mascota;
import com.anncode.aplicacioncontactos.MascotaResponse;
import com.anncode.aplicacioncontactos.restApi.JsonKeys;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isaachernandezquinonez on 06/07/16.
 */
public class MascotaRecentMediaDeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse contactoResponse = gson.fromJson(json,MascotaResponse.class);

        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        contactoResponse.setMascotas(deserializarMascotaDeJson(contactoResponseData));

        return contactoResponse;
    }
    private List<Mascota> deserializarMascotaDeJson(JsonArray contactoResponseData){
        List<Mascota> mascotas = new ArrayList<>();

        for (int i = 0; i < contactoResponseData.size(); i++) {
            JsonObject contactoResponseDataObject = contactoResponseData.get(i).getAsJsonObject();
            Log.d("TODO", String.valueOf(contactoResponseDataObject));
            JsonObject userJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.USER_FULL_NAME).getAsString();

            JsonObject imageJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String url = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            String link = contactoResponseDataObject.get(JsonKeys.MEDIA_LINK).getAsString();

            Log.d("LINK", link);
            Mascota mascota = new Mascota();
            mascota.setIdMascota(id);
            mascota.setNombre(nombreCompleto);
            mascota.setImagen(url);
            mascota.setLink(link);
            mascota.setLikes(likes);

            mascotas.add(mascota);

        }
        return mascotas;
    }
}
