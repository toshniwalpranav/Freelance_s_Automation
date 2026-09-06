package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import Pages.LoginPage;
import utils.DriverFactory;

@CucumberOptions(
        features = {
                "src/test/resources/features/1_Homepage.feature",
                "src/test/resources/features/2_Registration.feature",
                "src/test/resources/features/3_Login.feature"
        },
        glue = {"stepdefinitions"},
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-reports.html"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    private static final String BASE_URL = "https://freelance-learn-automation.vercel.app";

    // Runs ONCE before any scenario in the entire suite - opens Chrome
    // exactly one time and lands on the login page.
    @BeforeSuite(alwaysRun = true)
    public void launchBrowserOnce() {
        WebDriver driver = DriverFactory.getDriver();
        new LoginPage(driver).open(BASE_URL);
    }

    // Runs ONCE after every scenario in the suite has finished - closes
    // Chrome exactly one time.
    @AfterSuite(alwaysRun = true)
    public void closeBrowserOnce() {
        DriverFactory.quitDriver();
    }
}