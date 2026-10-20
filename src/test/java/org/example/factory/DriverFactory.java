package org.example.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    private static final String BSPB_URL = "https://www.bspb.ru";
    private static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(30);

    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--headless=new");

        options.setAcceptInsecureCerts(true);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT);
        return driver;
    }


    public static WebDriver createDriverAndOpenMainPage() {
        WebDriver driver = createDriver();
        driver.get(BSPB_URL);
        return driver;
    }
}