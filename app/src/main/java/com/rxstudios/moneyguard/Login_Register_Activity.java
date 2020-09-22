package com.rxstudios.moneyguard;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Login_Register_Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        generateLoginOrRegisterAlert();
    }

    public void generateLoginOrRegisterAlert() {
        final View loginOrRegisterView = getLayoutInflater().inflate(R.layout.alert_login_or_register, null);

        new AlertDialog.Builder(this)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        generateLoginAlert();
                    }
                })
                .setNegativeButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        generateRegisterAlert();
                    }
                })
                .setTitle("Do you already have an account?")
                .setView(loginOrRegisterView)
                .setCancelable(false)
                .create()
                .show();
    }

    public void generateLoginAlert() {
        final View loginView = getLayoutInflater().inflate(R.layout.alert_login, null);

        new AlertDialog.Builder(this)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText emailEditText = loginView.findViewById(R.id.alert_register_email_edittext);
                        EditText passwordEditText = loginView.findViewById(R.id.alert_register_password_edittext);
                        firebaseAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Log.d("Login_Register_Activity", "onComplete: Register successful");
                                            //TODO Notification
                                        }else{
                                            Log.d("Login_Register_Activity", "onComplete: Register successful");
                                            //TODO Notification
                                        }
                                    }
                                });
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        generateLoginOrRegisterAlert();
                    }
                })
                .setView(loginView)
                .create()
                .show();
    }

    public void generateRegisterAlert() {
        final View registerView = getLayoutInflater().inflate(R.layout.alert_register, null);

        new AlertDialog.Builder(this)
                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final EditText nameEditText = registerView.findViewById(R.id.alert_register_name_edittext);
                        EditText emailEditText = registerView.findViewById(R.id.alert_register_email_edittext);
                        EditText passwordEditText = registerView.findViewById(R.id.alert_register_password_edittext);
                        EditText password2EditText = registerView.findViewById(R.id.alert_register_password2_edittext);
                        //TODO password2EditText EditWatcher
                        if (passwordEditText.getText().equals(password2EditText.getText())) {
                            firebaseAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                                UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                                        .setDisplayName(nameEditText.getText().toString()).build();
                                                firebaseUser.updateProfile(profileUpdate)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if(task.isSuccessful()){
                                                                    Log.d("Login_Register_Activity", "onComplete: Register successful");
                                                                    //TODO Notification
                                                                }
                                                            }
                                                        });
                                            }else{
                                                Log.d("Login_Register_Activity", "onComplete: Register not successful");
                                                //TODO Notification
                                            }
                                        }
                                    }
                                    );
                        }
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        generateLoginOrRegisterAlert();
                    }
                })
                .setView(registerView)
                .create()
                .show();
    }
}