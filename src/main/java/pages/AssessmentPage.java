package pages;

import base.ControlAction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssessmentPage extends ControlAction {

	@FindBy(xpath = "//span[contains(text(),'published') and @class]")
	private WebElement publishedElement;

	@FindBy(xpath = "//div[@class='settings-list']/div[@class='group'][2]//span[@class='slider round']")
	private WebElement snapShotToggleBtn;

	@FindBy(xpath = "//span[text()='Take Snapshots via webcam every 30 second']/*[name()='svg']")
	private WebElement webcamElementOnOrOff;

	public AssessmentPage() {
		PageFactory.initElements(driver, this);
	}

	public enum TestStatus {
		PUBLISHED("published"), COMPLETED("completed"), DRAFT("draft");

		private final String value;

		private TestStatus(String statusValue) {
			this.value = statusValue;
		}

		public String GetValue() {
			return value;
		}
	}

	public int getAssessmentCountBasedOnTestStatus(TestStatus testStatus) {
		String textStatusLocator = "//span[text()='" + testStatus.GetValue() + "']";
		String text = getElementText("Xpath", textStatusLocator, true);
		return Integer.parseInt(text.split(" ")[1].replace("(", "").replace(")", ""));
	}

	public void waitForAssessmentPageToBeLoaded() {
		waitForElementToBeVisible(publishedElement);
	}

	public void clickOnAssessmentByText(String assessmentQues) {
		String locator = "//span[text()='" + assessmentQues + "']";
		clickOnElement("xpath", locator, true);
	}

	public enum TooggleStatus {
		ON, OFF
	}

	public void setSnapShotToggle(TooggleStatus status) {
		String value = getElementAttribute(webcamElementOnOrOff, "height");
		if (status.toString().equals("ON")) {
			if (value.equals("16"))
				clickOnElement(snapShotToggleBtn);
		} else {
			if (value.equals("17"))
				clickOnElement(snapShotToggleBtn);
		}
	}

	public boolean isPopDisplayByText(String message) {
		String locator = "//div[@role='alert']//div[text()= '" + message + "']";
		boolean flag = isElementDisplayed("xpath", locator, true);
		clickOnElement("xpath", locator, false);
		return flag;
	}

	public void clickOnBtnByText(String btnText) {
		String locator = "//span[text()='" + btnText + "']/parent::button";
		clickOnElement("xpath", locator, false);
	}

	public AssessmentPreviewPage switchToExcellioPortalTab() {
		switchToWindowOrTabBasedOnTitle("Excellio Candidate Assesment Portal");
		return new AssessmentPreviewPage();
	}

	public AssessmentPreviewPage clickOnPreviewAndSwitchToPreviewPage() {
		clickOnBtnByText("Test Preview");
		return switchToExcellioPortalTab();
	}
}
