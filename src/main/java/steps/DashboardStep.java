package steps;

import org.junit.Assert;

import base.ControlAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.DashboardPage;
import pages.DashboardPage.TestMenu;

public class DashboardStep {
	private DashboardPage dashboardPage;

	public DashboardStep() {
		dashboardPage = new DashboardPage();
	}

	@And("User wait for dasboardpage to be loaded")
	public void User_wait_for_dasboardpage_to_be_loaded() {
		dashboardPage.waitForDashboardPageIsLoaded();
	}

	@Then("User verify Invites Used, Total Assessments, Total Appeared, Total Qualified is visible")
	public void verifyInvites_Assessments_Appeared_Qualified() {
		Assert.assertTrue("Invites Used is not displayed", dashboardPage.isCardDisplayed("Invites Used"));
		Assert.assertTrue("Total Assessments is not displayed", dashboardPage.isCardDisplayed("Total Assessments"));
		Assert.assertTrue("Total Appeared is not displayed", dashboardPage.isCardDisplayed("Total Appeared"));
		Assert.assertTrue("Total Qualified is not displayed", dashboardPage.isCardDisplayed("Total Qualified"));
	}

	@Then("User verify Total Assessments, Total Appeared, Total Qualified card must have value 0 or more")
	public void verifyTotalAssessments_TotalAppeared_TotalQualified_0_or_more() {
		int totalAssessment = dashboardPage.getValueFromCards("Total Assessments");
		Assert.assertTrue("Total Assessments value was not 0 or more, displayed value was " + totalAssessment,
				totalAssessment >= 0);
		Assert.assertTrue("Total Appeared was not 0 or more", dashboardPage.getValueFromCards("Total Appeared") >= 0);
		Assert.assertTrue("Total Qualified was not 0 or more", dashboardPage.getValueFromCards("Total Qualified") >= 0);
	}

	@Then("User verify Assessments, Library, Candidates, Interviews tabs are visible.")
	public void verifyAssessments_Library_Candidates_Interview_tabs_are_visible() {
		Assert.assertTrue("Assessments card is not displayed", dashboardPage.isMenuVisible(TestMenu.ASSESSMENTS));
		Assert.assertTrue("Library card is not displayed", dashboardPage.isMenuVisible(TestMenu.LIBRARY));
		Assert.assertTrue("Candidates card is not displayed", dashboardPage.isMenuVisible(TestMenu.CANDIDATES));
		Assert.assertTrue("Interviews card is not displayed", dashboardPage.isMenuVisible(TestMenu.INTERVIEWS));
	}

	@Then("User verfiy Create Assessments and Create Question button is clickable.")
	public void verfiyCreateAssessments_CreateQuestion_button_is_clickable() {
		Assert.assertTrue(dashboardPage.isCreatedQuestionBtnIsClickable());
		Assert.assertTrue(dashboardPage.isCreatedAssessmentBtnIsClickable());
	}
	
	@Then("close the browser")
	public void close_the_browser() {
		System.out.println("STEP - Close the browser");
		ControlAction.closeBrowser();
	}
}
