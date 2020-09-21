package com.rxstudios.moneyguard.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.rxstudios.moneyguard.MainActivity;
import com.rxstudios.moneyguard.R;
import com.rxstudios.moneyguard.adapter.MoneyEarnSpendListAdapter;
import com.rxstudios.moneyguard.beans.EarnSpend;

import java.util.List;


public class MoneyEarnSpendList extends Fragment {

    public final static String TAG = MoneyEarnSpendList.class.getSimpleName();
    ListView moneyEarnSpendList;
    List<EarnSpend> earnSpendList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: entered");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_money_earn_spend_list, container, false);
        intializeViews(view);
        return view;
    }

    private void intializeViews(View view) {
        Log.d(TAG, "intializeViews: entered");
        moneyEarnSpendList = view.findViewById(R.id.EarnSpendList);
        //Adapter
        MainActivity mainActivity = (MainActivity) getActivity();
        earnSpendList = mainActivity.getEarnSpendList();
        MoneyEarnSpendListAdapter moneyEarnSpendListAdapter = new MoneyEarnSpendListAdapter(this.getContext(), R.layout.earnspendlist_layout, earnSpendList);
        moneyEarnSpendList.setAdapter(moneyEarnSpendListAdapter);
        moneyEarnSpendListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: entered");
    }
}