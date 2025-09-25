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
import features.Adapters.ProductAdapter;
import network.ApiProduct;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView rvProductos;
    private ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvProductos = view.findViewById(R.id.rvProductos);
        rvProductos.setLayoutManager(new GridLayoutManager(requireContext(), 2));  // Mostrar en 2 columnas

        // Obtener los productos de la API
        loadProducts();
    }

    // Método para cargar los productos
    private void loadProducts() {
        ApiProduct apiProduct = new ApiProduct();  // Instancia de ApiProduct
        Call<List<ProductoDTO>> call = apiProduct.getApi().getAllProducts();

        call.enqueue(new Callback<List<ProductoDTO>>() {
            @Override
            public void onResponse(Call<List<ProductoDTO>> call, Response<List<ProductoDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProductoDTO> productList = response.body();
                    productAdapter = new ProductAdapter(productList);
                    rvProductos.setAdapter(productAdapter);
                } else {
                    Log.e("API", "Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ProductoDTO>> call, Throwable t) {
                Log.e("API", "Error en la conexión: " + t.getMessage());
            }
        });
    }
}
