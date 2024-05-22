package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlAction;

public class DashboardPage extends ControlAction {

	@FindBy(xpath = "//div[@data-tip='Total Assessments']//b")
	private WebElement assessmentElement;

	@FindBy(xpath = "//span[text()='Dashboard']")
	private WebElement DashboardMenuElement;

	@FindBy(xpath = "//button[@class='createQuestionButton']")
	private WebElement createQuestionElement;

	@FindBy(xpath = "//button[@class='createAssementButton']")
	private WebElement createAssessmentsElement;

	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isDashboardMenuVisible() {
		return isElementDisplayed(DashboardMenuElement);
	}

	public void waitForDashboardPageIsLoaded() {
		waitForElementToBeVisible(DashboardMenuElement);
	}

	public enum TestMenu {
		DASHBOARD("Dashboard"), ASSESSMENTS("Assessments"), LIBRARY("Library"), CANDIDATES("Candidates"),
		INTERVIEWS("Interviews");

		private final String value;

		private TestMenu(String menuValue) {
			this.value = menuValue;
		}

		public String getValue() {
			return value;
		}
	}

	public int getAssessmentCount() {
		WebElement e = getElement(assessmentElement, true);
		int actualAssestmentCount = Integer.parseInt(e.getText());
		return actualAssestmentCount;
	}

	public int getValueFromCards(String cardName) {
		String cardLocator = "//span[text()= '" + cardName + "']/b";
		String text = getElementText("XPATH", cardLocator, true);
		return Integer.parseInt(text);
	}

	public boolean isCardDisplayed(String name) {
		return isElementDisplayed("XPATH", "//span[text()='" + name + "']", false);
	}

	public void navigate_to(TestMenu menu) {
		clickOnElement("XPATH", "//span[text()='" + menu.getValue() + "']", false);
	}

	public boolean isMenuVisible(TestMenu menu) {
		return isElementDisplayed("XPATH", "//span[text()='" + menu.getValue() + "']", false);
	}

	public boolean isCreatedQuestionBtnIsClickable() {
		return isElementClickable(createQuestionElement);
	}

	public boolean isCreatedAssessmentBtnIsClickable() {
		return isElementClickable(createAssessmentsElement);
	}

	public String getCurrentPageURL() {
		return super.getCurrentPageURL();
	}
}
