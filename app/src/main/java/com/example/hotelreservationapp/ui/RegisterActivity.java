package com.example.hotelreservationapp.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hotelreservationapp.R;
import com.example.hotelreservationapp.data.UserDao;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputName, inputEmail, inputPassword;
    private Button btnRegister;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName     = findViewById(R.id.inputName);
        inputEmail    = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnRegister   = findViewById(R.id.btnRegister);

        userDao = new UserDao(this);

        btnRegister.setOnClickListener(v -> {
            String name  = inputName.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();
            String pass  = inputPassword.getText().toString();

            if (TextUtils.isEmpty(name)) {
                inputName.setError("Nom requis");
                return;
            }
            if (!email.contains("@")) {
                inputEmail.setError("Email invalide");
                return;
            }
            if (pass.length() < 8) {
                inputPassword.setError("Min 8 caractères");
                return;
            }

            if (userDao.emailExists(email)) {
                inputEmail.setError("Email déjà utilisé");
                return;
            }

            boolean ok = userDao.insertUser(name, email, pass);
            if (ok) {
                Toast.makeText(this, "Compte créé (rôle: client)", Toast.LENGTH_SHORT).show();
                finish(); // Retour vers LoginActivity
            } else {
                Toast.makeText(this, "Erreur lors de l'inscription", Toast.LENGTH_LONG).show();
            }
        });
    }
}
