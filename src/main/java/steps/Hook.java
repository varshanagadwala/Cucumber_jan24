package steps;

import base.ControlAction;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook {

	@Before
	public void beforeScenario(Scenario scenario) {
		System.out.println("scenario name : " + scenario.getName());
		System.out.println("scenario tags : " + scenario.getSourceTagNames());
		ControlAction.launchBrowser();
	}
	
	@After
	public void afterScenario(Scenario scenario) {
		if(scenario.isFailed()) {
		}
		ControlAction.closeBrowser();
	}
}
