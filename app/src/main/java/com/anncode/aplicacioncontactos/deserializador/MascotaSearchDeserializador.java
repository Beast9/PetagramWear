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
public class MascotaSearchDeserializador implements JsonDeserializer<MascotaResponse>{
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

            String id = contactoResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            Log.d("ID_USER",id);
            String nombreCompleto = contactoResponseDataObject.get(JsonKeys.USER_FULL_NAME).getAsString();
            String profilePicture = contactoResponseDataObject.get(JsonKeys.PROFILE_PICTURE).getAsString();

            Mascota mascota = new Mascota();
            mascota.setIdMascota(id);
            mascota.setNombre(nombreCompleto);
            mascota.setImagen(profilePicture);

            mascotas.add(mascota);

        }
        return mascotas;
    }
}
