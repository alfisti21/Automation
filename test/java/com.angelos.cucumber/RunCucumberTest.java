package com.angelos.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features={"test/resources/features"},glue={"com.angelos.cucumber"})
public class RunCucumberTest {
}