package network;

import features.auth.presentation.data.LoginRequest;
import features.auth.presentation.data.LoginResponse;
import features.auth.presentation.data.GenericResponse;
import features.auth.presentation.data.RegisterRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest body);

    @POST("api/auth/register")
    Call<GenericResponse> register(@Body RegisterRequest body);
}