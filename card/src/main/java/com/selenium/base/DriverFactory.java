package com.selenium.base;

import org.openqa.selenium.WebDriver;
import java.util.Arrays;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	private static WebDriver driver;
	
	public static WebDriver getDriverInstance(Browser browser) {
		if (driver == null) {
			driver = getDriver(browser);
		}
		return driver;
	}
	
	public static WebDriver getDriver(Browser browser) {
		switch (browser.ordinal()) {
		case 1:
			return getFireFoxWebDriver();
		case 2:
			return getIEDriver();
		case 3:
			return getSafariWebDriver();
		case 4:
			return getHtmlWebDriver();
		}
		return getChromeWebDriver();
	}
 
	public static WebDriver getIEDriver() {
		if (!System.getProperties().containsKey("webdriver.ie.driver")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir")
							+ "\\src\\test\\resources\\IEDriverServer.exe");
		}
		DesiredCapabilities ieCapabilities = DesiredCapabilities
				.internetExplorer();
		ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		return new InternetExplorerDriver(ieCapabilities);
	}
 
	public static WebDriver getFireFoxWebDriver() {
		System.setProperty("webdriver.firefox.bin",
				"C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		return new FirefoxDriver();
	}
 
	public static WebDriver getChromeWebDriver() {
		try {
			if (!System.getProperties().containsKey("webdriver.chrome.driver")) {
				System.setProperty("webdriver.chrome.driver",
						"F:\\hdd\\chromedriver.exe"); //"/Users/space/Downloads/chromedriver",
			}
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches",Arrays.asList("--incognito"));			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			capabilities.setCapability("chrom.binary","src/ucBrowserDrivers/chromedriver.exe");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			if (System.getProperties().containsKey("webdriver.chrome.profile")) {
				options.addArguments(new String[] { "user-data-dir="
						+ System.getProperty("webdriver.chrome.profile") });
			}
			options.addArguments(new String[] { "start-maximized" });
			return new ChromeDriver(capabilities);
		} catch (Exception e) {
			e.getStackTrace();
		}
 
		return null;
	}
 
	public static WebDriver getSafariWebDriver() {
		Platform current = Platform.getCurrent();
		if ((Platform.MAC.is(current)) || (Platform.VISTA.is(current))
				|| (Platform.WIN8.is(current))) {
			return new SafariDriver();
		}
 
		return null;
	}
 
	public static WebDriver getHtmlWebDriver() {
		DesiredCapabilities Capabilities = DesiredCapabilities.htmlUnit();
		Capabilities.setCapability("ignoreProtectedModeSettings", true);
		HtmlUnitDriver html = new HtmlUnitDriver(Capabilities);
		html.setJavascriptEnabled(true);
		return html;
	}

	
}
