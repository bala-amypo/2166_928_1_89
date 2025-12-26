package com.example.demo.model;

public enum MatchType {
    EXACT,
    CONTAINS,
    REGEX;

    public static MatchType fromString(String value) {
        return MatchType.valueOf(value.toUpperCase());
    }
}
