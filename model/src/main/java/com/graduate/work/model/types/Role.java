package com.graduate.work.model.types;

/**
 * Enumeration representing different roles for users.
 */
public enum Role {
    /**
     * Represents the guest role. Disabled by default.
     */
    GUEST,
    /**
     * Represents a regular user role. Has read access to all SIMs. Can download reports.
     */
    USER,
    /**
     * Represents an operator role. Has full access to all SIM cards, both for reading and making changes.
     * Can download reports, read rules in the analytics block.
     */
    OPERATOR,
    /*
     * Represents a senior operator role. Has full access to all SIM cards, both for reading and making changes.
     * Can download reports, read and edit rules in the analytics block.
     */
    SENIOR_OPERATOR,
    /**
     * Represents an administrator role. Has unlimited rights, access to the settings panel, sending notifications.
     */
    ADMIN;

    public static Role getRole(String role) {
        if (role == null) {
            return null;
        }
        if (role.isEmpty()) {
            return null;
        }
        role = role.toUpperCase();
        if (Role.GUEST.name().contains(role)) {
            return GUEST;
        }
        if (Role.USER.name().contains(role)) {
            return USER;
        }
        if (Role.OPERATOR.name().contains(role)) {
            return OPERATOR;
        }
        if (Role.SENIOR_OPERATOR.name().contains(role)) {
            return SENIOR_OPERATOR;
        }
        if (Role.ADMIN.name().contains(role)) {
            return ADMIN;
        }
        return null;
    }
    
}