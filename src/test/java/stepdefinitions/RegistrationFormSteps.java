package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import Pages.SignUpPage;
import utils.DriverFactory;

public class RegistrationFormSteps {

    private SignUpPage getSignUpPage() {
        WebDriver driver = DriverFactory.getDriver();
        return new SignUpPage(driver);
    }

    @When("the user enters {string} in the {string} field")
    public void the_user_enters_value_in_the_field(String value, String fieldName) {
        SignUpPage signUpPage = getSignUpPage();
        switch (fieldName.toLowerCase()) {
            case "name":
                signUpPage.enterName(value);
                break;
            case "email":
                signUpPage.enterEmail(value);
                break;
            case "password":
                signUpPage.enterPassword(value);
                break;
            default:
                throw new IllegalArgumentException("Unknown field: " + fieldName);
        }
    }

    @And("the user selects {string} from the {string} dropdown")
    public void the_user_selects_value_from_the_dropdown(String value, String dropdownName) {
        SignUpPage signUpPage = getSignUpPage();
        if (dropdownName.equalsIgnoreCase("State")) {
            signUpPage.selectState(value);
        } else if (dropdownName.equalsIgnoreCase("Hobbies")) {
            signUpPage.selectHobby(value);
        } else {
            throw new IllegalArgumentException("Unknown dropdown: " + dropdownName);
        }
    }

    @And("the user selects {string} and {string} from the {string} dropdown")
    public void the_user_selects_two_values_from_the_dropdown(String value1, String value2, String dropdownName) {
        SignUpPage signUpPage = getSignUpPage();
        if (dropdownName.equalsIgnoreCase("Hobbies")) {
            signUpPage.selectHobby(value1);
            signUpPage.selectHobby(value2);
        } else {
            throw new IllegalArgumentException("Unknown dropdown: " + dropdownName);
        }
    }

    @And("the user checks the interest {string}")
    public void the_user_checks_the_interest(String interestName) {
        getSignUpPage().checkInterest(interestName);
    }

    @And("the user scrolls down the page")
    public void the_user_scrolls_down_the_page() {
        getSignUpPage().scrollDown();
    }

    @Then("the interest {string} should be checked")
    public void the_interest_should_be_checked(String interestName) {
        Assert.assertTrue(getSignUpPage().isInterestChecked(interestName),
                "Expected interest to be checked: " + interestName);
    }

    @Then("the hobbies {string} and {string} should be selected")
    public void the_hobbies_should_be_selected(String hobby1, String hobby2) {
        SignUpPage signUpPage = getSignUpPage();
        Assert.assertTrue(signUpPage.isHobbySelected(hobby1),
                "Expected hobby to be selected: " + hobby1);
        Assert.assertTrue(signUpPage.isHobbySelected(hobby2),
                "Expected hobby to be selected: " + hobby2);
    }

    @Then("the {string} gender radio button should be selected by default")
    public void the_gender_radio_button_should_be_selected_by_default(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            Assert.assertTrue(getSignUpPage().isMaleSelectedByDefault(),
                    "Expected Male radio button to be selected by default");
        } else {
            Assert.fail("No default-selection check implemented for gender: " + gender);
        }
    }

    @Then("the {string} button should be disabled")
    public void the_button_should_be_disabled(String buttonName) {
        if (buttonName.equalsIgnoreCase("Sign up")) {
            Assert.assertFalse(getSignUpPage().isSignUpButtonEnabled(),
                    "Expected Sign up button to be disabled");
        } else {
            throw new IllegalArgumentException("Unknown button: " + buttonName);
        }
    }

    @Then("the {string} button should be enabled")
    public void the_button_should_be_enabled(String buttonName) {
        if (buttonName.equalsIgnoreCase("Sign up")) {
            Assert.assertTrue(getSignUpPage().isSignUpButtonEnabled(),
                    "Expected Sign up button to be enabled");
        } else {
            throw new IllegalArgumentException("Unknown button: " + buttonName);
        }
    }

    @And("the user clicks the {string} button")
    public void the_user_clicks_the_button(String buttonName) {
        if (buttonName.equalsIgnoreCase("Sign up")) {
            getSignUpPage().clickSignUpButton();
        } else {
            throw new IllegalArgumentException("Unknown button: " + buttonName);
        }
    }

}