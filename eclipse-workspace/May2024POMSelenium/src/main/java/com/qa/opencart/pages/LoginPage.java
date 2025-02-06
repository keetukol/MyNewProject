package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	// private By locator

	private By usename = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.className("img-responsive");
	private By registarionPage= By.linkText("Register");

	// By
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// Actions public methods
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleContains(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Login page title is :" + title);
		return title;
	}

	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContainsAndReturn(AppConstants.LOGIN_PAGE_Fraction_URL,
				AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Login page url is :" + url);
		return url;
	}

	public boolean isLogoExist() {
		return eleUtil.isElemntDisplayed(logo);

	}

	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElemntDisplayed(forgotPwdLink);

	}

	public AccountsPage doLogin(String userName, String pwd) {

		eleUtil.waitForElementVisible(usename, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
          return new AccountsPage(driver);
		
	}
	public RegisterPage navaigateToRegisterPage() {

		eleUtil.doClick(registarionPage);
        return new RegisterPage(driver);
	}

}
