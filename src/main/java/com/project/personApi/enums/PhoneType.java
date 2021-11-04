package com.project.personApi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {
    HOME("Home"),
    MOBILE("MOBILE"),
    COMMERCIAL("Commercial");

    private final String description;
}
