	package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.FrameWorkException;

public class ElementUtil {
	private static final String DEFAULT_ALERT_TIME_OUT_MESSAGE = "Time out ...Alert is not found";
	private WebDriver driver;
	private Actions act;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
	}

	public boolean isElemntDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("No element is dispalyed");

		}
		return false;

	}

	public String getElemntText(By Locator) {

		String text = getElement(Locator).getText();

		if (text != null) {
			return text;
		} else {
			System.out.println("Element text is null" + text);
			return null;

		}
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	public void doSendKeys(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
     	}

	public void doSendKeys(By locator, CharSequence... value) {
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(By locator, int timeOuts, String value) {
		waitForElementVisible(locator, timeOuts).sendKeys(value);
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(By locator, int timeOuts) {
		waitForElementVisible(locator, timeOuts).click();
	}

	public boolean isEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public boolean isSelected(By locator) {
		return getElement(locator).isSelected();
	}

	public String doElementGetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	public int getElemntCount(By locator) {
		return getElements(locator).size();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public boolean doSearch(By serachField, By suggestion, String searchKey, String matchValue)
			throws InterruptedException {
		boolean flag = false;
		doSendKeys(serachField, searchKey);
		Thread.sleep(5000);
		List<WebElement> suggestElemntList = getElements(suggestion);
		int totalSuggestion = suggestElemntList.size();
		System.out.println("Total no  of suggestion :" + totalSuggestion);
		if (totalSuggestion == 0) {
			System.out.println("No suggestion found...	");
			throw new FrameWorkException("No suggestion FOUND...");
		}

		for (WebElement e : suggestElemntList) {
			String List = e.getText();
			System.out.println(List);
			if (List.contains(matchValue)) {
				e.click();
				flag = true;
				break;
			}

		}
		if (flag) {
			System.out.println(matchValue + " is found");
			return true;

		} else {
			System.out.println(matchValue + " is not found");
		}
		return false;

	}

	public boolean isElementNotPresent(By locator) {
		if (getElemntCount(locator) == 0) {
			return true;
		}
		return false;
	}

	public boolean isElementPresentMultipleTimes(By locator) {
		if (getElemntCount(locator) >= 1) {
			return true;
		}
		return false;
	}

	public boolean isElementPresent(By locator, int expectedElementCount) {
		if (getElemntCount(locator) == expectedElementCount)
			return true;
		{
			return false;
		}
	}

	public boolean isElementPresent(By locator) {
		if (getElemntCount(locator) == 1)
			return true;
		{
			return false;
		}
	}
	// Select Class

	public void selectByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);

	}

	public void selectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);

	}

	public void selectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);

	}

	/**
	 * 
	 * @param parentMenu
	 * @param ChildMenu
	 * @throws InterruptedException
	 */

	// ****************Actions uTIL*************
	public void parentChileMenu(String parentMenu, String ChildMenu) throws InterruptedException {
		act.moveToElement(getElement(By.xpath("//*[text()='" + parentMenu + "']"))).perform();
		Thread.sleep(1000);
		doClick(By.xpath("//div[text()='" + ChildMenu + "']"));

	}

	public void parentChileMenu(By parentMenu, By ChildMenu) throws InterruptedException {
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(1000);
		doClick(ChildMenu);

	}

	public void ParentChildMenu(By level1, By level2, By level3, By level4) throws InterruptedException {
		doClick(level1);
		Thread.sleep(1000);
		act.moveToElement(getElement(level2)).perform();
		Thread.sleep(1000);
		act.moveToElement(getElement(level3)).perform();
		Thread.sleep(1000);
		doClick(level4);

	}

	/**
	 * Actions SendKeys
	 * 
	 * @param locator
	 * @param value
	 */
	public void actionSendkeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	// **********Wait Util****************

	public WebElement waitForElementPresence(By locator, int timeOuts) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/*
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that isgreater than 0
	 * 
	 */
	public WebElement waitForElementVisible(By locator, int timeOuts) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public WebElement waitForElementVisible(By locator, int timeOuts, int intervaltime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(intervaltime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locatorare visible. Visibility means that the elements are not only
	 * displayed but also have a heightand width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForElementsVisiblity(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void waitElementToBeClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public String waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
			}
		} catch (Exception e) {
			System.out.println("title is not found within : " + timeOut);
		}
		return null;

	}

	public String waitForTitleContainsAndReturn(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.titleContains(titleFraction));
			return driver.getTitle();
		} catch (TimeoutException e) {
			System.out.println("title is not matched");
			return "-1";
		}
	}



	public String waitForURLContainsAndReturn(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			wait.until(ExpectedConditions.urlContains(urlFraction));	
				return driver.getCurrentUrl();
			}
		 catch (TimeoutException e) {
			System.out.println("url fraction is not found within : " + timeOut);
			return "-1";
		}		

	}

	public String waitForURLContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
		} catch (Exception e) {
			System.out.println("url fraction is not found within : " + timeOut);
		}
		return null;

	}
	
	public String waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleIs(title))) {
				return driver.getTitle();
			}
		} catch (Exception e) {
			System.out.println("title is not found within : " + timeOut);
		}
		return null;

	}

	public String waitForURLIs(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlToBe(url))) {
				return driver.getCurrentUrl();
			}
		} catch (Exception e) {
			System.out.println("url is not found within : " + timeOut);
		}
		return null;

	}

	public Alert waitForJSAlertWithFluentWait(int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoAlertPresentException.class)
				.withMessage(DEFAULT_ALERT_TIME_OUT_MESSAGE);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public Alert waitForJSAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		return waitForJSAlert(timeOut).getText();
	}

	public void acceptAlert(int timeOut) {
		waitForJSAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForJSAlert(timeOut).dismiss();
	}

	public void alertSendKeys(int timeOut, String value) {
		waitForJSAlert(timeOut).sendKeys(value);
	}

	public boolean waitForWindow(int totalNumberOfWindowsToBe, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.numberOfWindowsToBe(totalNumberOfWindowsToBe));
	}

	public void waitForFrameAndSwitchToIt(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameAndSwitchToIt(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameAndSwitchToIt(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

}
