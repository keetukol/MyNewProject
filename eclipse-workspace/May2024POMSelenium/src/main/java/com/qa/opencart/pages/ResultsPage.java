package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchHeader = By.xpath("//div[@id='content']/h1");
	private By results = By.xpath("//div[@class='product-thumb']");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getResPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.RESULT_PAGE_TITLE, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		System.out.println("ResultsPage  title is :" + title);
		return title;
	}

	public String getHeaderTest() {
		String headerValue = eleUtil.waitForElementVisible(searchHeader, AppConstants.DEFAULT_LONG_TIME_OUT).getText();
		System.out.println("Header is " + headerValue);
		return headerValue;
	}

	public int getsearchHeaderCount() {
     int resultCount= eleUtil.waitForElementsVisiblity(results, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
     System.out.println("The result count is :"+resultCount);
     return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
	   System.out.println("Selecting the product name :"+ productName);	
	   eleUtil.doClick(By.linkText(productName));
	   return new ProductInfoPage(driver);
	}
}
