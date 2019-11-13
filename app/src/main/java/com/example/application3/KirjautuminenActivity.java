package com.example.application3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


public class KirjautuminenActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnCancel;
    private Button btnRegister;
    private UserDao userDao;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Tallennettu");

        edtUsername = findViewById(R.id.edtUsernameI);
        edtPassword = findViewById(R.id.edtPasswordI);
        btnCancel = findViewById(R.id.btnCancel);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty()) {
                    progressDialog.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = new User(edtUsername.getText().toString(), edtPassword.getText().toString());
                            userDao.insert(user);
                            progressDialog.dismiss();
                            startActivity(new Intent(KirjautuminenActivity.this, MainActivity.class));
                        }
                    }, 1000);
                } else {
                    Toast.makeText(KirjautuminenActivity.this, "Jokin tieto puuttuu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KirjautuminenActivity.this, MainActivity.class));
                finish();
            }
        });

        userDao = Room.databaseBuilder(this, UserDatabase.class, "tietokanta.db").allowMainThreadQueries().build().getUserDao();
    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(edtUsername.getText().toString()) ||
                TextUtils.isEmpty(edtPassword.getText().toString())
        ) {
            return true;
        } else {
            return false;
        }
    }
}
