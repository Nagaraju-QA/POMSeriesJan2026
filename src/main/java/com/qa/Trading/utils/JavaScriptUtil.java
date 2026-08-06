package com.qa.Trading.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) this.driver;

	}

	public String getTitleByJS() {
		return js.executeScript("return document.title;").toString();
	}

	public String getURLByJS() {
		return js.executeScript("return document.URL;").toString();
	}

	public void navigateToBackPage() {
		js.executeScript("history.go(-1);");
	}

	public void navigateToForwardPage() {
		js.executeScript("history.go(1);");
	}

	public void refreshBrowserByJS() {
		js.executeScript("history.go(0);");
	}

	public void generateJSAlert(String msg) {
		js.executeScript("alert('" + msg + "')");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}

	public String getPageInnerText() {
		return js.executeScript("return document.documentElement.innerText;").toString();
	}

	// Scrolling

	public void scrollPageDown() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	public void scrollPageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight,0);");
	}

	public void scrollPageDown(String height) {
		js.executeScript("window.scrollTo(0," + height + ");");
	}

	public void scrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void drawBorder(WebElement element) {
		js.executeScript("arguments[0].style.border='4px solid red';", element);
	}

	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i <= 2; i++) {
			changeColor("rgb(0,200,0)", element);// green
			changeColor(bgcolor, element);// webelement background color
		}
	}

	public void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor='" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickElementByJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

}
