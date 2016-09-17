package com.anncode.aplicacioncontactos.restApi.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anncode.aplicacioncontactos.LikeInstagram.Adapter.LikeAdapter;
import com.anncode.aplicacioncontactos.LikeInstagram.EndpointLike.EndpointLike;
import com.anncode.aplicacioncontactos.LikeInstagram.Response.LikeResponse;

import com.anncode.aplicacioncontactos.Mascota;
import com.anncode.aplicacioncontactos.SendToServer.ServerAdapter.ServerAdapter;
import com.anncode.aplicacioncontactos.SendToServer.ServerEndpoint.EndpointServer;
import com.anncode.aplicacioncontactos.SendToServer.ServerResponse.ServerResponse;
import com.anncode.aplicacioncontactos.restApi.JsonKeys;
import com.anncode.recyclerviewfragments.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by isaachernandezquinonez on 06/07/16.
 */
public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.FotoHolder> {


        private static final String TAG = FotoAdapter.class.getName();
        private List<Mascota> fotos;
        private Context context;

        public FotoAdapter(List<Mascota> fotos, Context context) {
            this.fotos = fotos;
            this.context = context;
        }

        @Override
        public FotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Log.i(TAG,"Creando la vista");
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_contacto,parent,false);

            return new FotoHolder(view);
        }

        @Override
        public void onBindViewHolder(FotoHolder holder, int position) {

            final String token = FirebaseInstanceId.getInstance().getToken();
            final Mascota foto = fotos.get(position);
            Log.i(TAG,"foto: "+foto.getImagen());
            Log.i(TAG,"likes: "+foto.getLikes());
            Log.i(TAG,"link /._./: "+foto.getLink());
            String URILINK = foto.getLink();

            holder.cantidad.setText(String.valueOf(foto.getLikes()));
            holder.imgBone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("HOla", foto.getLink());
                    POST_LIKE(foto.getLink());
                    SENT_TO_SERVER(foto.getLink(),foto.getIdMascota(),token);
                }
            });
            Picasso.with(context)
                    .load(foto.getImagen())
                    .placeholder(R.drawable.candy_icon).into(holder.imagen);


        }



        public void SENT_TO_SERVER(String id_foto_instagram, String id_usuario_instagram, String id_dispositivo){
            ServerAdapter serverAdapter = new ServerAdapter();
            EndpointServer enpointserver = serverAdapter.ConecionAServidor();
            Call<ServerResponse> serverresponseCall = enpointserver.sendNotification(id_foto_instagram,id_usuario_instagram,id_dispositivo);
            serverresponseCall.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    ServerResponse serverResponse = response.body();
                    //Log.d("SERVER",serverResponse.getId());
                    //Log.d("SERVER",serverResponse.getId_dispositivo());
                    //Log.d("SERVER",serverResponse.getId_foto_instagram());
                    //Log.d("SERVER",serverResponse.getId_usuario_instagram());
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {

                }
            });
        }

        public void POST_LIKE(String idMedia){
            LikeAdapter likeAdapter = new LikeAdapter();
            EndpointLike endpointLike = likeAdapter.establecerConeccion();
            Call<LikeResponse> likeResponseCall = endpointLike.darLike(idMedia);
            likeResponseCall.enqueue(new Callback<LikeResponse>() {
                @Override
                public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                    Toast.makeText(context.getApplicationContext(),"Diste like a una foto", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<LikeResponse> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(),"Error :(", Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public int getItemCount() {
            return fotos.size();
        }

        public static class FotoHolder  extends RecyclerView.ViewHolder{

            private ImageView imagen;
            private TextView cantidad;
            private ImageView imgBone;

            public FotoHolder(View itemView) {
                super(itemView);
                imagen = (ImageView) itemView.findViewById(R.id.imgFoto);
                cantidad = (TextView) itemView.findViewById(R.id.tvLikes);
                imgBone = (ImageView) itemView.findViewById(R.id.imgBone);
            }
        }
}
