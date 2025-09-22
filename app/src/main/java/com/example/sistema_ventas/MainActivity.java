package com.example.sistema_ventas;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ✅ Usa NavHostFragment para obtener el NavController de forma segura
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main);
        if (navHostFragment == null) {
            throw new IllegalStateException("NavHostFragment con id R.id.main no encontrado. Revisa activity_main.xml");
        }
        NavController navController = navHostFragment.getNavController();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // ✅ Enlaza bottom nav con NavController
        NavigationUI.setupWithNavController(bottomNav, navController);

        // ✅ Oculta bottom nav en login
        navController.addOnDestinationChangedListener((c, dest, args) -> {
            if (dest.getId() == R.id.loginFragment) bottomNav.setVisibility(View.GONE);
            else bottomNav.setVisibility(View.VISIBLE);
        });
    }
}
