package features.auth.presentation;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistema_ventas.R;

import Dto.ProductoDTO;
import features.products.ProductAdapter;
import network.ApiProduct;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvProductos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);  // Inflar el layout de tu fragmento
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvProductos = view.findViewById(R.id.rvProductos);  // Configura el RecyclerView
        rvProductos.setLayoutManager(new GridLayoutManager(requireContext(), 2));  // Configura el RecyclerView para 2 columnas

        // Llamada a la API para obtener todos los productos
        ApiProduct apiProduct = new ApiProduct();  // Usar ApiProduct para crear una instancia de Retrofit
        Call<List<ProductoDTO>> call = apiProduct.getApi().getAllProducts();  // Llamada a getAllProducts() en ProductoApi

        // Realizar la petición asincrónica
        call.enqueue(new Callback<List<ProductoDTO>>() {
            @Override
            public void onResponse(Call<List<ProductoDTO>> call, Response<List<ProductoDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProductoDTO> productList = response.body();
                    Log.d("API", "Productos recibidos: " + productList.size());  // Verifica cuántos productos fueron recibidos

                    // Crear el adaptador y asignarlo al RecyclerView
                    ProductAdapter adapter = new ProductAdapter(productList);
                    rvProductos.setAdapter(adapter);  // Configura el RecyclerView con el adaptador
                } else {
                    Log.e("API", "Error en la respuesta: " + response.message());  // Si hay un error con la respuesta
                }
            }

            @Override
            public void onFailure(Call<List<ProductoDTO>> call, Throwable t) {
                Log.e("API", "Error en la conexión: " + t.getMessage());  // Si hay un error en la conexión
            }
        });
    }
}