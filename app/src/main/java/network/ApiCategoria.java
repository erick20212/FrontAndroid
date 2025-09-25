package network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCategoria {
    private static final String BASE_URL = "http://26.246.241.197:8081/";  // Asegúrate de que esta sea la URL correcta
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)  // Debes asegurarte de que esta URL sea correcta
                    .addConverterFactory(GsonConverterFactory.create())  // Usa el convertidor adecuado
                    .build();
        }
        return retrofit;
    }

    public static CategoriaApi getCategoriaApi() {
        return getInstance().create(CategoriaApi.class);  // Usa CategoriaApi en lugar de ProductoApi
    }
}