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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText editText1, editText2, editText3;
    private Button button;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        //Findings
        editText1 = findViewById(R.id.registerEmail);
        editText2 = findViewById(R.id.registerPass);
        editText3 = findViewById(R.id.registerRepeatPass);
        button = findViewById(R.id.registerButton);
        loadingBar = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });
    }

    private void CreateNewAccount() {
        String email = editText1.getText().toString();
        String password = editText2.getText().toString();
        String repeatPassword = editText3.getText().toString();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Input Your Email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Input Your Password", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(repeatPassword)) {
            Toast.makeText(this, "Please Confirm Your Password", Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(repeatPassword)) {
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
        }

        else {
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please Wait");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                SendUserToSetUpActivity();

                                Toast.makeText(RegisterActivity.this, "Your Account is Created Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                String message = Objects.requireNonNull(task.getException()).getMessage();
                                Toast.makeText(RegisterActivity.this, "Error Occurred: " + message, Toast.LENGTH_SHORT).show();
                            }
                            loadingBar.dismiss();
                        }
                    });
        }
    }

    private void SendUserToSetUpActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}