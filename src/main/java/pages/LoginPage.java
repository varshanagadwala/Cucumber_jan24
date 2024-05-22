package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlAction;

public class LoginPage extends ControlAction {

	@FindBy(xpath = "//span[text()='Sign In']")
	private WebElement signInElement;

	@FindBy(xpath = "//input[@placeholder='Enter email']")
	private WebElement userNameElement;

	@FindBy(xpath = "//input[@placeholder='Enter password']")
	private WebElement passwordElement;

	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginElement;

	@FindBy(xpath = "//div[text()='Authentication failed, please check your username and password.']")
	private WebElement authenticationFailedElement;

	@FindBy(xpath = "//div[@role='alert']/div[contains(text(),'email')]")
	private WebElement authenticationEmailElement;

	@FindBy(xpath = "//div[@role='alert']/div[contains(text(),'password')]")
	private WebElement authenticationPasswordElement;

	@FindBy(xpath = "(//div[@role='alert']/div)[1]")
	private WebElement genericMessageElement;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isSignInVisible() {
		return isElementDisplayed(signInElement);
	}

	public void login(String userName, String Password) {
		enterUserName(userName);
		enterPassword(Password);
		clickOnLoginBtn();
	}

	public void enterUserName(String userName) {
		waitForElementToBeVisible(userNameElement);
		userNameElement.sendKeys(userName);
	}

	public void enterPassword(String Password) {
		passwordElement.sendKeys(Password);
	}

	public void clickOnLoginBtn() {
		loginElement.click();
	}

	public boolean isAuthenticationFailureDisplay() {
		return isElementDisplayed(authenticationFailedElement);
	}

	public String getCurrentPageURL() {
		return super.getCurrentPageURL();
	}

	public boolean isEmailEmptyPopupDiasplayed(int wait) {
		try {
			return isElementDisplayed(authenticationEmailElement);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean ispasswordEmptyPopupDiasplayed(int wait) {
		try {
			return isElementDisplayed(authenticationEmailElement);
		} catch (Exception e) {
			return false;
		}
	}

	public String getErrorPopupMessage() {
		isElementDisplayed(genericMessageElement);
		String actualMessage = getElementText(genericMessageElement);
		return actualMessage;
	}

}
