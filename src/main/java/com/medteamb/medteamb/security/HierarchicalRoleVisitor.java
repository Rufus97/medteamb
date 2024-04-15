package com.medteamb.medteamb.security;

public class HierarchicalRoleVisitor extends RoleVisitor {
    @Override
    protected boolean isRoleInternalHierarchicallyUpperOrEqualsTo(String requiredRole, String userRoles) {
        switch(requiredRole) {
            case "ROLE_USER":
                return "ROLE_USER".equals(userRoles);
            case "ROLE_ADMIN":
                return "ROLE_DOCTOR".equals(userRoles) || "ROLE_SECRETARY".equals(userRoles);
            case "ROLE_SUPERADMIN":
                return "ROLE_SUPERADMIN".equals(userRoles);
            default:
                return false;
        }
    }
}
