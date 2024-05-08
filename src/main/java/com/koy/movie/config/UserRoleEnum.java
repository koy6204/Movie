package com.koy.movie.config;

public enum UserRoleEnum {

    MEMBER(Authority.MEMBER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String MEMBER = "ROLE_MEMBER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

    public static UserRoleEnum fromString(String role) {
        switch (role) {
            case "ROLE_MEMBER":
                return MEMBER;
            case "ROLE_ADMIN":
                return ADMIN;
        }

        return null;
    }
}
