package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.tagName("h1");
	private By productQuantity = By.id("input-quantity");
	private By addToCartBtn=By.id("button-cart");
	private By shoppingCart=By.linkText("shopping cart");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getProductPageTitle() {

		String title = eleUtil.waitForTitleIs(AppConstants.PRODUCT_PAGE_TITLE, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		System.out.println("ProductPage	  title is :" + title);
		return title;
	}

	public String getProductHeader() {
		String productHeaderValue = eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_SHORT_TIME_OUT)
				.getText();
		System.out.println("Product header is ------>:" + productHeaderValue);
		return productHeaderValue;
	}
	
	public ShoppingCartPage addProducts(String value) {
	WebElement ProdQuaty=	eleUtil.waitForElementPresence(productQuantity, AppConstants.DEFAULT_SHORT_TIME_OUT);
		eleUtil.doSendKeys(ProdQuaty,value);
		eleUtil.doClick(addToCartBtn);
		eleUtil.doClick(shoppingCart);
		return new ShoppingCartPage(driver);
		
	}

}
