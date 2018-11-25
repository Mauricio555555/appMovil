package com.example.mauricio.local;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener{


    private View.OnClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titulo, descripcion;
        ImageView videoBoton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=(TextView) itemView.findViewById(R.id.titulo);
            descripcion=(TextView) itemView.findViewById(R.id.descripcion);
            videoBoton=(ImageView) itemView.findViewById(R.id.imageButton);
        }
    }
    public List<VideoModel> videoLista;

    public RecyclerViewAdapter(List<VideoModel> videoLista) {

        this.videoLista = videoLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_video_list, viewGroup, false);
        ViewHolder viewHolder=new ViewHolder(view);

        view.setOnClickListener(this);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.titulo.setText(videoLista.get(i).getTitulo());
        holder.descripcion.setText(videoLista.get(i).getDescripcion());
        holder.videoBoton.setImageResource(videoLista.get(i).getImg());
    }

    @Override
    public int getItemCount() {

        return videoLista.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {

        if(listener!=null){
            listener.onClick(v);
        }
    }
}
