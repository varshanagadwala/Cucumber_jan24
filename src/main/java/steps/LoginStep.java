package steps;

import org.junit.Assert;

import base.ControlAction;
import constant.ConstantPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.LoginPage;
import utility.PropertiesOperation;

public class LoginStep {
	LoginPage loginPage;
	DashboardPage dashboardPage;

	@Given("User is on a login page")
	public void user_is_on_a_login_page() {
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
	}

	@When("User enter username as {string}")
	public void user_enter_username_as(String string) {
		System.out.println("STEP - Enter valid username");
		loginPage.enterUserName(string);
	}

	@When("User enter password as {string}")
	public void user_enter_password_as(String string) {
		System.out.println("STEP - Enter valid password");
		loginPage.enterPassword(string);
	}

	@When("User click on login button")
	public void user_click_on_login_button() {
		System.out.println("STEP - Enter valid login");
		loginPage.clickOnLoginBtn();
	}

	@Then("User should be on dashboard page")
	public void user_should_be_on_dashboard_page() {
		System.out.println("VERIFY1 - Dashboard page is displayed");
		Assert.assertTrue(dashboardPage.isDashboardMenuVisible());

		System.out.println("VERIFY2- Dashboard page is displayed");
		String currentURL = dashboardPage.getCurrentPageURL();
		Assert.assertTrue(currentURL.contains("dashboard"));
	}

	@Then("User verify authentication failure pop is display")
	public void user_verify_authentication_failure_pop_is_display() {
		System.out.println("VERIFY - Authentication Failure popup display");
		Assert.assertTrue("Authentication Failure popup is not visible", loginPage.isAuthenticationFailureDisplay());
	}

	@Then("User verify user is on login page")
	public void user_verify_user_is_on_login_page() {
		System.out.println("VERIFY - login page is still visible");
		String currentURL = loginPage.getCurrentPageURL();
		Assert.assertTrue(currentURL.endsWith("login"));
	}

	@When("User login using valid credentials")
	public void user_login_using_valid_credentials() {
		PropertiesOperation propOperation = new PropertiesOperation(ConstantPath.CONFIG_FILEPATH);
		String username = propOperation.getValue("username");
		String password = propOperation.getValue("password");
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginBtn();
	}

	@Then("User verify login is {string}")
	public void verfiyLogin(String status) {
		if (status.equals("successful")) {
			user_should_be_on_dashboard_page();
		} else {
			user_verify_user_is_on_login_page();
		}
	}

	@Then("User verify message {string} is displayed")
	public void verifyMessageIsDissplay(String message) {
		if (!message.isEmpty()) {
			String actualMessage = loginPage.getErrorPopupMessage();
			Assert.assertEquals(actualMessage, message);
		}
	}
}
