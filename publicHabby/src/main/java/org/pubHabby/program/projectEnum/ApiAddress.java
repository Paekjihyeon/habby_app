package org.pubHabby.program.projectEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum ApiAddress {
    CUSTOM_LOGIN_PAGE("ADDRESS_CUSTOM_LOGIN_PAGE", "/login"),
    CUSTOM_LOGIN("ADDRESS_CUSTOM_LOGIN", "/api/login"),
    CUSTOM_LOGOUT("ADDRESS_CUSTOM_LOGOUT", "/api/logout");


    private final String key;
    private final String title;

    ApiAddress(String key, String title) {
        this.key = key;
        this.title = title;
    }
}
