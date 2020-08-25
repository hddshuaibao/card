package com.selenium.testCase;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.bussiness.LoginPro;

@Listeners({ScreenFailtureListener.class})
public class LoginTestNewVersion extends CaseBase{

	public DriverBase driver;
	public LoginPro logpro;
	static Logger logger = Logger.getLogger(LoginTest.class);
	
	@BeforeClass
	public void beforeClass() {
		this.driver = InitDriver(Browser.CHROME);
		this.logpro = new LoginPro(driver);
	}
	
	@DataProvider(name = "logindata")
	public Object[][] getData() throws IOException{
		String filepath="F:\\hdd\\gitdir\\card\\card\\testData\\logdata.xls";
		return DataProviderExcel.getData(filepath,"登录失败");
	}
	
	//登录失败校验验证
	@Test(dataProvider = "logindata")
	public void loginFailTest(String username,String password,String expected) {
		driver.driver.get("http://test.spacetech.com.cn/weixt/login/");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logpro.loginNewVersion(username, password, expected);

	}

	
	@AfterClass
	public void AfterTest() {
		//driver.stop();
	}
	
}
