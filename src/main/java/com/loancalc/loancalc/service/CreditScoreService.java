package com.loancalc.loancalc.service;

import org.springframework.stereotype.Service;

@Service
public class CreditScoreService {

    public double calculateScore(int creditModifier, double amount, int period) {
        if (amount <= 0 || period <= 0) {
            return 0.0;
        }
        return (creditModifier / amount) * period;
    }
}
