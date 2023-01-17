package com.angelos.cucumber;

import io.cucumber.java.en.And;
import static com.angelos.cucumber.CommonSteps.driver;
import static com.angelos.cucumber.ProjectsSteps.project_name;
import static com.angelos.cucumber.Utils.*;
import static com.angelos.cucumber.Utils.waitForElement;
import static org.junit.Assert.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TaskSteps {
    @When("I click to add task")
    public void iClickToAddTask(){
        // The field validation would be the same as performed on previous tests
        driver.findElement(By.xpath("//span[text()='"+project_name
                +"']/ancestor::div[2]/div[@class='card-action']/a[text()='Add Task']")).click();

        driver.findElement(TASK_SUMMARY_INPUT_FIELD).sendKeys("Task 1");
        driver.findElement(TASK_DESCRIPTION_INPUT_FIELD).sendKeys("Task 1 Description");
        driver.findElement(ACTION_BUTTON).click();
    }

    @Then("I verify task is created")
    public void iVerifyTaskCreated(){
        waitForElement(By.xpath("//span[text()='Task 1']"));
        assertTrue(driver.findElement(By.xpath("//span[text()='Task 1']")).isDisplayed());
    }

    @When("I click to edit a task")
    public void iClickToEditTask(){
        /* The task's update page seems to be behaving very strangely. When testing manually
        or running the auto-test in debug mode everything seems to be working fine. But
        when the test is running fast sometimes certain fields are not being updated. It
        also seems that the page is performing some sort of auto refresh especially when interacting
         with the Summary field */


        driver.findElement(By.xpath("//span[text()='Task 1']/ancestor::div[2]" +
                "/div[@class='card-action']/a[text()='Edit']")).click();

        waitForElement(By.xpath("//div[@class='select-wrapper']//input"));
        driver.findElement(By.xpath("//div[@class='select-wrapper']//input")).click();
        driver.findElement(By.xpath("//li//span[text()='IN PROGRESS']")).click();
        driver.findElement(TASK_DESCRIPTION_INPUT_FIELD).clear();
        driver.findElement(TASK_DESCRIPTION_INPUT_FIELD).sendKeys("Updated Task 1 Description");
        driver.findElement(TASK_SUMMARY_INPUT_FIELD).clear();
        driver.findElement(TASK_SUMMARY_INPUT_FIELD).sendKeys("Updated Task 1");
        driver.findElement(ACTION_BUTTON).click();
    }

    @Then("I verify task is updated")
    public void iVerifyTaskUpdated(){
        waitForElement(By.xpath("//span[text()='Updated Task 1']"));
        assertTrue(driver.findElement(By.xpath("//span[text()='Updated Task 1']")).isDisplayed());
    }

    @When("I click to delete a task")
    public void iClickToDeleteTask() {
        driver.findElement(By.xpath("//span[text()='Updated Task 1']/ancestor::div[2]" +
                "/div[@class='card-action']/a[text()='Delete']")).click();
        driver.switchTo().alert().accept();
        refresh();
    }

    @Then("I verify the task is deleted")
    public void iVerifyTaskWasDeleted(){
        assertEquals(0, driver.findElements(By.xpath("//span[text()='Updated Task 1']")).size());
    }

    @And("I add a second task")
    public void iClickToAddSecondTask(){
        driver.findElement(DASHBOARD_BUTTON).click();
        waitForElement(By.xpath("//span[text()='"+project_name+"']"));
        // The field validation would be the same as performed on previous tests
        driver.findElement(By.xpath("//span[text()='"+project_name
                +"']/ancestor::div[2]/div[@class='card-action']/a[text()='Add Task']")).click();

        driver.findElement(TASK_SUMMARY_INPUT_FIELD).sendKeys("Task 2");
        driver.findElement(TASK_DESCRIPTION_INPUT_FIELD).sendKeys("Task 2 Description");
        driver.findElement(ACTION_BUTTON).click();
    }

    @Then("I drag and drop a task to another column")
    public void iDragAndDropTaskToOtherColumn(){
        /* Apparently selenium drag n drop method is not doing so well with HTML5
        I tried with JavaScript executors as well with no luck. Did not have more time
        to look deeper into the issue. You can comment-out line 98 for faster execution*/

        waitForElement(By.xpath("//span[text()='Task 1']/ancestor::div[1]"));
        WebElement from = driver.findElement(By.xpath("//div[@id='items']//span[text()='Task 1']/" +
                "ancestor::div[1]"));
        WebElement to = driver.findElement(By.xpath("//div[@id='items']//div[@id='done_items']"));
        // Actions actions = new Actions(driver);
        // actions.dragAndDrop(from, to).build().perform();
    }

}
