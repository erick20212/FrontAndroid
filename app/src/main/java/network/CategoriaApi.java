package network;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import Dto.CategoriaDTO;

public interface CategoriaApi {
    @GET("/api/categorias") // Asegúrate de que esta sea la ruta correcta en tu backend
    Call<List<CategoriaDTO>> getAllCategorias();
}