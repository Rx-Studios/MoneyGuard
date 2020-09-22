package com.rxstudios.moneyguard;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
            startActivity(new Intent(this, Login_Register_Activity.class));
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
         * Requestcodes:
         * 001: Create new EarnSpend
         */
        switch(requestCode){
            case 1:
                //Adds the new EarnSpend item to the general list
                earnSpendList.add(new Gson().fromJson(data.getStringExtra("result"),EarnSpend.class));
        }
    }


}