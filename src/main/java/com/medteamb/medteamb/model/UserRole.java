package com.medteamb.medteamb.model;

public enum UserRole {
    ADMIN("admin"),
    PATIENT("patient"),
    DOCTOR("doctor"),
    SECRETARY("secretary");
    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}

