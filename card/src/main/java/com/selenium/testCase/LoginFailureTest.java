package com.selenium.testCase;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.bussiness.LoginPro;

public class LoginFailureTest extends CaseBase{

	public DriverBase driver;
	public LoginPro lp;
	public LoginFailureTest() {
		this.driver = InitDriver(Browser.CHROME);
	}
	
}
