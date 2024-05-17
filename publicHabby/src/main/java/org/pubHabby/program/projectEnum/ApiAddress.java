package org.pubHabby.program.projectEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiAddress {

    customLogin("ADDRESS_CUSTOM_LOGIN","/api/login");

    private final String key;
    private final String title;
}
