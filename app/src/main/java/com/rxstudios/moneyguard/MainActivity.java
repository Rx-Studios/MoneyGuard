package com.rxstudios.moneyguard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rxstudios.moneyguard.beans.EarnSpend;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<EarnSpend> earnSpendList = new ArrayList<>();
    FloatingActionButton addNewEarnSpendButton;

    public List<EarnSpend> getEarnSpendList() {
        return earnSpendList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
}