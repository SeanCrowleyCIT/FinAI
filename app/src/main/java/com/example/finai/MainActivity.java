package com.example.finai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import com.example.finai.ui.login.LoginActivity;
import com.example.finai.ui.login.RegisterActivity;

public class MainActivity extends AppCompatActivity {



    public void signIn(View view){
            Log.i( "sign in",  "sign in");
        }

    public void register(View view) {
        Log.i("register", "register");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signIn = findViewById(R.id.signIn);
        Button register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                System.out.println("Register Clicked");

                Intent activity2IntentReg = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(activity2IntentReg);
            }

        });

        signIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                System.out.println("Sign In Clicked");

                Intent activity2Intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(activity2Intent);
            }

        });




    }
}