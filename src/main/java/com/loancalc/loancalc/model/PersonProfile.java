package com.loancalc.loancalc.model;

public class PersonProfile {
    private final String personalCode;
    private final int creditModifier;
    private final boolean hasDebt;

    public PersonProfile(String personalCode, int creditModifier, boolean hasDebt) {
        this.personalCode = personalCode;
        this.creditModifier = creditModifier;
        this.hasDebt = hasDebt;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public int getCreditModifier() {
        return creditModifier;
    }

    public boolean hasDebt() {
        return hasDebt;
    }
}
