package com.selenium.testCase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.bussiness.AttendanceGroupClockPro;
import com.selenium.util.ProUtil;
import com.selenium.util.handleCookie;

public class AttendanceGClockTest extends CaseBase{

	public DriverBase driver;
	public AttendanceGroupClockPro agcp;
	public handleCookie handC;
	public ProUtil pro;
	
	@BeforeClass
	public void beforeClass() {
		this.driver = InitDriver(Browser.CHROME);
		this.agcp = new AttendanceGroupClockPro(driver);
		this.handC = new handleCookie(driver);
		this.pro = new ProUtil(pro.getPro("cookielocation"));
		driver.getUrl(pro.getPro("kqgpUrl"));
		driver.delAllCookies();
		handC.setCookie("JSESSIONID_COOKIE");
		handC.setCookie("JSSSID_COOKIE");
		driver.getUrl(pro.getPro("kqgpUrl"));
		agcp.agp.buttonClick("设置扫码签到/考勤提醒");
	}
	
	@AfterClass
	public void afterClass() {
		driver.stop();
	}
	@DataProvider(name = "position")
	  public Object[][] createData() {
	   return new Object[][] {
	     { "120.201948,30.202671" },
	   };
	  }
	
	@Test
	public void testSwitchBtnD() {
		agcp.switchBtnDown();
	}
	
	@Test(dataProvider = "position")
	public void testSwitchBtnU(String position) {
		agcp.switchBtnUp(position);
	}
	
}
