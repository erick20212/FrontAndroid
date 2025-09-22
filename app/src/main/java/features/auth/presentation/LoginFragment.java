package features.auth.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class LoginFragment extends Fragment {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private ProgressBar progress;
    private TextView txtError;

    public LoginFragment() {
        super(R.layout.fragment_login); // usa el layout del Paso 1
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        edtEmail   = v.findViewById(R.id.edtEmail);
        edtPassword= v.findViewById(R.id.edtPassword);
        btnLogin   = v.findViewById(R.id.btnLogin);
        progress   = v.findViewById(R.id.progress);
        txtError   = v.findViewById(R.id.txtError);

        // Acción mínima para probar la pantalla (aún sin ViewModel)
        btnLogin.setOnClickListener(view -> {
            String email = edtEmail.getText().toString().trim();
            String pass  = edtPassword.getText().toString();
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(requireContext(), "Completa email y password", Toast.LENGTH_SHORT).show();
            } else {
                NavHostFragment.findNavController(this).navigate(R.id.homefragment);
            }
        });
    }
}
