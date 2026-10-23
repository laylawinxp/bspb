package org.example.factory;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.example.config.Config.*;

public class DriverFactory {

    private static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(TimeUnit.SECONDS.toSeconds(30));

    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--headless=new");

        options.setAcceptInsecureCerts(true);

        if (USE_SELENOID) {
            try {
                WebDriver driver = new RemoteWebDriver(new URL(SELENOID_URL), options);
                driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT);
                return driver;
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Неверный URL Selenoid: " + SELENOID_URL, e);
            } catch (SessionNotCreatedException e) {
                throw new IllegalStateException("Selenoid недоступен: " + SELENOID_URL, e);
            }
        } else {
            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT);
            return driver;
        }
    }


    public static WebDriver createDriverAndOpenMainPage() {
        WebDriver driver = createDriver();
        driver.get(BASE_URL);
        return driver;
    }
}