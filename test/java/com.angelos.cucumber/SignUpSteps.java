package com.angelos.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import static com.angelos.cucumber.CommonSteps.driver;
import static com.angelos.cucumber.Utils.*;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignUpSteps {
    String random_email = email;

    @Given("I access the website")
    public void openTheBrowserAndLaunchTheWebsite() {
        // you need to provide the location of chromedriver.exe on your machine
        // easily the below can be altered to run with Firefox
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Angelos Ladopoulos\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://node-fs-app.herokuapp.com");
    }

    @And("I press the Sign Up button and land on the correct page")
    public void iPressSignUp() {
        driver.findElement(SIGN_UP_BUTTON_HOME_PAGE).click();
        assertTrue(driver.findElement(ACTION_BUTTON).isDisplayed());
    }

    @When("^I fill in \"([^\"]*)\"$")
    public void iFillInFields(String whichFields) {

        switch (whichFields) {
            case "insufficient fields" -> {
                driver.findElement(NAME_FIELD).sendKeys(name);
                driver.findElement(EMAIL_FIELD).sendKeys(random_email);
                driver.findElement(ACTION_BUTTON).click();
                waitForElement(REQUIRED_FIELD_ERROR_MESSAGE);
                assertTrue(driver.findElement(REQUIRED_FIELD_ERROR_MESSAGE).isDisplayed());
                refresh();
            }
            case "existing email" -> {
                driver.findElement(NAME_FIELD).sendKeys(name);
                driver.findElement(EMAIL_FIELD).sendKeys(existing_email);
                driver.findElement(PASSWORD_FIELD).sendKeys(password);
                driver.findElement(ACTION_BUTTON).click();
                waitForElement(EXISTING_EMAIL_ERROR_MESSAGE);
                assertTrue(driver.findElement(EXISTING_EMAIL_ERROR_MESSAGE).isDisplayed());
                refresh();
            }
            case "wrong email format" -> {
                driver.findElement(NAME_FIELD).sendKeys(name);
                driver.findElement(EMAIL_FIELD).sendKeys(wrong_email_format);
                driver.findElement(PASSWORD_FIELD).sendKeys(password);
                driver.findElement(ACTION_BUTTON).click();
                waitForElement(WRONG_EMAIL_ERROR_MESSAGE);
                assertTrue(driver.findElement(WRONG_EMAIL_ERROR_MESSAGE).isDisplayed());
                refresh();
            }
            case "all fields correctly" -> {
                driver.findElement(NAME_FIELD).sendKeys(name);
                driver.findElement(EMAIL_FIELD).sendKeys(random_email);
                driver.findElement(PASSWORD_FIELD).sendKeys("123456789");
                driver.findElement(COMPANY_FIELD).sendKeys("MyCompany");
                driver.findElement(ADDRESS_FIELD).sendKeys("Address 13");
                driver.findElement(ACTION_BUTTON).click();
            }
        }
    }

    @Then("I verify account has been created")
    public void iVerifyAccountCreated() {
        waitForElement(SUCCESSFUL_REGISTRATION_MESSAGE);
        assertTrue(driver.findElement(SUCCESSFUL_REGISTRATION_MESSAGE).isDisplayed());
    }

}
