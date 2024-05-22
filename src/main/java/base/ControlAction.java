package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import customexception.InvalidLocatorExpection;
import utility.PropertiesOperation;
import constant.ConstantPath;

public class ControlAction {

	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected Actions actions;

	public static void launchBrowser() {

		PropertiesOperation propertiesOperation = null;
		String browser = System.getProperty("browserType");
		if (browser == null || browser.isEmpty()) {
			propertiesOperation = new PropertiesOperation(ConstantPath.CONFIG_FILEPATH);
			browser = propertiesOperation.getValue("browser");
		}

		if (browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("ie"))
			driver = new InternetExplorerDriver();
		else
			throw new customexception.InvalidBrowserException(
					browser + " is not supported only chrome, firefox and ie browser is supported");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(ConstantPath.WAIT));
		String url = System.getProperty("link");
		if (url == null || url.isEmpty()) {
			url = propertiesOperation.getValue("link");
		}
		driver.get(url);
	}

	protected WebElement getElement(By by, boolean isWaitRequired) {
		if (isWaitRequired)
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		else
			return driver.findElement(by);
	}

	protected WebElement getElement(WebElement element, boolean isWaitRequired) {
		if (isWaitRequired)
			return wait.until(ExpectedConditions.visibilityOf(element));
		else
			return element;
	}

	protected WebElement getElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement element = null;

		switch (locatorType.toUpperCase()) {
		case "ID":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			else
				element = driver.findElement(By.id(locatorValue));
			break;

		case "NAME":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			else
				element = driver.findElement(By.name(locatorValue));
			break;

		case "XPATH":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			else
				element = driver.findElement(By.xpath(locatorValue));
			break;

		case "LINKTEXT":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			else
				element = driver.findElement(By.linkText(locatorValue));
			break;

		case "PARTIALLINKTEXT":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			else
				element = driver.findElement(By.partialLinkText(locatorValue));
			break;

		case "TAGNAME":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			else
				element = driver.findElement(By.tagName(locatorValue));
			break;

		case "CLASSNAME":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			else
				element = driver.findElement(By.className(locatorValue));
			break;

		default:
			throw new customexception.InvalidLocatorExpection(locatorType + " is not supported");
		}
		return element;
	}

	protected void clickOnElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement element = getElement(locatorType, locatorValue, isWaitRequired);
		clickOnElement(element);
	}

	protected void clickOnElement(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (Exception e) {
			scrollToElement(element);
			element.click();
		}
	}

	protected boolean isElementClickable(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception ec) {
			return false;
		}
	}

	protected void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].ScrollIntoView[true]", element);
	}

	protected String getElementText(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement element = getElement(locatorType, locatorValue, isWaitRequired);
		return getElementText(element);
	}

	protected String getElementText(WebElement element) {
		try {
			return element.getText();
		} catch (Exception e) {
			scrollToElement(element);
			return element.getText();
		}
	}

	protected void setText(String locatorType, String locatorValue, boolean isWaitRequired, String textToBeSet) {
		WebElement element = getElement(locatorType, locatorValue, isWaitRequired);
		setText(element, textToBeSet);
	}

	protected void setText(WebElement element, String textToBeSet) {
		try {
			if (element.isEnabled())
				element.sendKeys(textToBeSet);
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(element));
			scrollToElement(element);
			element.sendKeys(textToBeSet);
		}
	}

	protected String getInputElementText(WebElement element) {
		try {
			return element.getAttribute("value");
		} catch (Exception e) {
			scrollToElement(element);
			return element.getAttribute("value");
		}
	}

	protected String getElementAttribute(String locatorType, String locatorValue, boolean isWaitRequired,
			String attribute) {
		WebElement element = getElement(locatorType, locatorValue, isWaitRequired);
		return element.getAttribute(attribute);
	}

	protected String getElementAttribute(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	protected void mouseHoverToElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement element = getElement(locatorType, locatorValue, isWaitRequired);
		actions.moveToElement(element).build().perform();
	}

	protected boolean isElementDisplayed(String locatorType, String locatorValue, boolean isWaitRequired) {
		try {
			WebElement element = getElement(locatorType, locatorValue, isWaitRequired);
			return element.isDisplayed();
		} catch (NoSuchElementException | TimeoutException se) {
			return false;
		}
	}

	protected boolean isElementDisplayed(String locatorType, String locatorValue, boolean isWaitRequired, int timeOut) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			WebElement element = getElement(locatorType, locatorValue, isWaitRequired);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	protected boolean isElementDisplayed(WebElement element) {
		WebElement e = wait.until(ExpectedConditions.visibilityOf(element));
		try {
			return e.isDisplayed();
		} catch (NoSuchElementException se) {
			return false;
		}
	}

	protected boolean isElementNotDisplayed(String locatorType, String locatorValue, boolean isWaitRequired) {
		try {
			WebElement element = getElement(locatorType, locatorValue, isWaitRequired);
			return !element.isDisplayed();
		} catch (NoSuchElementException se) {
			return true;
		}
	}

	protected void selectedOptionFromVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}

	protected void selectedOptionFromValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	protected void selectedOptionFromIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	protected Alert switchToAlert() {
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		return alert;
	}

	protected void acceptedAlertPrompt() {
		Alert alert = switchToAlert();
		alert.accept();
	}

	protected void declineAlertPrompt() {
		Alert alert = switchToAlert();
		alert.dismiss();
	}

	protected String getTextFromAlertPrompt() {
		Alert alert = switchToAlert();
		String actualAlertMsg = alert.getText();
		try {
			alert.accept();
		} catch (Exception e) {

		}
		return actualAlertMsg;
	}

	protected void setTextIntoAlertPrompt(String alertText) {
		Alert alert = switchToAlert();
		alert.sendKeys(alertText);
	}

	protected void switchToFrame(WebElement element) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	protected void switchToFrame(String idOrName) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	protected void switchToFrame(int index) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	protected void switchToWindowOrTabBasedOnTitle(String title) {
		Set<String> allWindowHandle = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandle) {
			driver.switchTo().window(currentWindowHandle);
			String currentWindowTitle = getCurrentPageTitle();
			if (title.equals(currentWindowTitle))
				break;
		}
	}

	protected String getCurrentPageURL() {
		return driver.getCurrentUrl();
	}

	protected boolean waitForTitleToBe(String title) {
		return wait.until(ExpectedConditions.titleContains(title));
	}

	protected String getCurrentPageTitle() {
		return driver.getTitle();
	}

	protected void refreshCurrentPage() {
		driver.navigate().refresh();
	}

	protected boolean waitUntilElementInvisible(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement element = getElement(locatorType, locatorValue, isWaitRequired);
		return waitUntilElementInvisible(element);
	}

	protected boolean waitUntilElementInvisible(WebElement element) {
		try {
			return wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			return true;
		}
	}

	protected boolean waitUntilElementInvisible(String locatorType, String locatorValue, boolean isWaitRequired,
			int timeOutInsec) {
		WebElement element = null;
		try {
			element = getElement(locatorType, locatorValue, isWaitRequired);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInsec));
			return wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			return true;
		}
	}

	protected WebElement smartWaitOnVisibilityOfElement(By by, int pollingTime) {
		WebElement stopBtnElement = driver.findElement(By.xpath("//button[@id='stopButton']"));
		WebElement targetElement = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(50)).ignoring(NoSuchElementException.class).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@id='progressBar' and @aria-valuenow > 75]")));

		stopBtnElement.click();
		return targetElement;

	}

	protected List<WebElement> getAllElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		List<WebElement> element = null;

		switch (locatorType.toUpperCase()) {
		case "ID":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
			else
				element = driver.findElements(By.id(locatorValue));
			break;

		case "NAME":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locatorValue)));
			else
				element = driver.findElements(By.name(locatorValue));
			break;

		case "XPATH":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));
			else
				element = driver.findElements(By.xpath(locatorValue));
			break;

		case "LINKTEXT":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locatorValue)));
			else
				element = driver.findElements(By.linkText(locatorValue));
			break;

		case "PARTIALLINKTEXT":
			if (isWaitRequired)
				element = wait
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(locatorValue)));
			else
				element = driver.findElements(By.partialLinkText(locatorValue));
			break;

		case "TAGNAME":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(locatorValue)));
			else
				element = driver.findElements(By.tagName(locatorValue));
			break;

		case "CLASSNAME":
			if (isWaitRequired)
				element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(locatorValue)));
			else
				element = driver.findElements(By.className(locatorValue));
			break;

		default:
			throw new InvalidLocatorExpection(locatorType + " is not supported");
		}
		return element;
	}

	protected List<String> getAllElementText(String locatorType, String locatorValue, boolean isWaitRequired) {
		List<WebElement> listOfWebElement = getAllElement(locatorType, locatorValue, isWaitRequired);
		List<String> listOfElementText = new ArrayList<String>();

		for (WebElement e : listOfWebElement) {
			String elementText = getElementText(e);
			listOfElementText.add(elementText);
		}

		return listOfElementText;
	}

	protected void waitForElementToBeVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForElementToBeVisible(String locatorValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
	}

	protected void waitForElementToBeVisible(WebElement element, int timeOut) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected boolean waitForElementToBeVisible(By by, int timeOut) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected WebElement waitForElementToBeClickable(String locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}

	protected boolean iselemenySelected(String locatorType, String locatorValue, Boolean isWaitRequired) {
		WebElement element = getElement(locatorType, locatorValue, isWaitRequired);
		return element.isSelected();
	}

	public static void captureScreenShot(String screenShotFile) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File scrFile = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(".//screenshots//" + screenShotFile + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void closeBrowser() {
		driver.quit();
	}
}
