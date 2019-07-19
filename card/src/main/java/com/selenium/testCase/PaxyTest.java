package com.selenium.testCase;

import org.testng.annotations.Test;

import com.selenium.base.DriverBase;
import com.selenium.bussiness.PaxyPro;

public class PaxyTest extends CaseBase{
	
	private PaxyPro paxyPro;
	private DriverBase driver;
	
	public PaxyTest(DriverBase driver) {
		this.driver = driver;
		this.paxyPro = new PaxyPro(driver);
		
	}
	
	@Test(groups = "paxy")
	public void getRequest() {
		
		
		
	}

}
