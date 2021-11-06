package com.project.personApi.utils;

import com.project.personApi.dto.request.PhoneDTO;
import com.project.personApi.entity.Person;
import com.project.personApi.entity.Phone;
import com.project.personApi.enums.PhoneType;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "11999999999";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final Integer PHONE_ID = 1;

    public static PhoneDTO createFakeDTO(){
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity(){
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

}
