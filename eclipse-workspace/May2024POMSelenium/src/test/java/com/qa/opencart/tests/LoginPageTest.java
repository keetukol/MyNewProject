package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void loginPageUrlTest() {
		String fractionUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(fractionUrl.contains(AppConstants.LOGIN_PAGE_Fraction_URL));
		}

	@Test
	public void isLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}

	@Test
	public void isforgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority = Integer.MAX_VALUE)
	public void doLoginTest() {
		 accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACC_PAGE_TITLE);
	}
	
}
