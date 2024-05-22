package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlAction;
import constant.ConstantPath;

public class LibraryPage extends ControlAction {

	@FindBy(xpath = "//div[@class='button-container']/button[2]")
	private WebElement clickOnQuesBtnElement;

	@FindBy(xpath = "//button[contains(text(),'Delete ')]")
	private WebElement topDeletedBtn;

	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement deleteElement;

	@FindBy(xpath = "//div[@role='presentation' and contains(@style,'opacity: 0')]//button[text()='Delete']")
	private WebElement cancelElement;

	@FindBy(xpath = "//label[text()='Source']")
	private WebElement libraryElement;

	@FindBy(xpath = "//div[@class='libray-name-header']/parent::span/div[1]")
	private WebElement questionCountHeader;

	@FindBy(xpath = "//div[@class='loader']")
	private WebElement loaderElement;

	public LibraryPage() {
		PageFactory.initElements(driver, this);
	}

	public CreateQuestionPage clickOnQuestionElement() {
		clickOnElement(clickOnQuesBtnElement);
		return new CreateQuestionPage();
	}

	public boolean isLeftMenuOptionSelectedByText(String text) {
		String locator = "//p[text()='" + text + "']/preceding-sibling::div/*";
		return isElementDisplayed("xpath", locator, true);
	}

	public boolean isLeftMenuOptionSelectedByText(String text, int count) {
		String locator = "//p[text()='" + text + "']/preceding-sibling::div/*";
		return isElementDisplayed("xpath", locator, true, ConstantPath.FASTWAIT);
	}

	public void libraryPageToBeLoaded() {
		waitForElementToBeVisible(libraryElement);
	}

	public boolean isQuestionDisplay(String title) {
		String locator = "//label[text()= '" + title + "']";
		return isElementDisplayed("xpath", locator, true, 5);
	}

	public void deleteQuestionDisplay(String title) {
		selectQuestionByTitle(title);
		clickOnTopDeleteButton();
		clickOnDeleteButton();
	}

	public void selectQuestionByTitle(String title) {
		String locator = "//label[text()='" + title + "']/preceding-sibling::div[2]/*[name()='svg']";
		clickOnElement("xpath", locator, true);
	}

	public void clickOnTopDeleteButton() {
		clickOnElement(topDeletedBtn);
	}

	public void clickOnDeleteButton() {
		clickOnElement(deleteElement);
	}

	public void clickOnCancelButton() {
		clickOnElement(cancelElement);
	}

	public void selectedOptionFromLeftMenu(String leftMenuText) {
		String locator = "//p[text()= '" + leftMenuText + "']/preceding-sibling::div ";
		boolean flag = isLeftMenuOptionSelectedByText(leftMenuText, ConstantPath.FASTWAIT);
		if (!flag)
			clickOnElement("xpath", locator, true);
	}

	public void deSelectedOptionFromLeftMenu(String leftMenuText) {
		String locator = "//p[text()= '" + leftMenuText + "']/preceding-sibling::div ";
		boolean flag = isLeftMenuOptionSelectedByText(leftMenuText);
		if (flag)
			clickOnElement("xpath", locator, true);
	}

	public void waitUntilspinnerIsdisappeared() {
		waitUntilElementInvisible(loaderElement);
	}

	public int getQuesCountFromHeader() {
		waitUntilspinnerIsdisappeared();
		String questionCountText = getElement(questionCountHeader, true).getText();
		return Integer.parseInt(questionCountText.trim().split("\\(")[1].replace(")", ""));
	}

	public void selectSkillByText(String skillsName) {
		String locator = "//span[@data-tip='" + skillsName.toLowerCase() + "']/div";
		clickOnElement("xpath", locator, true);
	}

	public void selectQuestionType(String questionType) {
		String locator = "//p[text()='" + questionType + "']/preceding-sibling::div";
		clickOnElement("xpath", locator, true);
	}
}
