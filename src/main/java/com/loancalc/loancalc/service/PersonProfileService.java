package com.loancalc.loancalc.service;

import com.loancalc.loancalc.model.PersonProfile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonProfileService {

    private final Map<String, PersonProfile> profileByCode = new HashMap<>();

    public PersonProfileService() {
        profileByCode.put("49002010965", new PersonProfile("49002010965", 0, true));
        profileByCode.put("49002010976", new PersonProfile("49002010976", 100, false));
        profileByCode.put("49002010987", new PersonProfile("49002010987", 300, false));
        profileByCode.put("49002010998", new PersonProfile("49002010998", 1000, false));
    }

    public Optional<PersonProfile> findByPersonalCode(String personalCode) {
        if (personalCode == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(profileByCode.get(personalCode));
    }
}
