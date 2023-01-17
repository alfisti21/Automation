Feature: QA Assignment

  Background:
    Given I access the website

  Scenario: A new user can create an account
    And   I press the Sign Up button and land on the correct page
    When  I fill in "insufficient fields"
    And   I fill in "existing email"
    And   I fill in "wrong email format"
    And   I fill in "all fields correctly"
    Then  I verify account has been created

  Scenario: Users should be able to Add/Edit/View/Delete projects
    And   I login with an existing account
    When  I create a new project
    And   I fill "name only"
    And   I fill "all"
    Then  I verify project is created
    When  I click to edit the project
    Then  I verify the project has been updated
    When  I click to delete the project
    Then  I verify it was deleted successfully

  Scenario: Users should be able to Add/Edit/View/Delete tasks for a project
    And   I login with an existing account
    When  I create a new project
    And   I fill "all"
    Then  I verify project is created
    When  I click to add task
    Then  I verify task is created
    When  I click to edit a task
    Then  I verify task is updated
    When  I click to delete a task
    Then  I verify the task is deleted

  Scenario: Users should be able to move tasks to different status with drag and drop
    And   I login with an existing account
    When  I create a new project
    And   I fill "all"
    Then  I verify project is created
    When  I click to add task
    Then  I verify task is created
    And   I add a second task
    Then  I drag and drop a task to another column

  Scenario: Users should be able to View/Sort/Search tasks in TaskDB
    And   I login with an existing account
    And   I go to TaskDB and view the tasks
    When  I search for an existing task
    Then  It should be displayed
    When  I search for a non-existing task
    Then  No results should be displayed
    When  I sort the tasks with "ascending" order
    Then  Tasks should be in "ascending" order
    When  I sort the tasks with "descending" order
    Then  Tasks should be in "descending" order

  Scenario: Users can validate / modify their personal details in the Settings tab.
    And   I login with an existing account
    And   I go to Settings
    When  I modify information
    Then  I verify the change
