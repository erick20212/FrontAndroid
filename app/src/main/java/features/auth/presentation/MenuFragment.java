package features.auth.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sistema_ventas.R;

import storage.TokenStore;

public class MenuFragment extends Fragment {

    // Cambié el tipo de Button a CardView
    private View cardLogout; // Referencia al CardView de logout

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);  // Infla el layout de Menu Fragment
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Referencia el CardView de logout
        cardLogout = view.findViewById(R.id.btnLogout); // Asegúrate de que la ID coincida con la de tu XML

        // Configurar el listener para el logout
        cardLogout.setOnClickListener(v -> {
            // Eliminar el token de SharedPreferences al hacer logout
            TokenStore.clear(requireContext());

            // Redirigir al LoginFragment
            NavHostFragment.findNavController(MenuFragment.this)
                    .navigate(R.id.loginFragment);  // Asegúrate de que la ID del LoginFragment esté correcta
        });
    }
}