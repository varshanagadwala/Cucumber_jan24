package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlAction;

public class AssessmentPreviewPage extends ControlAction {

	@FindBy(xpath = "//span[contains(text(),'Terms of Use')]/preceding-sibling::input")
	private WebElement clickOnAgressTeamsAndCon;

	@FindBy(xpath = "//span[text()='Start Test']")
	private WebElement clickonStartBtn;

	@FindBy(xpath = "//div[@class='user-test-2-content-popup']//span[text()=\"Can't start test, Please turn on camera\"]")
	private WebElement cameraErrorPopUpDisplay;

	@FindBy(xpath = "//div[@class='user-test-2-content-popup']//span[@class='message']")
	private WebElement cameraErrorPopUpMessage;

	@FindBy(xpath = "//span[text()='Close']")
	private WebElement closeCameraPopupBtn;

	public AssessmentPreviewPage() {
		PageFactory.initElements(driver, this);
	}

	public void selectTearmsOfUse() {
		clickOnElement(clickOnAgressTeamsAndCon);
	}

	public AssessmentPage switchToAssessmentPlatform() {
		switchToWindowOrTabBasedOnTitle("EliteQA - #1 Assessment Platform");
		return new AssessmentPage();
	}

	public void clickOnSaveBtn() {
		clickOnElement(clickonStartBtn);
	}

	public boolean isCameraErrorPopupdisplay() {
		try {
			return isElementDisplayed(cameraErrorPopUpDisplay);
		} catch (NoSuchElementException | TimeoutException ce) {
			return false;
		}
	}

	public String getCameraErrorPopupMessage() {
		return getElementText(cameraErrorPopUpMessage);
	}

	public void clickOnClosePopupError() {
		clickOnElement(closeCameraPopupBtn);
	}
}
