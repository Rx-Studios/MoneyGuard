package com.rxstudios.moneyguard;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Login_Register_Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        linearLayout = findViewById(R.id.login_register_activity_general);
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

        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setPositiveButton("Login", null)
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        generateLoginOrRegisterAlert();
                    }
                })
                .setView(loginView)
                .setCancelable(false)
                .create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {
                Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText emailEditText = loginView.findViewById(R.id.alert_register_email_edittext);
                        EditText passwordEditText = loginView.findViewById(R.id.alert_register_password_edittext);
                        firebaseAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            dialogInterface.dismiss();
                                            Log.d("Login_Register_Activity", "DevMessage: onComplete: Register successful");
                                            setResult(901);
                                            finish();
                                        } else {
                                            Log.d("Login_Register_Activity", "DevMessage: onComplete: Register successful");
                                            Snackbar.make(linearLayout, "Login not successful! " + task.getException().toString().split("Exception: ")[1], Snackbar.LENGTH_SHORT)
                                                    .setBackgroundTint(Color.RED)
                                                    .setTextColor(Color.WHITE)
                                                    .show();
                                        }
                                    }
                                });
                    }
                });
            }
        });
        alertDialog.show();
    }

    public void generateRegisterAlert() {
        final View registerView = getLayoutInflater().inflate(R.layout.alert_register, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setPositiveButton("Register", null)
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        generateLoginOrRegisterAlert();
                    }
                })
                .setView(registerView)
                .setCancelable(false)
                .create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                final EditText nameEditText = registerView.findViewById(R.id.alert_register_name_edittext);
                EditText emailEditText = registerView.findViewById(R.id.alert_register_email_edittext);
                EditText passwordEditText = registerView.findViewById(R.id.alert_register_password_edittext);
                EditText password2EditText = registerView.findViewById(R.id.alert_register_password2_edittext);
                //TODO password2EditText EditWatcher
                if (passwordEditText.getText().toString().equals(password2EditText.getText().toString())) {
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
                                                                               if (task.isSuccessful()) {
                                                                                   Log.d("Login_Register_Activity", "DevMessage: onComplete: Register successful");
                                                                                   alertDialog.dismiss();
                                                                                   setResult(911);
                                                                                   finish();
                                                                               }
                                                                           }
                                                                       });
                                                           } else {
                                                               Log.d("Login_Register_Activity", "DevMessage: onComplete: Register not successful: " + task.getException());
                                                               Snackbar.make(linearLayout, "Register not successful! " + task.getException().toString().split("Exception: ")[1], Snackbar.LENGTH_SHORT)
                                                                       .setBackgroundTint(Color.RED)
                                                                       .setTextColor(Color.WHITE)
                                                                       .show();
                                                           }
                                                       }
                                                   }
                            );
                } else {
                    Log.d("Login_Register_Activity", "DevMessage: onClick: The passwords weren't the same");
                    Log.d("Login_Register_Activity", "DevMessage: onComplete: Register not successful: 2 different passwords were entered");
                    Snackbar.make(linearLayout, "Register not successful! Register not successful: 2 different passwords were entered", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.RED)
                            .setTextColor(Color.WHITE)
                            .show();
                }
            }
        });
        alertDialog.show();
    }
}