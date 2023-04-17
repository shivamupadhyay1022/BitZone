package com.solaris.bitzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {
    TextView clickhere_login;
    Button open_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        clickhere_login = findViewById(R.id.clickhere_login);
        open_register = findViewById(R.id.button_openregister);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.clickhere_login:
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.button_openregister:
                Intent intent2 = new Intent(IntroActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;


        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
