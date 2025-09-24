package network;

import android.content.Context;

import androidx.annotation.NonNull;



import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import storage.TokenStore;

public class AuthInterceptor implements Interceptor {
    private final Context app;
    public AuthInterceptor(Context c){ this.app = c.getApplicationContext(); }

    @NonNull @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String token = TokenStore.get(app);
        Request req = chain.request();
        if (token != null && !token.isEmpty()){
            req = req.newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
        }
        return chain.proceed(req);
    }
}