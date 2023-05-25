package com.jumcoder.rentalmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText editText1, editText2;
    private TextView textView;
    private Button button;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        //Findings
        editText1 = findViewById(R.id.editTextEmail);
        editText2 = findViewById(R.id.editTextPassword);
        textView = findViewById(R.id.register);
        button = findViewById(R.id.loginButton);
        loadingBar = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowUserToLogin();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AllowUserToLogin() {
        String email = editText1.getText().toString();
        String password = editText2.getText().toString();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Write Your Email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Write Your Password", Toast.LENGTH_SHORT).show();
        }
        else  {
            loadingBar.setTitle("Login");
            loadingBar.setMessage("Please Wait");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            SendUserToMainActivity();

//                            if(task.isSuccessful()) {
//                                SendUserToMainActivity();
//
//                                Toast.makeText(LoginActivity.this, "You are Logged In Successfully", Toast.LENGTH_SHORT).show();
//                                loadingBar.dismiss();
//                            }
//                            else  {
//                                String message = task.getException().getMessage();
//                                Toast.makeText(LoginActivity.this, "Error Occured" + message, Toast.LENGTH_SHORT).show();
//                                loadingBar.dismiss();
//                            }
                        }
                    });
        }
    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}