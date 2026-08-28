package com.qa.Trading.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.Trading.constants.AppConstants.*;
import com.qa.Trading.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotpwdLink = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("getting login page tilte")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIME);
		return title;
	}
	
	@Step("getting login page URL")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIME);
		System.out.println(url);
		return url;
	}
	
	@Step("checking forgot password link")
	public boolean isForgotPwdLinkText() {
		return eleUtil.waitForElementVisibile(forgotpwdLink, DEFAULT_TIME).isDisplayed();
	
	}
	
	@Step("logging with valid username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.sendKeysWithWait(email, DEFAULT_TIME, username);
		eleUtil.doSendKeys(password, pwd); 
		eleUtil.doClick(loginBtn);
		
		String title = driver.getTitle();
		System.out.println("Account page title: "+title);
		
		return new AccountsPage(driver);
	}
	
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWithWait(registerLink, DEFAULT_TIME);
		return new RegisterPage(driver);
	}
	
	public ForgottenPasswordPage clickForgottenPassword() {
		eleUtil.clickWithWait(forgotpwdLink, DEFAULT_TIME);
		return new ForgottenPasswordPage(driver);
	}
	
}
