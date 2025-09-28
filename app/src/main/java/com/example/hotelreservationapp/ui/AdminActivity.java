package com.example.hotelreservationapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hotelreservationapp.R;

public class AdminActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnHotels = findViewById(R.id.btnHotels);
        Button btnRooms = findViewById(R.id.btnRooms);
        Button btnBookings = findViewById(R.id.btnBookings);

        btnHotels.setOnClickListener(v ->
                Toast.makeText(this, "Gérer Hôtels (Sprint 3)", Toast.LENGTH_SHORT).show());
        btnRooms.setOnClickListener(v ->
                Toast.makeText(this, "Gérer Chambres (Sprint 3)", Toast.LENGTH_SHORT).show());
        btnBookings.setOnClickListener(v ->
                Toast.makeText(this, "Gérer Réservations (Sprint 3)", Toast.LENGTH_SHORT).show());
    }
}
