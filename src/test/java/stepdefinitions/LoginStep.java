package stepdefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import Pages.LoginPage;
import utils.DriverFactory;

import java.time.Duration;

public class LoginStep {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    String urlBeforeLogin;

    // "the user is on the login page" is already defined in NavigationStep.java - not repeated here

    @When("the user logs in with email {string} and password {string}")
    public void the_user_logs_in_with_email_and_password(String email, String password) {
        urlBeforeLogin = driver.getCurrentUrl();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
    }

    @Then("the user should be navigated to another page")
    public void the_user_should_be_navigated_to_another_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(d -> !d.getCurrentUrl().equals(urlBeforeLogin));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Page source after failed login attempt:\n" + driver.getPageSource());
        }
        Assert.assertNotEquals(driver.getCurrentUrl(), urlBeforeLogin,
                "User was not navigated away from the login page after clicking Sign In");
    }
}