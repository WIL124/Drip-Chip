package com.example.dripchipsystem.account.model;

public enum Role {
    ADMIN("ADMIN"), CHIPPER("CHIPPER"), USER("USER");
    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
