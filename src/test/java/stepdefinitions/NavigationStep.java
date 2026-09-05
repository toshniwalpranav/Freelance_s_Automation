package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import Pages.LoginPage;
import Pages.SignUpPage;
import utils.DriverFactory;

public class NavigationStep {

    private static final String BASE_URL = "https://freelance-learn-automation.vercel.app";

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        WebDriver driver = DriverFactory.getDriver();
        new LoginPage(driver).open(BASE_URL);
    }

    @When("the user clicks on the {string} link")
    public void the_user_clicks_on_the_link(String linkText) {
        WebDriver driver = DriverFactory.getDriver();
        if (linkText.equalsIgnoreCase("New user? Signup")) {
            new LoginPage(driver).clickNewUserSignUpLink();
        } else if (linkText.equalsIgnoreCase("Already a user? Login")) {
            new SignUpPage(driver).clickBackToLoginLink();
        } else {
            throw new IllegalArgumentException("Unknown link: " + linkText);
        }
    }

    @Then("the user should be navigated to the registration page")
    public void the_user_should_be_navigated_to_the_registration_page() {
        WebDriver driver = DriverFactory.getDriver();
        Assert.assertTrue(driver.getCurrentUrl().contains("/signup"),
                "Expected URL to contain '/signup' but was: " + driver.getCurrentUrl());
    }

    @Then("the user should be navigated to the login page")
    public void the_user_should_be_navigated_to_the_login_page() {
        WebDriver driver = DriverFactory.getDriver();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "Expected URL to contain '/login' but was: " + driver.getCurrentUrl());
    }
}