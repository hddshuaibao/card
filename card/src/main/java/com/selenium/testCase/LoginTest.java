package com.selenium.testCase;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.bussiness.LoginPro;
import com.selenium.util.handleCookie;

public class LoginTest extends CaseBase{

	public DriverBase driver;
	public LoginPro logpro;
	public handleCookie handcookie;
	//public ProUtil pro;
	String script = "localStorage.BASE_DATA;";
	static Logger logger = Logger.getLogger(LoginTest.class);
	
	@BeforeClass
	public void BeforeClass() {
		this.driver = InitDriver(Browser.CHROME);
		this.logpro = new LoginPro(driver);
		this.handcookie = new handleCookie(driver);
		//this.pro = new ProUtil("/Users/space/eclipse-workspace/card/cookie.properties");
		
	}
	@DataProvider(name = "logindata")
	public Object[][] getData() throws IOException{
		String filepath="logdata.xls";
		return DataProviderExcel.getData(filepath);
	}
	
	@Test(dataProvider = "logindata")
	public void login(String username,String password,String expectlogname) {
		logger.debug("用log4j打印日志 ~~~~~进行登录");
		driver.getUrl("https://www.weixiaotong.com.cn/weixt/login");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.getUrl("http://10.10.10.190:8090/weixt/login");
		logpro.login(username, password);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logpro.assertEquals(expectlogname);
		logger.info("登录成功");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//存储storage 信息，存入cookie2 中
		String basedata =  (String) driver.JavascriptExecutor().executeScript(String.format("return localStorage.getItem('%s');", "BASE_DATA"));
		handcookie.writeBase(basedata);
		System.out.println(basedata);
		handcookie.writeCookie();
		
	}
	
	@AfterClass
	public void AfterTest() {
		driver.stop();
	}
}
