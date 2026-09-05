package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Confirmed real locators from the site's HTML
    private final By emailField = By.id("email1");
    private final By passwordField = By.id("password1");
    private final By signInButton = By.cssSelector("button.submit-btn");
    private final By newUserSignUpLink = By.cssSelector("a.subLink");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(String baseUrl) {
        driver.get(baseUrl + "/login");
    }

    public void clickNewUserSignUpLink() {
        wait.until(ExpectedConditions.elementToBeClickable(newUserSignUpLink)).click();
    }
}