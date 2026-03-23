package com.loancalc.loancalc.service;

import com.loancalc.loancalc.model.LoanDecision;
import com.loancalc.loancalc.model.LoanRequest;
import com.loancalc.loancalc.model.PersonProfile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanDecisionEngine {

    private final PersonProfileService personProfileService;
    private final CreditScoreService creditScoreService;
    private final LoanValidationService validationService;

    public LoanDecisionEngine(PersonProfileService personProfileService,
                              CreditScoreService creditScoreService,
                              LoanValidationService validationService) {
        this.personProfileService = personProfileService;
        this.creditScoreService = creditScoreService;
        this.validationService = validationService;
    }

    public LoanDecision evaluate(LoanRequest request) {
        // Basic null check for request
        if (request == null) {
            return new LoanDecision(false, 0, 0, 0, "Missing request body");
        }

        // Validate personal code
        if (request.getPersonalCode() == null || request.getPersonalCode().isBlank()) {
            return new LoanDecision(false, 0, 0, 0, "Personal code is required");
        }

        // Validate amount
        if (!validationService.isValidAmount(request.getRequestedAmount())) {
            return new LoanDecision(false, 0, 0, 0,
                    String.format("Requested amount must be between %.0f and %.0f", LoanValidationService.MIN_AMOUNT, LoanValidationService.MAX_AMOUNT));
        }

        // Validate period
        if (!validationService.isValidPeriod(request.getRequestedPeriod())) {
            return new LoanDecision(false, 0, 0, 0,
                    String.format("Requested period must be between %d and %d months", LoanValidationService.MIN_PERIOD, LoanValidationService.MAX_PERIOD));
        }

        // Find person profile
        Optional<PersonProfile> profileOptional = personProfileService.findByPersonalCode(request.getPersonalCode());
        if (profileOptional.isEmpty()) {
            return new LoanDecision(false, 0, 0, 0, "Personal code not found");
        }

        PersonProfile profile = profileOptional.get();

        // Check for debt
        if (profile.hasDebt()) {
            return new LoanDecision(false, 0, 0, 0, "Applicant has debt, loan denied");
        }

        int creditModifier = profile.getCreditModifier();

        // Find the maximum approved amount across all valid periods
        double bestAmount = 0;
        int bestPeriod = 0;

        for (int period = LoanValidationService.MIN_PERIOD; period <= LoanValidationService.MAX_PERIOD; period++) {
            double maxAmountForPeriod = creditModifier * (double) period;
            double approvedAmount = Math.min(maxAmountForPeriod, LoanValidationService.MAX_AMOUNT);

            if (approvedAmount >= LoanValidationService.MIN_AMOUNT) {
                if (approvedAmount > bestAmount || (approvedAmount == bestAmount && period > bestPeriod)) {
                    bestAmount = approvedAmount;
                    bestPeriod = period;
                }
            }
        }

        if (bestAmount < LoanValidationService.MIN_AMOUNT) {
            return new LoanDecision(false, 0, 0, 0, "No suitable amount and period can be approved");
        }

        double bestScore = creditScoreService.calculateScore(creditModifier, bestAmount, bestPeriod);

        return new LoanDecision(true, bestAmount, bestPeriod, bestScore,
                String.format("Maximum approved amount is %.2f for %d months.", bestAmount, bestPeriod));
    }
}
