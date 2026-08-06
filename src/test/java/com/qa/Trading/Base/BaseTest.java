package com.qa.Trading.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.Trading.factory.DriverFactory;
import com.qa.Trading.pages.AccountsPage;
import com.qa.Trading.pages.ForgottenPasswordPage;
import com.qa.Trading.pages.LoginPage;
import com.qa.Trading.pages.ProductInfoPage;
import com.qa.Trading.pages.RegisterPage;
import com.qa.Trading.pages.SearchResultsPage;
import com.qa.Trading.utils.LogUtils;

//@Listeners(ChainTestListener.class)
public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage acctPage;
	protected SearchResultsPage searchResPage; 
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	protected ForgottenPasswordPage forgottenPwdPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.initProperty();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.iniDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@BeforeMethod
	public void beforeMethod(ITestContext result) {
		LogUtils.info("--Starting the test case-- "+result.getName());
	}
	
	@AfterMethod
	public void attatchScreenshot(ITestResult result) {
		if(!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
		LogUtils.info("--Ending the test case-- "+result.getName());
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
