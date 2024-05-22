package com.example;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
		publish = true,
        features = {"/Users/varshanagadwala/java_course/Java-Assigment/Cucumber-Jan24/src/test/resources/feature/Login.feature:13"},
        plugin = {"json:/Users/varshanagadwala/java_course/Java-Assigment/Cucumber-Jan24/target/cucumber-parallel/1.json"},
        monochrome = true,
        glue = {"steps"})
public class Parallel01IT {
}