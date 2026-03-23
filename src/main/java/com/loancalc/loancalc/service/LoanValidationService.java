package com.loancalc.loancalc.service;

import org.springframework.stereotype.Service;

@Service
public class LoanValidationService {

    public static final double MIN_AMOUNT = 2000.0;
    public static final double MAX_AMOUNT = 10000.0;
    public static final int MIN_PERIOD = 12;
    public static final int MAX_PERIOD = 60;

    public boolean isValidAmount(double amount) {
        return amount >= MIN_AMOUNT && amount <= MAX_AMOUNT;
    }

    public boolean isValidPeriod(int period) {
        return period >= MIN_PERIOD && period <= MAX_PERIOD;
    }

    public boolean meetsScoreThreshold(double score) {
        return score >= 1.0;
    }
}
