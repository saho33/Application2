package com.example.application3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity  extends AppCompatActivity {
    private TextView tvUser;
    private Button btnLogout;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        user = (User) getIntent().getSerializableExtra("User");
        tvUser = findViewById(R.id.tvUser);
        btnLogout = findViewById(R.id.btnLogOut);

        if (user != null) {
            tvUser.setText(user.getUsername());
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}