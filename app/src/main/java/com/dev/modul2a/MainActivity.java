package com.dev.modul2a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsername;
    private EditText edtPassword;
    private TextView resultTextFailed;
    private TextView resultTextSuccess;
    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // taking value of inputted component
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        resultTextSuccess = findViewById(R.id.resultTextSuccess);
        resultTextFailed = findViewById(R.id.resultTextFailed);

        // taking button component for event listener
        Button btnLogin = findViewById(R.id.loginButton);

        btnLogin.setOnClickListener(this);

        if(savedInstanceState != null) {
            String resultTextFailedValue = savedInstanceState.getString(STATE_RESULT);
            resultTextFailed.setText(resultTextFailedValue);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginButton) {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            boolean isEmptyFields = false;

            if(TextUtils.isEmpty(username)) {
                isEmptyFields = true;
                edtUsername.setError("Username harus diisi");
            }

            if(TextUtils.isEmpty(password)) {
                isEmptyFields = true;
                edtPassword.setError("Password harus diisi");
            }

            if(!isEmptyFields) {
                String emptyDummy = "";
                if(username.equals("Admin") && password.equals("123")) {
                    String successMessage = "Login Berhasil";
                    resultTextSuccess.setText(String.valueOf(successMessage));
                    resultTextFailed.setText(String.valueOf(emptyDummy));
                } else {
                    String failedMessage = "Login Gagal (cek kembali data anda)";
                    resultTextFailed.setText(String.valueOf(failedMessage));
                    resultTextSuccess.setText(String.valueOf(emptyDummy));
                }
            }
        }
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, resultTextFailed.getText().toString());
    }
}