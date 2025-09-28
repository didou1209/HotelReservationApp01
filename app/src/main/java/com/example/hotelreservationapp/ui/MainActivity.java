package com.example.hotelreservationapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hotelreservationapp.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSearch = findViewById(R.id.btnSearch);
        Button btnMyBookings = findViewById(R.id.btnMyBookings);

        btnSearch.setOnClickListener(v ->
                Toast.makeText(this, "Écran Recherche (Sprint 3/4)", Toast.LENGTH_SHORT).show());
        btnMyBookings.setOnClickListener(v ->
                Toast.makeText(this, "Mes réservations (Sprint 3)", Toast.LENGTH_SHORT).show());
    }
}
