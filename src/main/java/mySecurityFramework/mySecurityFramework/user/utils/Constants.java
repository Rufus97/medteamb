package mySecurityFramework.mySecurityFramework.user.utils;

public class Constants {

    private final static String jwtKey = "5qEVmRtrLaeQa8gecyIJOCwBCzwml39pNvg0B9QWBQg";
    private final static Long jwtExpire = 80000L;

    public static String getJwtKey(){
        return jwtKey;
    }
    public static Long getJwtExpire(){
        return jwtExpire;
    }
}
