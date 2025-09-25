package features.auth.presentation;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sistema_ventas.R;

import features.Adapters.CategoriaAdapter;
import Dto.CategoriaDTO;
import network.ApiCategoria;  // Usa ApiCategoria aquí
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class CategoriaFragment extends Fragment {
    private RecyclerView rvCategorias;
    private CategoriaAdapter categoriaAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categoria, container, false);  // Inflate the layout for categories
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCategorias = view.findViewById(R.id.rvCategorias);  // Referencia el RecyclerView para categorías
        rvCategorias.setLayoutManager(new LinearLayoutManager(requireContext()));  // Para lista vertical

        // Llamar al método para obtener categorías desde la API
        loadCategorias();
    }

    private void loadCategorias() {
        ApiCategoria apiCategoria = new ApiCategoria();  // Usar ApiCategoria
        Call<List<CategoriaDTO>> call = apiCategoria.getCategoriaApi().getAllCategorias();  // Obtener categorías

        call.enqueue(new Callback<List<CategoriaDTO>>() {
            @Override
            public void onResponse(Call<List<CategoriaDTO>> call, Response<List<CategoriaDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CategoriaDTO> categoriaList = response.body();
                    categoriaAdapter = new CategoriaAdapter(categoriaList);
                    rvCategorias.setAdapter(categoriaAdapter);  // Asignar el adaptador para categorías
                } else {
                    Log.e("API", "Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<CategoriaDTO>> call, Throwable t) {
                Log.e("API", "Error en la conexión: " + t.getMessage());
            }
        });
    }
}