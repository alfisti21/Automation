package com.angelos.cucumber;

import io.cucumber.java.en.And;
import static com.angelos.cucumber.CommonSteps.driver;
import static com.angelos.cucumber.Utils.*;
import static com.angelos.cucumber.Utils.waitForElement;
import static org.junit.Assert.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class ProjectsSteps {
    public static String project_name = project_name_random;
    public static String description = description_random;

    @When("I create a new project")
    public void iCreateNewProject(){
        waitForElement(CREATE_PROJECT);
        driver.findElement(CREATE_PROJECT).click();
    }

    @And("^I fill \"([^\"]*)\"$")
    public void iFillFields(String whichFields) {
        switch(whichFields){
            case "name only" -> {
                driver.findElement(PROJECT_NAME_INPUT_FIELD).sendKeys(project_name);
                driver.findElement(ACTION_BUTTON).click();
                waitForElement(REQUIRED_FIELD_ERROR_MESSAGE);
                assertTrue(driver.findElement(REQUIRED_FIELD_ERROR_MESSAGE).isDisplayed());
                refresh();
            }
            case "all" -> {
                driver.findElement(PROJECT_NAME_INPUT_FIELD).sendKeys(project_name);
                driver.findElement(PROJECT_DESCRIPTION_INPUT_FIELD).sendKeys(description);
                driver.findElement(ACTION_BUTTON).click();
            }
        }
    }

    @Then("I verify project is created")
    public void iVerifyProjectCreated(){
        waitForElement(By.xpath("//span[text()='"+project_name+"']"));
        assertTrue(driver.findElement(By.xpath("//span[text()='"+project_name+"']")).isDisplayed());
    }

    @When("I click to edit the project")
    public void iClickToEditProject(){
        // The field validation would be the same as performed on previous tests
        driver.findElement(By.xpath("//span[text()='"+project_name
                +"']/ancestor::div[2]/div[@class='card-action']/a[text()='Edit']")).click();

        driver.findElement(PROJECT_NAME_INPUT_FIELD).sendKeys("Updated Name");
        driver.findElement(PROJECT_DESCRIPTION_INPUT_FIELD).sendKeys("Updated Description");
        driver.findElement(ACTION_BUTTON).click();
    }

    @Then("I verify the project has been updated")
    public void iVerifyProjectUpdated(){
        waitForElement(By.xpath("//span[text()='Updated Name']"));
        assertTrue(driver.findElement(By.xpath("//span[text()='Updated Name']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//p[text()='Updated Description']")).isDisplayed());
    }

    @When("I click to delete the project")
    public void iClickToDeleteProject() {
        driver.findElement(By.xpath("//span[text()='Updated Name']/ancestor::div[2]" +
                "/div[@class='card-action']/a[text()='Delete']")).click();
        driver.switchTo().alert().accept();
        refresh();
    }

    @Then("I verify it was deleted successfully")
    public void iVerifyProjectWasDeleted(){
        assertEquals(0, driver.findElements(By.xpath("//span[text()='Updated Name']")).size());
    }

}
