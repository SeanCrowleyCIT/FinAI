package com.example.finai.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finai.MainNav;
import com.example.finai.R;
import com.example.finai.ui.login.LoginViewModel;
import com.example.finai.ui.login.LoginViewModelFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder;
import java.lang.Process;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        String output = String.valueOf(usernameEditText.getText());
        //System.out.println(usernameEditText);
        Log.println(Log.DEBUG, "tag", output);


        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        //curl
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here
                //String curl = "curl https://Cormander.pythonanywhere.com/newUser -d 'username=testing@hotmail.com' -d 'password=apitest' -X GET";
                //Log.d(curl);


                //String command = "curl GET https://Cormander.pythonanywhere.com/newUser -d 'usernameEditText.getText() ='\" + testing@hotmail.com + \" -d\n" + "'passwordEditText='\" +apitest +\" -X GET";
                String command = "curl https://Cormander.pythonanywhere.com/newUser -d 'usernameEditText.getText()' -d 'passwordEditText.getText()' -X GET";
                ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));



                try {
                    Process p = new ProcessBuilder(command).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Process process = null;
                try {
                    process = processBuilder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert process != null;
                InputStream inputStream = process.getInputStream();
                Log.i("Curl Test", String.valueOf(inputStream));

                //testing
                //String curlTest = "curl https://Cormander.pythonanywhere.com/newUser -d 'username=testing@hotmail.com' -d 'password=apitest' -X GET";
                //Log.i("This is the curlTest", curlTest);
            }
        });

        //

        String welcome = getString(R.string.welcome) + model.getDisplayName();

        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();

        Intent activityIntent = new Intent(getApplicationContext(), MainNav.class);
        startActivity(activityIntent);
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}