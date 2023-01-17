package com.angelos.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import static com.angelos.cucumber.Utils.*;
import org.openqa.selenium.WebDriver;

public class CommonSteps {
    public static WebDriver driver;

    @And("I login with an existing account")
    public void iLoginWithExistingUser() {
        driver.findElement(LOG_IN_BUTTON_HOME_PAGE).click();
        driver.findElement(EMAIL_FIELD).sendKeys(existing_email);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(ACTION_BUTTON).click();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
