package com.loancalc.loancalc.controller;

import com.loancalc.loancalc.model.LoanDecision;
import com.loancalc.loancalc.model.LoanRequest;
import com.loancalc.loancalc.service.LoanDecisionEngine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loan")
@CrossOrigin(origins = "*")
public class LoanController {

    private final LoanDecisionEngine loanDecisionEngine;

    public LoanController(LoanDecisionEngine loanDecisionEngine) {
        this.loanDecisionEngine = loanDecisionEngine;
    }

    @PostMapping("/decision")
    public ResponseEntity<LoanDecision> getDecision(@RequestBody LoanRequest loanRequest) {
        LoanDecision decision = loanDecisionEngine.evaluate(loanRequest);
        return ResponseEntity.ok(decision);
    }
}
