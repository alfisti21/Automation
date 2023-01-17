package com.angelos.cucumber;

import io.cucumber.java.en.And;
import static com.angelos.cucumber.CommonSteps.driver;
import static com.angelos.cucumber.Utils.*;
import static com.angelos.cucumber.Utils.waitForElement;
import static org.junit.Assert.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class SettingsSteps {
    /* Similar to the first test case regarding creating an account
    the validations will be the same. Also, there could be many combinations
     of changes in the Settings. For simplicity only the name is changed*/

    @And("I go to Settings")
    public void iGoToSettings(){
        waitForElement(SETTINGS_BUTTON);
        driver.findElement(SETTINGS_BUTTON).click();
        waitForElement(By.xpath("//input[@value='"+name+"']"));
    }

    @And("I modify information")
    public void iModifyInfo(){
        driver.findElement(NAME_FIELD).clear();
        driver.findElement(NAME_FIELD).sendKeys(project_name_random);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(ACTION_BUTTON).click();
    }

    @And("I verify the change")
    public void iVerifyChanges(){
        waitForElement(INFO_UPDATED_TOAST);
        assertTrue(driver.findElement(INFO_UPDATED_TOAST).isDisplayed());
    }

}
