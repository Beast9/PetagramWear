package com.anncode.aplicacioncontactos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anncode.aplicacioncontactos.restApi.ConstantesRestApi;
import com.anncode.aplicacioncontactos.restApi.EndpointsApi;
import com.anncode.aplicacioncontactos.restApi.JsonKeys;
import com.anncode.aplicacioncontactos.restApi.adapter.RestApiAdapter;
import com.anncode.recyclerviewfragments.R;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuentaInstagram extends AppCompatActivity {
    private static final String TAG = CuentaInstagram.class.getName();
    private TextView nombreUsuario;
    private Activity activity;


    public String nombre2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_instagram);
        nombreUsuario = (TextView) findViewById(R.id.nombreBusqueda);
        activity = this;
    }
    public void buscarContacto(final View view) {



        RestApiAdapter raa = new RestApiAdapter();
        Gson gsonSearch = raa.construyeGsonDeserializadorSearch();
        EndpointsApi epa = raa.establecerConexionRestApiInstagram(gsonSearch);
        String nombre = nombreUsuario.getText().toString();
        nombre2 = nombre;
        final Call<MascotaResponse> mascotaResponseCall = epa.search(nombre, ConstantesRestApi.ACCESS_TOKEN);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {

                List<Mascota> mascotas = response.body().getMascotas();

                if (mascotas != null && !mascotas.isEmpty()) {

                    guardarPreferenciasUsuario(response.body().getMascotas().get(0));
                    Intent intent = new Intent(CuentaInstagram.this, MainActivity.class);
                    startActivity(intent);
                    ActivityCompat.finishAffinity(CuentaInstagram.this);
                }else{
                    Snackbar.make(view,"El usuario no tiene acceso a esta area.",Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(CuentaInstagram.this, "Fallo conexion, intente de nuevo.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Fallo conexion: " + t.toString());
            }
        });


    }


    public void guardarPreferenciasUsuario(Mascota mascota) {

        SharedPreferences preps = getSharedPreferences("datosPersonales", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preps.edit();

        String profilePicture = mascota.getImagen();
        String nombre = mascota.getNombre();
        String idUsuario = mascota.getIdMascota();


        edit.putString(JsonKeys.USER, nombre2);
        edit.putString(JsonKeys.USER_ID, idUsuario);
        edit.putString(JsonKeys.USER_FULL_NAME, nombre);
        edit.putString(JsonKeys.PROFILE_PICTURE, profilePicture);

        edit.commit();

    }
}
