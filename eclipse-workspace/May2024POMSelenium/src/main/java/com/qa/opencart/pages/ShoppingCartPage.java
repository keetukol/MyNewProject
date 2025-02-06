package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getShoppingPageTitle() {

		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.SHOPPING_PAGE_TITLE,
				AppConstants.DEFAULT_MEDIUM_TIME_OUT);

		System.out.println("Shopping page title is" + title);
		return title;
	}

}
