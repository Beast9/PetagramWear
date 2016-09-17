package com.anncode.aplicacioncontactos.Wear;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anncode.aplicacioncontactos.Mascota;
import com.anncode.aplicacioncontactos.MascotaResponse;
import com.anncode.aplicacioncontactos.presentador.RecyclerViewFragmentPresenter;
import com.anncode.aplicacioncontactos.restApi.ConstantesRestApi;
import com.anncode.aplicacioncontactos.restApi.EndpointsApi;
import com.anncode.aplicacioncontactos.restApi.JsonKeys;
import com.anncode.aplicacioncontactos.restApi.adapter.FotoAdapter;
import com.anncode.aplicacioncontactos.restApi.adapter.RestApiAdapter;
import com.anncode.recyclerviewfragments.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class photosActivity extends AppCompatActivity {
    private static final String TAG = RecyclerViewFragmentPresenter.class.getName();
    private List<Mascota> fotos;
    private RecyclerView recyclerView;

    public photosActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        recyclerView = (RecyclerView) findViewById(R.id.rvContactos);
        generarImagenes();
        GridLayoutManager glm = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(glm);

    }

    private void iniciarAdaptador() {

        FotoAdapter fa = new FotoAdapter(fotos, getApplicationContext());
        recyclerView.setAdapter(fa);
        recyclerView.setHasFixedSize(true);

    }

    private void generarImagenes() {
        fotos = new ArrayList<>();

        SharedPreferences preps = getApplicationContext().getSharedPreferences("datosPersonales", Context.MODE_PRIVATE);
        String idUsuario = preps.getString(JsonKeys.USER_ID, "");

        RestApiAdapter raa = new RestApiAdapter();
        Gson gsonMedia = raa.construyeGsonDeserializadorMediaRecent();
        EndpointsApi epa = raa.establecerConexionRestApiInstagram(gsonMedia);

        final Call<MascotaResponse> mascotaResponseCall = epa.getUserRecentMedia(idUsuario, ConstantesRestApi.ACCESS_TOKEN);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {

                MascotaResponse mr = response.body();
                if (mr != null) {
                    fotos = mr.getMascotas();
                    iniciarAdaptador();
                }
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fallo conexion, intente de nuevo.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Fallo conexion: " + t.toString());
            }
        });

    }
}
