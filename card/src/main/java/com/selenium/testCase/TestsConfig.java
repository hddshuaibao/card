package com.selenium.testCase;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.util.ProUtil;
import com.selenium.util.handleCookie;
import org.testng.annotations.*;

public class TestsConfig{

	private DriverBase driver;
	private handleCookie cookie;
	private ProUtil pro;
	
	public TestsConfig(DriverBase driver) {
		this.driver = driver;
		this.cookie = new handleCookie(driver);
		this.pro = new ProUtil("F:\\hdd\\gitdir\\card\\card\\cookie.properties");
	}
	
	public void cardBefore() {
		driver.getUrl(pro.getPro("kqcardUrl_1"));
		driver.delAllCookies();
		cookie.setCookie("JSESSIONID_COOKIE");
		cookie.setCookie("JSSSID_COOKIE");
		driver.getUrl(pro.getPro("kqcardUrl_1"));
	}



	

	
}
