package com.rxstudios.moneyguard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.rxstudios.moneyguard.beans.EarnSpend;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<EarnSpend> earnSpendList = new ArrayList<>();
    FloatingActionButton addNewEarnSpendButton;

    //User Authentication
    private FirebaseAuth firebaseUserAuth = FirebaseAuth.getInstance();
    RelativeLayout relativeLayout;

    public List<EarnSpend> getEarnSpendList() {
        return earnSpendList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Check if logged in
        if(firebaseUserAuth.getCurrentUser()==null){
            //TODO Login
            startActivityForResult(new Intent(this, Login_Register_Activity.class), 002);
        }else{
            Log.d("MainActivity", "DevMessage: onCreate: User is already logged in!");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        findViewById(R.id.addNewEarnSpendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddNewEarnSpendActivity.class);
                startActivityForResult(intent, 001);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * Resultcodes:
         * 1: ok
         *
         * 901: Login successful
         * 901: Login not successful and went out of login activity
         * 911: Register successful
         * 912: Register not successful and went out of login activity
         *
         * Requestcodes:
         * 001: Create new EarnSpend
         * 002: Login
         */
        switch(requestCode) {
            case 001:
                //Adds the new EarnSpend item to the general list
                earnSpendList.add(new Gson().fromJson(data.getStringExtra("result"), EarnSpend.class));
                break;
            case 002:
                relativeLayout = findViewById(R.id.activity_main_general);
                switch (resultCode) {
                    case 901:
                        Snackbar.make(relativeLayout, "Login successful!", Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.rgb(97,182,62))
                                .setTextColor(Color.WHITE)
                                .show();
                        break;
                    case 902:
                        Snackbar.make(relativeLayout, "ERROR: Login not successful!", Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.RED)
                                .setTextColor(Color.WHITE)
                                .show();
                        break;
                    case 911:
                        Snackbar.make(relativeLayout, "Register successful!", Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.rgb(97,182,62))
                                .setTextColor(Color.WHITE)
                                .show();
                        break;
                    case 912:
                        Snackbar.make(relativeLayout, "Register not successful!", Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.GREEN)
                                .setTextColor(Color.WHITE)
                                .show();
                        break;
                }
        }
    }
}