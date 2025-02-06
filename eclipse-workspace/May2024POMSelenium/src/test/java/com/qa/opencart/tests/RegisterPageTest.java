package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;


public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetUp() {
		regiterPage = loginPage.navaigateToRegisterPage();
	}

	public String userRegister() {
		return "uiautomation"+System.currentTimeMillis()+"@open.com";
	}
	
	@DataProvider
	public Object[][] getRegisterData()
	{
		return new Object[][] {
			{"Preethi","Kumari","9845465712","123456","Yes"},
			{"Rekha","srinath","9845465712","123456","Yes"},
			{"Alka","Kumari","9845465712","123456","Yes"},
			{"Naveen","Singh","9845465712","123456","Yes"},
			
		};
	}
	
	@Test(dataProvider="getRegisterData")
	public void registerUser(String name,String lastname,String PhoneNo,String password, String subsribe) {
		Assert.assertTrue(
				regiterPage.userRegister(name, lastname, userRegister(), PhoneNo, password,subsribe));

	}

}
