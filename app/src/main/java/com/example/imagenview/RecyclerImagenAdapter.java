package com.example.imagenview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerImagenAdapter extends RecyclerView.Adapter<RecyclerImagenAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ImagenModel> imagenModelArrayList;

    public RecyclerImagenAdapter(Context context, ArrayList<ImagenModel> imagenModelArrayList) {
        this.context = context;
        this.imagenModelArrayList = imagenModelArrayList;
    }

    @NonNull
    @Override
    public RecyclerImagenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unica_imagen_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context)
                .load(imagenModelArrayList.get(position).getImagenurl())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,NewActivity.class);
                intent.putExtra("imagenes@#", imagenModelArrayList.get(position).getImagenurl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imagenModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imagenview);
        }
    }
}
