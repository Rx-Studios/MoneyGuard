package com.rxstudios.moneyguard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.rxstudios.moneyguard.beans.EarnSpend;
import com.rxstudios.moneyguard.enums.Currency;

import java.util.ArrayList;
import java.util.List;

public class AddNewEarnSpendActivity extends AppCompatActivity {

    EditText source;
    EditText amount;
    Spinner currency;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_earn_spend);
    }

    @Override
    protected void onStart() {
        super.onStart();
        source = findViewById(R.id.add_new_earn_spend_source_EditText);
        amount = findViewById(R.id.add_new_earn_spend_amount_EditText);
        currency = findViewById(R.id.add_new_earn_spend_currency_spinner);
        List<CharSequence> currencyList = new ArrayList<>();
        for (int i = 0; i < Currency.values().length; i++) {
            currencyList.add(Currency.values()[i].toString());
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, currencyList);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        currency.setAdapter(adapter);

        done = findViewById(R.id.add_new_earn_spend_done_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EarnSpend earnSpend = new EarnSpend();
                earnSpend.setAmount(Double.parseDouble(amount.getText().toString()));
                earnSpend.setCurrency(Currency.valueOf(currency.getSelectedItem().toString()));
                earnSpend.setSource(source.getText().toString());

                //TO Json
                String result = new Gson().toJson(earnSpend);
                Intent intent = new Intent();
                intent.putExtra("result", result);
                setResult(1, intent);
                finishActivity(001);
            }
        });
    }
}