package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SignUpPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Confirmed real locators from the signup page HTML
    private final By nameField = By.id("name");
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By maleRadio = By.id("gender1");
    private final By femaleRadio = By.id("gender2");
    private final By stateDropdown = By.id("state");
    private final By hobbiesDropdown = By.id("hobbies");
    private final By signUpButton = By.cssSelector("button.submit-btn");
    private final By backToLoginLink = By.cssSelector("a.subLink");
    private final By interestLabels = By.cssSelector(".interest-div label.interest");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Scrolls the given element to the center of the viewport so it's
    // visible and interactable, even on long forms like this signup page.
    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
    }

    public void enterName(String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        scrollIntoView(field);
        field.clear();
        field.sendKeys(value);
    }

    public void enterEmail(String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        scrollIntoView(field);
        field.clear();
        field.sendKeys(value);
    }

    public void enterPassword(String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        scrollIntoView(field);
        field.clear();
        field.sendKeys(value);
    }

    public void selectGender(String gender) {
        By locator = gender.equalsIgnoreCase("Male") ? maleRadio : femaleRadio;
        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollIntoView(radio);
        radio.click();
    }

    public boolean isMaleSelectedByDefault() {
        WebElement radio = wait.until(ExpectedConditions.presenceOfElementLocated(maleRadio));
        scrollIntoView(radio);
        return radio.isSelected();
    }

    public void selectState(String stateName) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(stateDropdown));
        scrollIntoView(dropdown);
        new Select(dropdown).selectByVisibleText(stateName);
        scrollDown();
    }

    public void selectHobby(String hobbyName) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(hobbiesDropdown));
        scrollIntoView(dropdown);
        new Select(dropdown).selectByVisibleText(hobbyName);
    }

    public boolean isHobbySelected(String hobbyName) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(hobbiesDropdown));
        Select select = new Select(dropdown);
        return select.getAllSelectedOptions().stream()
                .anyMatch(option -> option.getText().equals(hobbyName));
    }

    public void checkInterest(String interestLabelText) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(interestLabels));
        List<WebElement> labels = driver.findElements(interestLabels);
        for (WebElement label : labels) {
            if (label.getText().trim().equalsIgnoreCase(interestLabelText.trim())) {
                String forAttribute = label.getAttribute("for");
                WebElement checkbox = driver.findElement(By.id(forAttribute));
                scrollIntoView(checkbox);
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
                return;
            }
        }
        throw new IllegalArgumentException("Interest not found: " + interestLabelText);
    }

    public boolean isInterestChecked(String interestLabelText) {
        List<WebElement> labels = driver.findElements(interestLabels);
        for (WebElement label : labels) {
            if (label.getText().trim().equalsIgnoreCase(interestLabelText.trim())) {
                String forAttribute = label.getAttribute("for");
                return driver.findElement(By.id(forAttribute)).isSelected();
            }
        }
        return false;
    }

    public boolean isSignUpButtonEnabled() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(signUpButton));
        scrollIntoView(button);
        return button.isEnabled();
    }

    public void clickSignUpButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        scrollIntoView(button);
        button.click();
    }

    public void clickBackToLoginLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(backToLoginLink));
        scrollIntoView(link);
        link.click();
    }

    // Plain page scroll, no element target and no assertion - just moves
    // the viewport down so subsequent elements are easier to interact with.
    public void scrollDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400);");
    }
}