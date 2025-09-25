package features.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sistema_ventas.R;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import Dto.CategoriaDTO;
import network.ApiCategoria;  // Usa ApiCategoria


public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    private List<CategoriaDTO> categoriaList;

    // Constructor
    public CategoriaAdapter(List<CategoriaDTO> categoriaList) {
        this.categoriaList = categoriaList;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_categorias, parent, false);
        return new CategoriaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        CategoriaDTO categoria = categoriaList.get(position);
        holder.txtCategoria.setText(categoria.getNombre());

        // Usar Glide para cargar la imagen desde la URL de la categoría
        Glide.with(holder.imgIcono.getContext())
                .load(categoria.getImagenUrl())  // Imagen de la categoría
                .into(holder.imgIcono);  // Establecer la imagen en el ImageView

        // Si la imagen es nula o vacía, se puede usar un ícono por defecto
        if (categoria.getImagenUrl() == null || categoria.getImagenUrl().isEmpty()) {
            holder.imgIcono.setImageResource(R.drawable.ic_manzana); // Icono por defecto
        }
    }

    @Override
    public int getItemCount() {
        return categoriaList.size();
    }

    public static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategoria;
        ImageView imgIcono;

        public CategoriaViewHolder(View itemView) {
            super(itemView);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            imgIcono = itemView.findViewById(R.id.imgIcono);
        }
    }

    // Método para obtener las categorías desde la API
    public void loadCategorias() {
        ApiCategoria apiCategoria = new ApiCategoria();  // Instancia de ApiCategoria
        Call<List<CategoriaDTO>> call = apiCategoria.getCategoriaApi().getAllCategorias();  // Llamada a la API para obtener las categorías

        call.enqueue(new Callback<List<CategoriaDTO>>() {
            @Override
            public void onResponse(Call<List<CategoriaDTO>> call, Response<List<CategoriaDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoriaList = response.body();
                    notifyDataSetChanged();  // Notificar que los datos han cambiado y actualizar el RecyclerView
                } else {
                    // Manejar error de respuesta
                }
            }

            @Override
            public void onFailure(Call<List<CategoriaDTO>> call, Throwable t) {
                // Manejar error de conexión
            }
        });
    }
}
