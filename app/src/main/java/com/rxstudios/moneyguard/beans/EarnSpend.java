package com.rxstudios.moneyguard.beans;

public class EarnSpend {
    Type type;
    double amount;
    String currency;
    String source;

    public EarnSpend() {
    }

    public EarnSpend(Type type, double amount, String currency, String source) {
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
