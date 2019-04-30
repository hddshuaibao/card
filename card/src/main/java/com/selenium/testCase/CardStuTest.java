package com.selenium.testCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.bussiness.CardPro;
import com.selenium.util.ProUtil;
import com.selenium.util.handleCookie;


public class CardStuTest extends CaseBase{

	public DriverBase driver;
	public CardPro cpro;
	public ProUtil pro;
	public handleCookie handleC;
	String filepath = "/Users/space/Downloads/";
	
	@BeforeClass
	public void beforeClass() {
		this.driver = InitDriver(Browser.CHROME);
		this.cpro = new CardPro(driver);
		this.handleC = new handleCookie(driver);
		this.pro = new ProUtil("/Users/space/eclipse-workspace/card/cookie.properties");
		driver.getUrl(pro.getPro("kqcardUrl_1"));
		driver.delAllCookies();
		handleC.setCookie("JSESSIONID_COOKIE");
		handleC.setCookie("JSSSID_COOKIE");
		driver.getUrl(pro.getPro("kqcardUrl_1"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.stop();
	}
	
	@DataProvider(name = "data1")
	public Object[][] createData() {
		return new Object[][] {
		{"1234877441","草莓三班"  },
		};
	}
	
	/**
	 * 导出测试用例
	 * */
	@Test
	public void testExport() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean export = cpro.exportCard(filepath);
		Assert.assertEquals(true, export);
	}
	
	@Test(dataProvider = "data1")
	public void testAddCard(String cardno,String classname) {
		cpro.addCard(cardno,classname);
	}
	
	
	/**
	 * 删除卡号测试用例
	 * */
	@Test(dataProvider = "data1")
	public void testDelCard(String cardno,String classname) {
		cpro.delcard(cardno,classname);
	}
	
}
