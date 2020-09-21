package com.rxstudios.moneyguard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rxstudios.moneyguard.R;
import com.rxstudios.moneyguard.beans.EarnSpend;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MoneyEarnSpendListAdapter extends BaseAdapter {


    private List<EarnSpend> earnSpendList = new ArrayList<>();
    private int layoutId;
    private LayoutInflater inflater;

    public MoneyEarnSpendListAdapter(Context ctx, int layoutId, List<EarnSpend> earnSpends) {
        this.earnSpendList = earnSpends;
        this.layoutId = layoutId;
        // Um das Layout anzeigen zu können, benötigen wir eine Referenz auf den LayoutInflater
        // diesen erhalten wir mittels getSystemService, müssen das Systemservice allerdings
        // dann in einen LayoutInflater casten.
        this.inflater = (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    // Nun müssen wir dafür sorgen, dass unser Parameter bei den Events die korrekten Werte für
    // id, pos, etc. zurückliefert.

    // Anzahl der vorhandenen Elemente
    @Override
    public int getCount() {
        return earnSpendList.size();
    }

    // Liefert das Objekt an Position i aus der Liste
    @Override
    public Object getItem(int i) {
        return earnSpendList.get(i);
    }

    // Liefert die ID des Objekts bei Anwendungen mit Datenbanken
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        EarnSpend earnSpend = earnSpendList.get(i);
        View listItem = (view == null) ? inflater.inflate(this.layoutId, null) : view;
        ((TextView) listItem.findViewById(R.id.earnspendlist_type)).setText(earnSpend.getType().toString().toUpperCase().charAt(0));
        ((TextView) listItem.findViewById(R.id.earnspendlist_source)).setText(earnSpend.getSource());
        ((TextView) listItem.findViewById(R.id.earnspendlist_amount_currency)).setText(earnSpend.getAmount() + " " + earnSpend.getCurrency());
        return listItem;
    }


}
