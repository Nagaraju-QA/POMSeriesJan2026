package com.qa.Trading.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.Trading.exceptions.BrowserException;

public class DriverFactory {

	WebDriver driver;
	protected Properties prop;
	OptionsManager optManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static Logger log = LogManager.getLogger(DriverFactory.class);

	/**
	 * This method is used to initialize driver on the basis of browser name passed
	 * 
	 * @param browserName
	 * @return
	 */

	public WebDriver iniDriver(Properties prop) {
		this.prop = prop;
		log.info("Properties :" + prop);
		String browserName = prop.getProperty("browser");

		log.info("Browser name: " + browserName);

		optManager = new OptionsManager(prop);

		highlight = prop.getProperty("highlight");

		switch (browserName.trim().toLowerCase()) {
		case "chrome":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver("chrome");
			} else {
				tlDriver.set(new ChromeDriver(optManager.getChromeOptions()));
			}
			break;

		case "edge":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver("edge");
			} else {
				tlDriver.set(new EdgeDriver(optManager.getEdgeOptions()));
			}
			break;

		case "firefox":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver("firefox");
			} else {
				tlDriver.set(new FirefoxDriver(optManager.getFirefoxOptions()));
			}
			break;

		default:
			// System.out.println("Please pass the valid browser name....."+browserName);
			log.error("Please pass the valid browser name....." + browserName);
			throw new BrowserException("==Invalid Browser==");
		}

		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();

		return getDriver();
	}

	private void initRemoteDriver(String browserName) {
		log.info("Starting REMOTE driver for: " + browserName);
		switch (browserName) {
		case "chrome":
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optManager.getChromeOptions()));
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
			break;

		case "edge":
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optManager.getEdgeOptions()));
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
			break;

		case "firefox":
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
			break;

		default:
			log.error("Please pass the valid browser name....." + browserName);
			throw new BrowserException("==Invalid Browser==");
		}

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize the driver on the basis of browser name
	 * passed
	 * 
	 * @return
	 */

	public Properties initProperty() {
		prop = new Properties();
//		try {
//		FileInputStream fi = new FileInputStream("./src/test/resources/config/config.properties");
//		prop.load(fi);
//		}
//		catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}

		String envName = System.getProperty("env");
		FileInputStream ip = null;

		try {
			if (envName == null) {
				// System.out.println("Env is null, hence running the tests on QA env by
				// default...");
				log.warn("Env is null, hence running the tests on QA env by default...");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				// System.out.println("Running tests on env: "+envName);
				log.info("Running tests on env: " + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + envName);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static File getScreenshotFile() {
		File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return file;
	}
}
