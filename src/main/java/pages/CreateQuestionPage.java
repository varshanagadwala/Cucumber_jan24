package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlAction;

public class CreateQuestionPage extends ControlAction {

	@FindBy(xpath = "//div[@class='question-type-model-container']//label[text()='MCQ']")
	private WebElement clickOnMCQButton;

	@FindBy(xpath = "//label[text()='Save']")
	private WebElement clickOnSaveBtn;

	@FindBy(xpath = "//input[@placeholder='Your question title']")
	private WebElement enterTitle;

	@FindBy(xpath = "//input[@placeholder='Score']")
	private WebElement enterScore;

	@FindBy(xpath = "//div[@class='add-problem-input-container']//div[@contenteditable='true']")
	private WebElement enterProblem;

	@FindBy(xpath = "//div[@class='add-solution-answer-input-container']/div[@class='answers-container']/div[@class='answer-box'][1]//div[@class='se-wrapper']//p")
	private WebElement answeronetext;

	@FindBy(xpath = "//div[@class='add-solution-answer-input-container']/div[@class='answers-container']/div[@class='answer-box'][2]//div[@class='se-wrapper']//p")
	private WebElement answertwotext;

	@FindBy(xpath = "//div[@class='add-solution-answer-input-container']/div[@class='answers-container']/div[@class='answer-box'][1]/div[@class='check-box']")
	private WebElement selectAnswer;

	@FindBy(xpath = "//button[text()= 'Easy']")
	private WebElement clickOndifficultyLevel;

	@FindBy(xpath = "//input[@placeholder='Search skill here...']")
	private WebElement enterSkill;

	@FindBy(xpath = "//span[text()='java']")
	private WebElement selectJavaSkill;

	@FindBy(xpath = "//p[text()='object-oriented programming']")
	private WebElement selectprogramminglanguage;

	public CreateQuestionPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isPopDisplayByText(String message) {
		String locator = "//div[@role='alert']//div[text()= '" + message + "']";
		boolean flag = isElementDisplayed("xpath", locator, true);
		clickOnElement("xpath", locator, false);
		return flag;
	}

	public boolean closeToastPopupIfPresent(String message) {
		String locator = "//div[@role='alert']//div[text()= '" + message + "']";
		boolean flag = waitForElementToBeVisible(By.xpath(locator), 5);
		if (flag)
			clickOnElement("xpath", locator, false);
		return flag;
	}

	public void clickOnMCQ() {
		clickOnElement(clickOnMCQButton);
	}

	public void clickOnSave() {
		clickOnElement(clickOnSaveBtn);
	}

	public void enterQuestionTitle(String text) {
		setText(enterTitle, text);
	}

	public void enterScoreElement(String text) {
		setText(enterScore, text);
	}

	public void enterProblemElement(String text) {
		setText(enterProblem, text);
	}

	public void enterAnswerOneElement(String text) {
		setText(answeronetext, text);
	}

	public void enterAnswertwoElement(String text) {
		setText(answertwotext, text);
	}

	public void clickOnCorrectAnsElement(String text) {
		String locator = "//div[@class='answer-box'][" + text + "]/div[@class='check-box']";
		clickOnElement("xpath", locator, false);
	}

	public void clickOnDifficultyElement() {
		clickOnElement(clickOndifficultyLevel);
	}

	public void enterSkillElement(String text) {
		setText(enterSkill, text);
	}

	public void clickOnJavaSkillElement() {
		clickOnElement(selectJavaSkill);
	}

	public void selectProgrammminglanguageElement(String topic) {
		String locator = "//p[text()='" + topic + "']";
		clickOnElement("xpath", locator, false);
	}

	public enum Difficulty {
		EASY("Easy"), MEDIUM("Medium"), HARD("Hard");

		private String name;

		private Difficulty(String str) {
			name = str;
		}

		public String getValue() {
			return name;
		}
	}

	public void setDifficulty(Difficulty diff) {
		String locator = "//button[text()='" + diff.getValue() + "']";
		clickOnElement("XPATH", locator, true);
	}

	public void selectSkillFromSearchBox(String skill) {
		setText(enterSkill, skill);
		String locator = "//span[text()='" + skill + "']";
		clickOnElement("xpath", locator, true);
	}

}
