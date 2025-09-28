package com.example.hotelreservationapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hotelreservationapp.R;
import com.example.hotelreservationapp.data.UserDao;

public class LoginActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button btnLogin;
    private TextView linkRegister;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail    = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin      = findViewById(R.id.btnLogin);
        linkRegister  = findViewById(R.id.linkRegister);

        userDao = new UserDao(this);
        // (optionnel) créer un admin de test :
        // userDao.ensureAdminTestUser();

        linkRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));

        btnLogin.setOnClickListener(v -> {
            String email = inputEmail.getText().toString().trim();
            String pass  = inputPassword.getText().toString();

            if (TextUtils.isEmpty(email)) { inputEmail.setError("Email requis"); return; }
            if (TextUtils.isEmpty(pass))  { inputPassword.setError("Mot de passe requis"); return; }

            String role = userDao.getRoleIfLoginOK(email, pass);
            if (role == null) {
                Toast.makeText(this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                return;
            }

            if ("admin".equalsIgnoreCase(role)) {
                startActivity(new Intent(this, AdminActivity.class));
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
            finish();
        });
    }
}
