package network;

import Dto.ProductoDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ProductoApi {

    @GET("/api/productos")  // Para obtener todos los productos
    Call<List<ProductoDTO>> getAllProducts();

    @GET("/api/productos/categoria/{categoriaId}")  // Para obtener productos por categoría
    Call<List<ProductoDTO>> getProductosPorCategoria(@Path("categoriaId") String categoriaId);
}