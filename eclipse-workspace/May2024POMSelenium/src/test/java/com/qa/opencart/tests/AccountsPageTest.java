package com.qa.opencart.tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void setUpAccPage() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void AccountPageTitleTest() {
		String actTitle = accPage.getAccountPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACC_PAGE_TITLE);
	}

	// @Test
//	public void isLogoutLinkExistTest() {
//		Assert.assertTrue(accPage.isLogOutLinkExist());
//	}

	@Test
	public void accPageHeaderCount() {
		Assert.assertEquals(accPage.getTotalAccountPageHeader(), AppConstants.ACC_PAGE_HEADERCOUNT);
	}

	@Test
	public void accPageHeaderTest() {
		List<String> actulHeaderList = accPage.getAccPageHeaders();

		Assert.assertEquals(actulHeaderList, AppConstants.ACTUAL_EXPECTED_PAGE_HEADER_LIST);
	}

	@Test
	public void doSerachTest() {
		resultsPage = accPage.doSearch("macbook");
		Assert.assertEquals(resultsPage.getResPageTitle(), AppConstants.RESULT_PAGE_TITLE);
	}

	@Test
	public void getHeaderTest() {
		String resultPgeHeader = resultsPage.getHeaderTest();
		Assert.assertEquals(resultPgeHeader, AppConstants.RESULT_PAGE_HEADER);
	}

	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] { 
			{ "macbook", 3 }, 
			{ "imac", 1 }, 
			{ "samsung", 2 }
			};
	}

	@Test(dataProvider = "getSearchKey")
	public void getResultCountTest(String serachKey, int serachCount) {
		resultsPage = accPage.doSearch(serachKey);
		Assert.assertEquals(resultsPage.getsearchHeaderCount(), serachCount);
	}

	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"macbook",	"MacBook Pro"},
		//	{"MacBook","MacBook Air"},
		//	{"iMac","iMac"}	,
		//	{"samsung","Samsung SyncMaster 941BW"},
		//	{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	
	
	@Test(dataProvider  = "getSearchData")
	public void SearchTest(String serachKey,String productName) {
		resultsPage = accPage.doSearch(serachKey);
		prodInfoPage = resultsPage.selectProduct(productName);
		Assert.assertEquals(prodInfoPage.getProductHeader(), productName);
		shoppingCartPage=prodInfoPage.addProducts("2");
		String actualTitle=	shoppingCartPage.getShoppingPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.SHOPPING_PAGE_TITLE);


		

	}

		


	}

