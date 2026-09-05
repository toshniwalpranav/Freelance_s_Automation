package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThread.get() == null) {
            ChromeOptions options = new ChromeOptions();
            // options.addArguments("--headless=new"); // uncomment to run headless
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");

            WebDriver driver = new ChromeDriver(options);
            driverThread.set(driver);
        }
        return driverThread.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
}