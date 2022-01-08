package com.example.madcamp2.signin;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madcamp2.MainActivity;
import com.example.madcamp2.R;
import com.example.madcamp2.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends Activity {
    private Button signInBtn, signUpBtn;
    private TextInputEditText signInUsername, signInPassword;

    private TextView signInAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInBtn = (Button) findViewById(R.id.sign_in_button);
        signInUsername = (TextInputEditText) findViewById(R.id.sign_in_username);
        signInPassword = (TextInputEditText) findViewById(R.id.sign_in_password);
        signInAlert = (TextView) findViewById(R.id.sign_in_alert);
        signUpBtn = (Button) findViewById(R.id.sign_up_button);

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

        Call<ResponseBody> callSignIn = RetrofitClient.getApiService()
                .signinFunc(signInUsername.getText().toString(), signInPassword.getText().toString());
        callSignIn.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("SignInActivity", t.getMessage());
                Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_LONG).show();
            }
        });

    }
}
