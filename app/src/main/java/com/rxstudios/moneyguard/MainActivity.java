package com.rxstudios.moneyguard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rxstudios.moneyguard.beans.EarnSpend;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<EarnSpend> earnSpendList = new ArrayList<>();

    public List<EarnSpend> getEarnSpendList() {
        return earnSpendList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}