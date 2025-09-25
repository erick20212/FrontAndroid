package features.auth.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistema_ventas.R;

import Dto.ProductoDTO;
import features.Adapters.ProductAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import network.ApiProduct;

import java.util.List;

public class ProductsFragment extends Fragment {

    private RecyclerView rvProductos;
    private TextView txtCategoriaTitulo;  // Agrega la referencia para el título
    private String categoriaId;  // Variable para almacenar el ID de la categoría seleccionada
    private String categoriaNombre;  // Para almacenar el nombre de la categoría

    public ProductsFragment() {
        super(R.layout.fragment_categoria_productos); // Asegúrate de que el layout esté correcto
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Obtener el ID de la categoría desde los argumentos del fragmento
        if (getArguments() != null) {
            categoriaId = getArguments().getString("categoriaId");
            categoriaNombre = getArguments().getString("categoriaNombre");  // Obtener el nombre de la categoría
        }

        return inflater.inflate(R.layout.fragment_categoria_productos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvProductos = view.findViewById(R.id.rvProductos);  // Configura el RecyclerView
        txtCategoriaTitulo = view.findViewById(R.id.txtCategoriaTitulo);  // Configura el TextView para el título de la categoría

        // Asignar el nombre de la categoría al TextView
        txtCategoriaTitulo.setText(categoriaNombre);  // Actualiza el título con el nombre de la categoría

        rvProductos.setLayoutManager(new GridLayoutManager(requireContext(), 2));  // Configura el RecyclerView para mostrar productos en 2 columnas

        // Llamar al método para obtener los productos de la categoría seleccionada
        loadProductos();
    }

    // Método para cargar los productos de la categoría seleccionada
    private void loadProductos() {
        // En lugar de ApiProducto usa ApiProduct
        ApiProduct apiProduct = new ApiProduct();
        Call<List<ProductoDTO>> call = apiProduct.getApi().getProductosPorCategoria(categoriaId);

        call.enqueue(new Callback<List<ProductoDTO>>() {

            @Override
            public void onResponse(Call<List<ProductoDTO>> call, Response<List<ProductoDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProductoDTO> productList = response.body();
                    Log.d("PRODUCTS", "Productos cargados: " + productList.size());  // Verifica cuántos productos están llegando

                    ProductAdapter adapter = new ProductAdapter(productList);  // Crear el adaptador
                    rvProductos.setAdapter(adapter);  // Asignar el adaptador al RecyclerView
                } else {
                    Log.e("API", "Error en la respuesta: " + response.message());  // En caso de error en la respuesta de la API
                }
            }

            @Override
            public void onFailure(Call<List<ProductoDTO>> call, Throwable t) {
                // Manejo de errores (por ejemplo, mostrar un mensaje de error)
                Log.e("API", "Error al cargar productos: " + t.getMessage());
            }
        });
    }
}
