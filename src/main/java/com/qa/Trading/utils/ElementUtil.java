package com.qa.Trading.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.Trading.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {
	
	private WebDriver driver;
	private Actions act;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	private void highlightElement(WebElement element) {
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
	}
	
	private WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		ChainTestListener.log("Locator: "+locator.toString());
		highlightElement(element);
		return element;
	}
	
	private void nullCheck(CharSequence... value) {
		if(value==null) {
			throw new RuntimeException("Value cannot be null");
		}
	}
	
	@Step("Entering value : {1} into element : {0}")
	public void doSendKeys(By locator, String value) {
		nullCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	public void doSendKeys(By locator, CharSequence... value) {
		nullCheck(value);
		getElement(locator).sendKeys(value);
	}
	
	@Step("Clicking on element: {0}")
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	@Step("Fetching the text for element : {0}")
	public String doGetText(By locator) {
		String text = getElement(locator).getText();
		System.out.println("Element text is: "+text);
		return text;
	}
	
	public String getDomAttributeValue(By locator,String attributeName) {
		nullCheck(attributeName);
		return getElement(locator).getDomAttribute(attributeName);
	}
	
	public String getDomPropertyValue(By locator, String propertyName) {
		nullCheck(propertyName);
		return getElement(locator).getDomProperty(propertyName);
	}
	
	public boolean isElementDisplayed(By locator) {
		try {
			System.out.println("Element is present on the page");
		return getElement(locator).isDisplayed();
		}
		catch (NoSuchElementException e) {
			System.out.println("Element is not present on the page");
			return false;
		}
	}
	
	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e:eleList) {
			String text = e.getText();
			if(text.length()!=0) {
			//	System.out.println(text);
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}
	
	public int getElementsCount(By locator) {
		int count = getElements(locator).size();
		System.out.println("Element count==>"+count);
		return count;
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
		}
	
	public boolean checkElementDisplayed(By locator) {
		if(getElements(locator).size()==1) {
			System.out.println("Element: "+locator+" is displayed on the page one time");
			return true;
		}
		return false;
	}
	
	public boolean checkElementDisplayed(By locator, int expEleCount) {
		if(getElements(locator).size()==expEleCount) {
			System.out.println("Element: "+locator+" is displayed on the page "+expEleCount+" times");
			return true;
		}
		return false;
	}
	
	public void clickElement(By locator, String value) {
		List<WebElement> eleList = getElements(locator);
		System.out.println(eleList.size());
		
		for(WebElement e:eleList) {
			String text = e.getText();
			System.out.println(text);
			
			if(text.contains(value)) {
				e.click();
				break;
			}
		}
	}
	
//	****************************dropdown select class ****************************************//
	
	public boolean doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		try {
			select.selectByIndex(index);
			return true;
		}
		catch (NoSuchElementException e) {
			System.out.println(index+" is not present in the dropdown");
			return false;
		}
	}
	
	public boolean doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		try {
			select.selectByValue(value);
			return true;
		}
		catch (NoSuchElementException e) {
			System.out.println(value+" is not present in the dropdown");
			return false;
		}
	}
	
	public boolean doSelectDropDownByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		try {
			select.selectByVisibleText(text);
			return true;
		}
		catch (NoSuchElementException e) {
			System.out.println(text+" is not present in the dropdown");
			return false;
		}
	}
	
	//***************** drop down utils -- non select based*****************//
	
		/**
		 * This method is used to select the choices with three different use cases:
		 * 1. Single selection: Ex - selectChoice(choice,choiceList,"choice 2 3");
		 * 2. Multiple selection: Ex - selectChoice(choice, choiceList, "choice 1","choice 2 3","choice 3","choice 7");
		 * 3. All selection: Use "all/All/ALL" to select the all choices.. Ex- selectChoice(choice, choiceList, "all");
		 * @param choice
		 * @param choiceList
		 * @param choiceValue
		 * @throws InterruptedException
		 */
		
		public void selectChoice(By choice,By choiceList, String... choiceValue) throws InterruptedException{
			doClick(choice);
			Thread.sleep(2000);
			
			List<WebElement> choices = getElements(choiceList);
			System.out.println(choices.size());
			
				for(WebElement e:choices) {
					String text = e.getText();
					System.out.println(text);
					
						for(String value:choiceValue) {
							if(text.trim().equals(value)) {
								e.click();
								break;
							}
						}
				}
			
		}
		
		public void selectAllChoices(By choice,By choiceList) throws InterruptedException {
			doClick(choice);
			Thread.sleep(2000);
		
			List<WebElement> choices = getElements(choiceList);
			System.out.println(choices.size());
		
			for(WebElement e:choices) {
				e.click();
			}
		}
		
		//*************************** Actions Util *************************//
		
		public void doMoveToElement(By locator) throws InterruptedException {
			act.moveToElement(getElement(locator)).build().perform();
			Thread.sleep(2000);
		}
		
		public void handleParentSubMenu(By parentMenu,By subMenu) throws InterruptedException {
			doMoveToElement(parentMenu);
			doClick(subMenu);
		}
		
		public void handle4LevelMenu(By level1Menu,By level2Menu,By level3Menu, By level4Menu) throws InterruptedException {
			doClick(level1Menu);
			Thread.sleep(1000);
			doMoveToElement(level2Menu);
			Thread.sleep(1000);
			doMoveToElement(level3Menu);
			Thread.sleep(1000);
			doClick(level4Menu);
		}
		
		public void doActionsSendKeys(By locator,String value) {
			act.sendKeys(getElement(locator), value).perform();
		}
		
		public void doActionsClick(By locator) {
			act.click(getElement(locator)).perform();
		}
		
		public void doSendKeysWithPause(By locator, String value, long pauseTime) {
			char val[] = value.toCharArray();
			for(char ch:val) {
				act.sendKeys(getElement(locator), String.valueOf(ch)).pause(pauseTime).perform();
			}
		}
		//Wait Utils*******************//
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page. 
		 * This does not necessarily mean that the element is visible.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		
		public WebElement waitForElementPresence(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page and visible.
		 * Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		
		@Step("Waiting for element using : {0} and timeout: {1}")
		public WebElement waitForElementVisibile(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			highlightElement(element);
			return element;
		}
		
		public void clickWithWait(By locator,int timeOut) {
			waitForElementVisibile(locator, timeOut).click();
		}
		
		/**
		 * An expectation for checking an element is visible and enabled such that you can click it.
		 * @param locator
		 * @param timeOut
		 */
		public void clickWhenReady(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}
		
		
		public void sendKeysWithWait(By locator,int timeOut, CharSequence... value) {
			waitForElementVisibile(locator, timeOut).sendKeys(value);
		}
		
		
		//** Wait for alert(JS)*****//
		public Alert waitForAlert(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.pollingEvery(Duration.ofSeconds(2))
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.withMessage("=====Alert not found====");
			return wait.until(ExpectedConditions.alertIsPresent());
		}
		public void acceptAlertWithWait(int timeOut) {
			waitForAlert(timeOut).accept();
		}
		public void dismissAlertWithWait(int timeOut) {
			waitForAlert(timeOut).dismiss();
		}
		public String getTextAlertWithWait(int timeOut) {
			return waitForAlert(timeOut).getText();
		}
		
		//** Wait for title **//
		
		public String waitForTitleContains(String fractionTitle,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			try {
				wait.until(ExpectedConditions.titleContains(fractionTitle));
				return driver.getTitle();
			}
			catch(TimeoutException e) {
				return null;
			}
			
		}
		
		public String waitForTitleIs(String Title,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			try {
				wait.until(ExpectedConditions.titleIs(Title));
				return driver.getTitle();
			}
			catch(TimeoutException e) {
				return null;
			}
			
		}
		
		//** Wait for URL**//
		public String waitForURLContains(String fractionURL,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			try {
				wait.until(ExpectedConditions.urlContains(fractionURL));
				return driver.getCurrentUrl();
			}
			catch(TimeoutException e) {
				return null;
			}
			
		}
		
		public String waitForURLToBe(String URL,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			try {
				wait.until(ExpectedConditions.urlToBe(URL));
				return driver.getCurrentUrl();
			}
			catch(TimeoutException e) {
				return null;
			}
			
		}
		
		//wait for iframes
		public void waitForFrameAndSwitchToIt(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
			
		}
		public void waitForFrameAndSwitchToIt(int index, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
			
		}
		public void waitForFrameAndSwitchToIt(String locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
			
		}
		public void waitForFrameAndSwitchToIt(WebElement frame, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
			
		}
		
		//wait for windows:
		
		public boolean waitForWindow(int expectedNoOfWindows, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			try {
				return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNoOfWindows));
			}
			catch(TimeoutException e) {
				System.out.println(expectedNoOfWindows+ " expected windows not available");
				return false;
			}
		}
		
		//Wait for WebElements
		
		/**
		 * An expectation for checking that there is at least one element present on a web page.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		
		public List<WebElement> waitForAllElementsPresence(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}
		
		/**
		 * An expectation for checking that all elements present on the web page that match the locatorare visible. 
		 * Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		
		public List<WebElement> waitForAllElementsVisibile(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			}
			catch(TimeoutException e){
				return Collections.EMPTY_LIST;
			}
		}
		
	}

