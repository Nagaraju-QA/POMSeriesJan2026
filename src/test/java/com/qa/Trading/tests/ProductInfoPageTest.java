package com.qa.Trading.tests;

import static com.qa.Trading.constants.AppConstants.*;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.Trading.Base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		acctPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { 
			{ "macbook", "MacBook" },
			{ "macbook", "MacBook Air" },
			{ "macbook", "MacBook Pro" },
			{ "imac", "iMac" },
			{ "samsung", "Samsung SyncMaster 941BW" },
			{ "samsung", "Samsung Galaxy Tab 10.1" } 
		};
	}

	@Test(dataProvider = "getProductData")
	public void productHeaderTest(String searchKey, String expHeader) {
		searchResPage = acctPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(expHeader);
		String actualHeaders = productInfoPage.getProductHeader();
		Assert.assertEquals(actualHeaders, expHeader);
	}

	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object[][] { 
			{ "macbook", "MacBook Air", 4},
			{ "macbook", "MacBook Pro", 4},
			{ "imac", "iMac", 3},
			{ "samsung", "Samsung SyncMaster 941BW", 1},
			{ "samsung", "Samsung Galaxy Tab 10.1", 7} 
		};
	}
	
	@Test(dataProvider = "getProductImagesData")
	public void productImageTest(String searchKey, String productName, int expImgCount) {
		searchResPage = acctPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		int imgCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(imgCount, expImgCount);
	}

	@Test
	public void productInfoTest() {
		searchResPage = acctPage.doSearch("MacBook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");

		Map<String, String> actualProductDetailsMap = productInfoPage.getProductDetailsMap();

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualProductDetailsMap.get("ProductHeader"), "MacBook Pro");
		softAssert.assertEquals(actualProductDetailsMap.get("ProductImages"), "4");
		softAssert.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actualProductDetailsMap.get("ProductPrice"), "$2,000.00");
		softAssert.assertEquals(actualProductDetailsMap.get("ExTax"), "$2,000.00");

		softAssert.assertAll();
	}
	
	@Test(dataProvider = "getProductData")
	public void addToCartTest(String searchKey, String expHeader) {
		searchResPage = acctPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(expHeader);
		String actualMsg = productInfoPage.addToCart();
		Assert.assertTrue(actualMsg.contains(EXPECTED_FRACTION_SUCCESS_MSG));
	}
	
//	@Test
//	public void totalCartTest() {
//		String total = productInfoPage.getTotalAddedItems();
//		System.out.println("Total added items in cart is: "+total);
//		Assert.assertEquals(total, "6 item(s) - $4,409.99");
//	}
}
