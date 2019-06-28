package com.selenium.testCase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.bussiness.DormitoryAddBuildPro;
import com.selenium.bussiness.DormitoryDelBuildPro;
import com.selenium.page.DormitoryPage;
import com.selenium.util.ProUtil;
import com.selenium.util.handleCookie;

public class DormitoryDelBuildTest extends CaseBase{

	public DormitoryDelBuildPro delpro;
	public DriverBase driver;
	public handleCookie handleC;
	public DormitoryPage dtp;
	public ProUtil pro;
	public DormitoryAddBuildPro dabp;
	

	
	@BeforeClass
	public void BeforeClass() {
		//this.driver = InitDriver(Browser.CHROME);
		this.driver = InitDriver(Browser.CHROME);
		this.delpro = new DormitoryDelBuildPro(driver);
		this.handleC = new handleCookie(driver);
		this.dtp = new DormitoryPage(driver);
		this.dabp = new DormitoryAddBuildPro(driver);
		this.pro = new ProUtil("/Users/space/eclipse-workspace/Dormitory/cookie.properties");
		driver.getUrl(pro.getPro("kqgpUrl_1"));
		driver.delAllCookies();
		handleC.setCookie("JSESSIONID_COOKIE");
		handleC.setCookie("JSSSID_COOKIE");
		handleC.setBase("BASE_DATA");
		driver.getUrl(pro.getPro("kqdomUrl_1"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//dtp.clickDormitoryPage();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	
	@AfterClass
	public void afterClass() {
		driver.stop();
	}
	
	@DataProvider(name = "delbuildinfo")
	public Object[][] addbuild(){
		return new Object[][] {
			{"自动化测试楼宇","该楼宇下有楼层信息"},
		};
	}
	
	@Test(dataProvider = "delbuildinfo")
	public void delBuild(String buildname,String delText) {
		delpro.delBuild(buildname,delText);
	}
	
	@Test
	public void delRoom() {
		delpro.driver.fresh();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		delpro.delRoom();
	}
	
	@Test
	public void entryRoom() {
		dabp.entryRoom(1);
	}
	@Test(dependsOnMethods = "entryRoom")
	public void delStuTest() {
		delpro.delStu();
		delpro.assertDelStu();
	}
	
	
	
}
