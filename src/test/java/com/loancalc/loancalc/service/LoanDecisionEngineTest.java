package com.loancalc.loancalc.service;

import com.loancalc.loancalc.model.LoanDecision;
import com.loancalc.loancalc.model.LoanRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoanDecisionEngineTest {

    private LoanDecisionEngine engine;

    @BeforeEach
    public void setUp() {
        engine = new LoanDecisionEngine(new PersonProfileService(), new CreditScoreService(), new LoanValidationService());
    }

    @Test
    public void testDebtProfileRejected() {
        LoanDecision dec = engine.evaluate(new LoanRequest("49002010965", 4000, 24));

        assertFalse(dec.isApproved());
        assertEquals("Applicant has debt, loan denied", dec.getReason());
    }

    @Test
    public void testSegment1ApprovedRequestedAndMax() {
        LoanDecision dec = engine.evaluate(new LoanRequest("49002010976", 4000, 24));

        assertTrue(dec.isApproved());
        assertEquals(6000.0, dec.getApprovedAmount(), 0.01); // best across periods (100*60)
        assertEquals(60, dec.getApprovedPeriod());
    }

    @Test
    public void testSegment2CanImproveAmount() {
        LoanDecision dec = engine.evaluate(new LoanRequest("49002010987", 3000, 24));
        assertTrue(dec.isApproved());
        assertEquals(10000.0, dec.getApprovedAmount(), 0.01); // capped by max 10000
        assertEquals(60, dec.getApprovedPeriod());
    }

    @Test
    public void testSegment3High() {
        LoanDecision dec = engine.evaluate(new LoanRequest("49002010998", 9000, 12));
        assertTrue(dec.isApproved());
        assertEquals(10000.0, dec.getApprovedAmount(), 0.01); // capped by max
        assertEquals(60, dec.getApprovedPeriod());
    }

    @Test
    public void testNoSuitableAmountAndPeriodSearch() {
        // segment1, small modifier so 12 month still 1200 (too low), should try > until 60 -> 6000 available
        LoanDecision dec = engine.evaluate(new LoanRequest("49002010976", 10000, 12));
        assertTrue(dec.isApproved());
        assertEquals(6000.0, dec.getApprovedAmount(), 0.01);
        assertEquals(60, dec.getApprovedPeriod());
    }
}
