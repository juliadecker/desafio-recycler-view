package com.example.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.adapter.listener.OnItemClickListener;
import com.example.recyclerview.data.CountryCovidData;

import java.util.List;

@SuppressWarnings("rawtypes")
public class ListaPaisesAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<CountryCovidData> dados;
    private OnItemClickListener onItemClickListener;

    public ListaPaisesAdapter(Context context, List<CountryCovidData> dados){
        this.context = context;
        this.dados = dados;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pais, parent, false);
        return new PaisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CountryCovidData dado = dados.get(position);
        mostraNomePais(holder, dado);
    }

    private void mostraNomePais(RecyclerView.ViewHolder holder, CountryCovidData dado) {
        TextView nome = holder.itemView.findViewById(R.id.item_pais_nome);
        nome.setText(dado.getCountryText());
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    class PaisViewHolder extends RecyclerView.ViewHolder{
        private CountryCovidData pais;

        public PaisViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(view -> {
                int posicao = getAdapterPosition();
                pais = dados.get(posicao);
                onItemClickListener.OnItemClick(pais);
            });
        }
    }
}
