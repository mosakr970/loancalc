package com.loancalc.loancalc.model;

public class LoanRequest {
    private String personalCode;
    private double requestedAmount;
    private int requestedPeriod;

    public LoanRequest() {
    }

    public LoanRequest(String personalCode, double requestedAmount, int requestedPeriod) {
        this.personalCode = personalCode;
        this.requestedAmount = requestedAmount;
        this.requestedPeriod = requestedPeriod;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public int getRequestedPeriod() {
        return requestedPeriod;
    }

    public void setRequestedPeriod(int requestedPeriod) {
        this.requestedPeriod = requestedPeriod;
    }
}
