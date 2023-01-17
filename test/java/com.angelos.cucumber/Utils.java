package com.angelos.cucumber;

import net.andreinc.mockneat.unit.user.Emails;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static com.angelos.cucumber.CommonSteps.driver;
import static net.andreinc.mockneat.unit.text.Strings.strings;

public class Utils {
    public static String existing_email = "alfisti@gmail.com";
    public static String wrong_email_format = "abcd";
    public static String name = "Pete Maverick Mitchell";
    public static String password = "123456789";

    // We randomize emails to avoid getting errors for existing ones
    public static String email = Emails.emails().val();

    //We randomize project names to avoid duplicates and be able to target specific projects
    public static String project_name_random = strings().size(10).get();
    public static String description_random = strings().get();

    ////////////////////////////////////// HOME PAGE LOCATORS/////////////////////////////////////////
    public static final By SIGN_UP_BUTTON_HOME_PAGE = By.xpath("//a[@id='signup']");
    public static final By LOG_IN_BUTTON_HOME_PAGE = By.xpath("//a[@id='login']");
    public static final By DASHBOARD_BUTTON = By.xpath("//a[@id='dashboard']");
    public static final By TASK_DB_BUTTON = By.xpath("//a[@id='task_db']");
    public static final By SETTINGS_BUTTON = By.xpath("//a[@id='settings']");
    public static final By INFO_UPDATED_TOAST = By.xpath("//div[text()='User info updated successfully!']");

    ////////////////////////////////////// SIGNUP PAGE LOCATORS///////////////////////////////////////
    public static final By ACTION_BUTTON = By.xpath("//button[@name='action']");
    public static final By NAME_FIELD = By.xpath("//input[@id='fullName']");
    public static final By EMAIL_FIELD = By.xpath("//input[@id='email']");
    public static final By PASSWORD_FIELD = By.xpath("//input[@id='password']");
    public static final By COMPANY_FIELD = By.xpath("//input[@id='company']");
    public static final By ADDRESS_FIELD = By.xpath("//input[@id='address']");
    public static final By REQUIRED_FIELD_ERROR_MESSAGE = By.xpath("//p[text()='This field is required']");
    public static final By EXISTING_EMAIL_ERROR_MESSAGE =
            By.xpath("//p[contains(text(),'already exits')]");
    public static final By WRONG_EMAIL_ERROR_MESSAGE = By.xpath("//p[text()='Invalid email format']");
    public static final By SUCCESSFUL_REGISTRATION_MESSAGE =
            By.xpath("//p[text()='Successfull registration, login to start using PPMTool']");


    ////////////////////////////////////// DASHBOARD PAGE LOCATORS///////////////////////////////////////
    public static final By CREATE_PROJECT = By.xpath("//a[@href='/createProject']");


    /////////////////////////////////// CREATE PROJECT PAGE LOCATORS/////////////////////////////////////
    public static final By PROJECT_NAME_INPUT_FIELD = By.xpath("//input[@id='name']");
    public static final By PROJECT_DESCRIPTION_INPUT_FIELD = By.xpath("//input[@id='description']");


    /////////////////////////////////// CREATE PROJECT PAGE LOCATORS/////////////////////////////////////
    public static final By TASK_SUMMARY_INPUT_FIELD = By.xpath("//input[@id='summary']");
    public static final By TASK_DESCRIPTION_INPUT_FIELD = By.xpath("//textarea[@id='description']");


    /////////////////////////////////// CREATE PROJECT PAGE LOCATORS/////////////////////////////////////
    public static final By TASK_SEARCH_INPUT_FIELD = By.xpath("//input[@id='search']");
    public static final By SORT_TASKS_BUTTON = By.xpath("//a[@id='sort_tasks']");


    /////////////////////////////////// VARIOUS METHODS /////////////////////////////////////
    public static void waitForElement(By element) {
        //set the maximum waiting time depending on performance (network, UI render etc.)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void refresh() {
        driver.navigate().refresh();
    }


}
