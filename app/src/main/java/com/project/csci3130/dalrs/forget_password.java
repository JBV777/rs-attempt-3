package com.project.csci3130.dalrs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Button submit = findViewById(R.id.submit);
        Button back = findViewById(R.id.back);
        final EditText e = findViewById(R.id.email);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!e.getText().toString().isEmpty()) {
                    e.setError("Please enter the email!");
                    Toast.makeText(forget_password.this, "Please enter the email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!e.getText().toString().contains("@dal.ca")){
                    e.setError("Please enter a valid email!");
                    Toast.makeText(forget_password.this, "Please enter a valid email!", Toast.LENGTH_LONG).show();
                    return;
                }
                FirebaseAuth.getInstance().sendPasswordResetEmail(e.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(forget_password.this,"Email with instructions are sent successfully",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(forget_password.this,"Error, please check your email",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(forget_password.this,LoginActivity.class));
            }
        });
    }
}
