package com.selenium.testCase;

import org.testng.annotations.*;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.bussiness.DormitoryAddBuildPro;
import com.selenium.page.DormitoryPage;
import com.selenium.util.ProUtil;
import com.selenium.util.handleCookie;

public class DormitoryAddBuildTest extends CaseBase{

	public DormitoryAddBuildPro dabp;
	public DriverBase driver;
	public handleCookie handleC;
	public DormitoryPage dtp;
	public ProUtil pro;
	
	@BeforeClass
	public void BeforeClass() {
		this.driver = InitDriver(Browser.CHROME);
		this.dabp = new DormitoryAddBuildPro(driver);
		this.handleC = new handleCookie(driver);
		this.dtp = new DormitoryPage(driver);
		this.pro = new ProUtil(pro.getPro("cookielocation"));
		driver.getUrl(pro.getPro("kqgpUrl_1"));
		driver.delAllCookies();
		handleC.setCookie("JSESSIONID_COOKIE");
		handleC.setCookie("JSSSID_COOKIE");
		handleC.setBase("BASE_DATA");
		driver.getUrl(pro.getPro("kqgpUrl_1"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtp.clickDormitoryPage();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	
	@AfterClass
	public void afterClass() {
		//driver.stop();
	}
	
	@DataProvider(name = "addbuildinfo")
	public Object[][] addbuild(){
		return new Object[][] {
			{"自动化测试楼宇","10"},
		};
	}
	
	@Test(dataProvider = "addbuildinfo")
	public void testAddBuild(String buildname,String level) {
		dabp.addBuildPro(buildname, level);
	}
}
