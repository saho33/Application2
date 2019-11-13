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

public class MainActivity extends AppCompatActivity {

    private Button btnSignIn;
    private Button btnSignUp;
    private EditText edtUsername;
    private EditText edtPassword;
    private UserDatabase database;
    private UserDao userDao;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setProgress(0);

        database = Room.databaseBuilder(this, UserDatabase.class, "tietokanta.db").allowMainThreadQueries().build();
        userDao = database.getUserDao();

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtUsername = findViewById(R.id.edtUsernameInput);
        edtPassword = findViewById(R.id.edtPasswordInput);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KirjautuminenActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = userDao.getUser(edtUsername.getText().toString(), edtPassword.getText().toString());
                            if(user!=null){
                                Intent i = new Intent(MainActivity.this, UserActivity.class);
                                i.putExtra("User", user);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(MainActivity.this, "Tuntematon kirjautuminen", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    }, 1000);
                }else{

                }
            }
        });
    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(edtUsername.getText().toString()) || TextUtils.isEmpty(edtPassword.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }
}