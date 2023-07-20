package com.example.unimercados_beta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unimercados_beta.R;
import com.example.unimercados_beta.model.Categoria;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.MyViewHolder> {

    private final List<Categoria> categoriaList;
    private OnClick onClick;


    public CategoriaAdapter(List<Categoria> categoriaList, OnClick onClick) {
        this.categoriaList = categoriaList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria_horizontal, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Categoria categoria = categoriaList.get(position);

        holder.nomeCategoria.setText(categoria.getNome());

        Picasso.get().load(categoria.getUrlImagem()).into(holder.imagemCategoria);

        holder.itemView.setOnClickListener(view -> {onClick.onClickListener(categoria);});

    }

    @Override
    public int getItemCount() {
        return categoriaList.size();
    }

    public interface OnClick{

        public void onClickListener(Categoria categoria);

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imagemCategoria;
        TextView nomeCategoria;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            imagemCategoria = itemView.findViewById(R.id.imagemCategoria);
            nomeCategoria = itemView.findViewById(R.id.nomeCategoria);
        }
    }

}
