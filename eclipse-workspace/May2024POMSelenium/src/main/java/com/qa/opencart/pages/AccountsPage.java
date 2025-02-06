package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

		private WebDriver driver;
	private ElementUtil eleUtil;

	private By headers = By.xpath("//div[@id='content']/h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.xpath("//div[@id='search']//button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.ACC_PAGE_TITLE,
				AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Accounts page title is" + title);
		return title;

	}

	public boolean isLogOutLinkExist() {
		return eleUtil.isElemntDisplayed(logoutLink);
	}

	public int getTotalAccountPageHeader() {
		return eleUtil.waitForElementsVisiblity(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	}

	public List<String> getAccPageHeaders() {

		List<WebElement> headersList = eleUtil.waitForElementsVisiblity(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> headersValueList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String header = e.getText();
			headersValueList.add(header);
		}
		return headersValueList;
	}

	public ResultsPage doSearch(String searchKey) {
		System.out.println("Serach key is "+searchKey);
		WebElement serachEle = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_LONG_TIME_OUT);
		eleUtil.doSendKeys(serachEle, searchKey);
		eleUtil.doClick(searchIcon);
		return new ResultsPage(driver);	
		
	}	
	
	
}
