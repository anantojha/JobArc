package com.comp4905.jobarc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.comp4905.jobarc.R;
import com.comp4905.jobarc.RetrofitClient;
import com.comp4905.jobarc.Models.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etUsername, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etRName);
        etUsername = findViewById(R.id.etRUserName);
        etPassword = findViewById(R.id.etRPassword);
        etConfirmPassword = findViewById(R.id.etRPasswordConfirm);
        String accountType = getIntent().getStringExtra("accountType");


        findViewById(R.id.btnRegister).setOnClickListener(view ->
                registerUser(accountType));

        findViewById(R.id.tvLoginLink).setOnClickListener(view ->
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }

    private void registerUser(String accountType) {
        String name = etName.getText().toString().trim();
        String userName = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Name is required");
            etName.requestFocus();
            return;
        } else if (userName.isEmpty()) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return;
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        } else if (confirmPassword.isEmpty()) {
            etConfirmPassword.setError("Confirm Password is required");
            etConfirmPassword.requestFocus();
            return;
        } else if (!password.equals(confirmPassword)) {
            etPassword.setError("Password does not match");
            etPassword.requestFocus();
            etConfirmPassword.setError("Confirm Password does not match");
            etConfirmPassword.requestFocus();
            return;
        }

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAPI()
                .createUser(new User(userName, password).setAccountType(accountType).setName(name));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = "";
                try {
                    s = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (s.equals("\"SUCCESS\"")) {
                    Toast.makeText(RegisterActivity.this, "Successfully registered. Please login", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "User already exists!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}