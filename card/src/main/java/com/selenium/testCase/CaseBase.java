package com.selenium.testCase;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;

public class CaseBase {

	public DriverBase InitDriver(Browser browser) {
		return new DriverBase(browser);
	}
	
	
}
