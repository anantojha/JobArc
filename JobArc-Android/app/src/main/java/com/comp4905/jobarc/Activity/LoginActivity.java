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
import com.comp4905.jobarc.Models.Login;
import com.comp4905.jobarc.R;
import com.comp4905.jobarc.RetrofitClient;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btnLogin).setOnClickListener(view ->
                loginUser());

        findViewById(R.id.tvRegisterLink).setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this, AccountTypeRegisterActivity.class)));
    }

    private void loginUser() {
        final String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty()) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return;
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAPI()
                .checkUser(new Login(username, password));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = "";
                JSONObject jsonObject = null;

                try {
                    s = response.body().string();
                    if (s != null) {
                        System.out.println(s);
                        jsonObject = new JSONObject(s);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                try {
                    if (!jsonObject.get("status").equals("FAILURE")) {
                        Toast.makeText(LoginActivity.this, "User logged in!", Toast.LENGTH_LONG).show();

                        if(jsonObject.get("accountType").toString().equals("employer")){
                            Intent intent = new Intent(LoginActivity.this, EmployerDashboardActivity.class);
                            intent.putExtra("id", Long.valueOf(jsonObject.get("id").toString()));
                            intent.putExtra("name", jsonObject.get("status").toString());
                            intent.putExtra("username", jsonObject.get("username").toString());
                            intent.putExtra("accountType", jsonObject.get("accountType").toString());
                            startActivity(intent);
                        } else if (jsonObject.get("accountType").toString().equals("jobseeker")) {
                            Intent intent = new Intent(LoginActivity.this, JobseekerDashboardActivity.class);
                            intent.putExtra("id", Long.valueOf(jsonObject.get("id").toString()));
                            intent.putExtra("name", jsonObject.get("status").toString());
                            intent.putExtra("username", jsonObject.get("username").toString());
                            intent.putExtra("accountType", jsonObject.get("accountType").toString());
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect Credentials! Try again!", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}