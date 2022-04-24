package com.comp4905.jobarc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.comp4905.jobarc.Models.RegistrationResponse;
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

        Call<RegistrationResponse> call = RetrofitClient
                .getInstance()
                .getAPI()
                .createUser(new User(name, userName, password, accountType));

        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                RegistrationResponse res = response.body();
                if (res != null){
                    if (res.getStatus().equals("SUCCESS")) {
                        if(accountType.equals("jobseeker")) {
                            Intent intent = new Intent(RegisterActivity.this, PostResumeDescriptionActivity.class);
                            intent.putExtra("id", Long.valueOf(res.getId()));
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Complete!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "User already exists!", Toast.LENGTH_LONG).show();
                    }
                }
            }


            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}