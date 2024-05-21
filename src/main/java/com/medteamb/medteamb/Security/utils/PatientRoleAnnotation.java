package com.medteamb.medteamb.Security.utils;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PatientRoleAnnotation {

    String text = "USER";
}
