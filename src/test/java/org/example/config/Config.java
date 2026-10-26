package org.example.config;

public class Config {
    public static final String BASE_URL = "https://www.bspb.ru";
    public static final String SELENOID_URL = System.getProperty("selenoidUrl", "http://localhost:4444/wd/hub");
    public static final boolean USE_SELENOID = Boolean.parseBoolean(System.getProperty("useSelenoid", "false"));
}
