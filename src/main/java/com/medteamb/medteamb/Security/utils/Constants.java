package com.medteamb.medteamb.Security.utils;

public class Constants {

    private final static String jwtKey = "5qEVmRtrLaeQa8gecyIJOCwBCzwml39pNvg0B9QWBQg";
    private final static Long jwtExpire = 80000L;

    private final static String docIDattr = "docID";
    public static String getJwtKey(){
        return jwtKey;
    }
    public static Long getJwtExpire(){
        return jwtExpire;
    }

    public static String getDocIDattr(){
        return  docIDattr;
    }
}
