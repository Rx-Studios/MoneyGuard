package com.rxstudios.moneyguard.beans;

import com.rxstudios.moneyguard.enums.Currency;
import com.rxstudios.moneyguard.enums.Type;

public class EarnSpend {
    Type type;
    double amount;
    Currency currency;
    String source;

    public EarnSpend() {
    }

    public EarnSpend(Type type, double amount, Currency currency, String source) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.source = source;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
