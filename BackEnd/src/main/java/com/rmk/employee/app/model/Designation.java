package com.rmk.employee.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Designation {

    @JsonProperty("Developer")
    DEVELOPER("Developer"),

    @JsonProperty("Senior Developer")
    SENIOR_DEVELOPER("Senior Developer"),

    @JsonProperty("Manager")
    MANAGER("Manager"),

    @JsonProperty("Team Lead")
    TEAM_LEAD("Team Lead"),

    @JsonProperty("VP")
    VP("VP"),

    @JsonProperty("CEO")
    CEO("CEO");

    private final String name;

    Designation(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
