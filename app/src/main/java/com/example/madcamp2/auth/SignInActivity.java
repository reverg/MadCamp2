package com.example.madcamp2.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madcamp2.MainActivity;
import com.example.madcamp2.R;
import com.example.madcamp2.RetrofitClient;
import com.example.madcamp2.auth.DTO.SignInResult;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends Activity {
    private MaterialButton signInBtn, signUpBtn;
    private TextInputEditText signInUsername, signInPassword;
    private TextView signInAlert;

    private String TOKEN_KEY = "JWT TOKEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInBtn = (MaterialButton) findViewById(R.id.sign_in_button);
        signInUsername = (TextInputEditText) findViewById(R.id.sign_in_username);
        signInPassword = (TextInputEditText) findViewById(R.id.sign_in_password);
        signInAlert = (TextView) findViewById(R.id.sign_in_alert);
        signUpBtn = (MaterialButton) findViewById(R.id.sign_up_button);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(signInUsername.getText().toString())
                        || TextUtils.isEmpty(signInPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Username / Password Required",
                            Toast.LENGTH_LONG).show();
                } else {
                    // proceed to login
                    signIn();
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void signIn() {
        Call<SignInResult> callSignIn = RetrofitClient.getSignInService()
                .signinFunc(signInUsername.getText().toString(), signInPassword.getText().toString());
        callSignIn.enqueue(new Callback<SignInResult>() {
            @Override
            public void onResponse(Call<SignInResult> call, Response<SignInResult> response) {
                if (response.isSuccessful()) {
                        String accessToken = response.body().getAccessToken();
                        String message = response.body().getMessage();

                        TokenManager.setToken(getApplicationContext(), TokenManager.TOKEN_KEY, accessToken);

                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SignInResult> call, Throwable t) {
                Log.d("SignInActivity", t.getMessage());
                Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void authenticate(String token) {
        Call<Boolean> callSignIn = RetrofitClient.getSignInService()
                .authFunc(token);
        callSignIn.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body()) {
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("SignInActivity", t.getMessage());
                Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}
