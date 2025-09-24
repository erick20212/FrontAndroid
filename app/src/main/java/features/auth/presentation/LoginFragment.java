package features.auth.presentation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sistema_ventas.R;

import features.auth.presentation.data.LoginRequest;
import features.auth.presentation.data.LoginResponse;
import network.ApiClient;
import network.AuthApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import storage.TokenStore;

public class LoginFragment extends Fragment {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private ProgressBar progress;
    private TextView txtError;

    private AuthApi api;

    public LoginFragment() {
        super(R.layout.fragment_login); // ✅ deja esto y NO infles manualmente
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        edtEmail   = v.findViewById(R.id.edtEmail);
        edtPassword= v.findViewById(R.id.edtPassword);
        btnLogin   = v.findViewById(R.id.btnLogin);
        progress   = v.findViewById(R.id.progress);
        txtError   = v.findViewById(R.id.txtError);

        Retrofit retrofit = ApiClient.build(requireContext());
        api = retrofit.create(AuthApi.class);

        btnLogin.setOnClickListener(view -> doLogin());
    }

    private void doLogin() {
        String email = edtEmail.getText().toString().trim();
        String pass  = edtPassword.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(requireContext(), "Completa email y password", Toast.LENGTH_SHORT).show();
            return;
        }

        setLoading(true);
        api.login(new LoginRequest(email, pass)).enqueue(new Callback<LoginResponse>() {
            @Override public void onResponse(Call<LoginResponse> call, Response<LoginResponse> resp) {
                setLoading(false);
                if (resp.isSuccessful() && resp.body()!=null) {
                    LoginResponse body = resp.body();
                    if (body.success) {
                        TokenStore.save(requireContext(), body.token);
                        Toast.makeText(requireContext(), "Bienvenido " + body.user.nombre, Toast.LENGTH_SHORT).show();
                        NavHostFragment.findNavController(LoginFragment.this)
                                .navigate(R.id.homefragment);
                    } else {
                        showError("Credenciales incorrectas");
                    }
                } else {
                    showError(readError(resp.errorBody()));
                }
            }
            @Override public void onFailure(Call<LoginResponse> call, Throwable t) {
                setLoading(false);
                showError("Error de red: " + t.getMessage());
            }
        });
    }

    private void setLoading(boolean loading) {
        progress.setVisibility(loading ? View.VISIBLE : View.GONE);
        btnLogin.setEnabled(!loading);
        txtError.setVisibility(View.GONE);
    }

    private void showError(String msg) {
        txtError.setText(msg);
        txtError.setVisibility(View.VISIBLE);
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show();
    }

    private String readError(ResponseBody errBody) {
        try { return errBody != null ? errBody.string() : "Error en la petición"; }
        catch (Exception e) { return "Error en la petición"; }
    }
}
