package com.qa.Trading.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.Trading.constants.AppConstants.*;
import com.qa.Trading.utils.ElementUtil;
import com.qa.Trading.utils.StringUtils;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private final By firstName = By.id("input-firstname");
	private final By lastName = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confirmPassword = By.id("input-confirm");
	private final By subscribeYes = By.cssSelector(".radio-inline:nth-of-type(1) input[type='radio']");
	private final By subscribeNo = By.cssSelector(".radio-inline:nth-of-type(2) input[type='radio']");
	private final By agreeCheckBox = By.cssSelector("input[name='agree']");
	private final By continueButton = By.cssSelector("input[type='submit'][value='Continue']");
	private final By successMsg = By.cssSelector("div#content>h1");
	private final By logout = By.linkText("Logout");
	private final By register = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean userRegistration(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		eleUtil.sendKeysWithWait(this.firstName, MED_DEFAULT_TIME, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, StringUtils.getRandomEmailID());
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		String actualMsg = eleUtil.waitForElementVisibile(successMsg, MED_DEFAULT_TIME).getText();
		if(actualMsg.equals(REGISTER_SUCCESS_MSG)) {
			eleUtil.clickWithWait(logout, DEFAULT_TIME);
			eleUtil.clickWithWait(register, DEFAULT_TIME);
			return true;
		}
		return false;
	}
}
