package com.comp4905.jobarc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.comp4905.jobarc.R;

public class AccountTypeRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type_register);

        findViewById(R.id.btnEmployer).setOnClickListener(view ->
                startActivity(new Intent(AccountTypeRegisterActivity.this, RegisterActivity.class).putExtra("accountType", "employer")));
        findViewById(R.id.btnJobseeker).setOnClickListener(view ->
                startActivity(new Intent(AccountTypeRegisterActivity.this, RegisterActivity.class).putExtra("accountType", "jobseeker")));
    }
}