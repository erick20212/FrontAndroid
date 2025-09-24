package network;

import Dto.ProductoDTO;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ProductoApi {

    @GET("/api/productos")  
    Call<List<ProductoDTO>> getAllProducts();  // Retorna una lista de productos (ProductoDTO)
}