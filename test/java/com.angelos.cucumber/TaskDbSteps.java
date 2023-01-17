package com.angelos.cucumber;

import io.cucumber.java.en.And;
import static com.angelos.cucumber.CommonSteps.driver;
import static com.angelos.cucumber.Utils.*;
import static com.angelos.cucumber.Utils.waitForElement;
import static org.junit.Assert.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class TaskDbSteps {

    @And("I go to TaskDB and view the tasks")
    public void iGoToTaskDB(){
        waitForElement(TASK_DB_BUTTON);
        driver.findElement(TASK_DB_BUTTON).click();
        waitForElement(By.xpath("//span[text()='Task 1']"));
        assertTrue(driver.findElement(By.xpath("//span[text()='Task 1']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//span[text()='Task 2']")).isDisplayed());
    }

    @When("I search for an existing task")
    public void iSearchForExistingTask(){
        driver.findElement(TASK_SEARCH_INPUT_FIELD).sendKeys("Task 1");
    }

    @Then("It should be displayed")
    public void shouldBeDisplayed(){
        waitForElement(By.xpath("//span[text()='Task 1']"));
        assertTrue(driver.findElement(By.xpath("//span[text()='Task 1']")).isDisplayed());
    }

    @When("I search for a non-existing task")
    public void iSearchForNonExistingTask(){
        driver.findElement(TASK_SEARCH_INPUT_FIELD).clear();
        driver.findElement(TASK_SEARCH_INPUT_FIELD).sendKeys("Task 0");
    }

    @Then("No results should be displayed")
    public void nothingShouldBeDisplayed(){
        waitForElement(By.xpath("//span[text()='Task 1']"));
        assertEquals(0, driver.findElements(By.xpath("//span[text()='Task 0']")).size());
        refresh();
    }

    @When("^I sort the tasks with \"([^\"]*)\" order$")
    public void iSort(String sortOrder){
        switch(sortOrder) {
            case "ascending", "descending" -> {
                waitForElement(SORT_TASKS_BUTTON);
                driver.findElement(SORT_TASKS_BUTTON).click();
                waitForElement(By.xpath("//span[text()='Task 1']"));
            }
        }
    }

    @When("^Tasks should be in \"([^\"]*)\" order$")
    public void tasksShouldBeInOrder(String sortOrder){
        switch(sortOrder) {
            case "ascending" -> assertEquals("Task 1", driver.findElement(By.xpath("//div[@id='items']" +
                    "//div[1]/div[1]/span[@id='card_title'][1]")).getText());
            case "descending" -> assertEquals("Task 2", driver.findElement(By.xpath("//div[@id='items']" +
                    "//div[1]/div[1]/span[@id='card_title'][1]")).getText());
        }
    }

}
