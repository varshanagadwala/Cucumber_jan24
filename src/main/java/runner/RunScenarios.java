package runner;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/feature", 
		glue = "steps", 
		tags = "@loginDataProvider",
		publish = true,
		dryRun = false, 
		monochrome = true, 
		plugin = {"html:target/cucumber-reports/Cucumber.html"}
)

public class RunScenarios {

	@Before
	public void before() {
		System.out.println("This is before");
	}

	@After
	public void after() {
		System.out.println("This is after");
	}

}
