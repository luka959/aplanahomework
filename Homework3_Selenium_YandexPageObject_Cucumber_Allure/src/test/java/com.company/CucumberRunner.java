package com.company;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/"},
        glue = {"com.company.steps"},
        plugin = {"com.company.utils.AllureReporter"}
)
public class CucumberRunner {
}
