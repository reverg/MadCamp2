package com.example.madcamp2.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.madcamp2.R;
import com.example.madcamp2.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends Activity {
    private Button continueBtn;
    private TextInputEditText signUpUsername, signUpDisplayName, signUpPassword, signUpPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        continueBtn = (Button) findViewById(R.id.sign_up_button);
        signUpUsername = (TextInputEditText) findViewById(R.id.sign_up_username);
        signUpDisplayName = (TextInputEditText) findViewById(R.id.sign_up_display_name);
        signUpPassword = (TextInputEditText) findViewById(R.id.sign_up_password);
        signUpPasswordConfirm = (TextInputEditText) findViewById(R.id.sign_up_password_confirm);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(signUpUsername.getText().toString())
                        || TextUtils.isEmpty(signUpDisplayName.getText().toString())
                        || TextUtils.isEmpty(signUpPassword.getText().toString())
                        || TextUtils.isEmpty(signUpPasswordConfirm.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Username / DisplayName / Password Required",
                            Toast.LENGTH_LONG).show();
                } else if (signUpPassword.getText().toString().equals(signUpPasswordConfirm.getText().toString())) {
                    signUp();
                } else {
                    Toast.makeText(getApplicationContext(), "Password Not Match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void signUp() {

        Call<ResponseBody> callSignUp = RetrofitClient.getSignInService()
                .signupFunc(signUpUsername.getText().toString(),
                        signUpPassword.getText().toString(), signUpDisplayName.getText().toString());
        callSignUp.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
