package com.selenium.testCase;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.util.ProUtil;
import com.selenium.util.handleCookie;
import org.testng.annotations.*;

public class TestsConfig extends CaseBase{

	private DriverBase driver;
	private ProUtil pro;
	private handleCookie cookie;
	
	
	@BeforeGroups
	public void beforeGroup() {
		this.driver = InitDriver(Browser.CHROME);
		this.cookie = new handleCookie(driver);
		this.pro = new ProUtil(pro.getPro("cookielocation"));
		driver.getUrl(pro.getPro("kqcardUrl_1"));
		driver.delAllCookies();
		cookie.setCookie("JSESSIONID_COOKIE");
		cookie.setCookie("JSSSID_COOKIE");
		driver.getUrl(pro.getPro("kqcardUrl_1"));
	}
	
	
}
