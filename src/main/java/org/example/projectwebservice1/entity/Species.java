package org.example.projectwebservice1.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum Species {
    DOG("dog"),
    BIRD("bird"),
    CAT("cat"),
    GOAT("GOAT"),
    SHEEP("sheep"),
    OTHER("other");
    // Add more species as needed

    private final String value;

    Species(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Species fromValue(String value) {
        for (Species species : Species.values()) {
            if (species.value.equalsIgnoreCase(value)) {
                return species;
            }
        }
        throw new IllegalArgumentException("Invalid species: " + value);
    }
    }
