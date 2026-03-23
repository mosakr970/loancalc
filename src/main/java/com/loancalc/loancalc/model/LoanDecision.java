package com.loancalc.loancalc.model;

public class LoanDecision {
    private boolean approved;
    private double approvedAmount;
    private int approvedPeriod;
    private double score;
    private String reason;

    public LoanDecision() {
    }

    public LoanDecision(boolean approved, double approvedAmount, int approvedPeriod, double score, String reason) {
        this.approved = approved;
        this.approvedAmount = approvedAmount;
        this.approvedPeriod = approvedPeriod;
        this.score = score;
        this.reason = reason;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public int getApprovedPeriod() {
        return approvedPeriod;
    }

    public void setApprovedPeriod(int approvedPeriod) {
        this.approvedPeriod = approvedPeriod;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
