package com.selenium.testCase;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.bussiness.CardPro;
import com.selenium.util.ProUtil;
import com.selenium.util.handleCookie;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class CardTeacherTest extends CaseBase{
	
	public DriverBase driver;
	public CardPro cardpro;
	public handleCookie cookie;
	public ProUtil pro;
	static Logger logger = Logger.getLogger(LoginTest.class);
	
	@BeforeClass
	public void BeforeClass() {
		this.driver = InitDriver(Browser.CHROME);
		this.cookie = new handleCookie(driver);
		this.cardpro = new CardPro(driver);
		this.pro = new ProUtil("/Users/space/eclipse-workspace/card/cookie.properties");
		driver.getUrl(pro.getPro("kqcardUrl_1"));
		driver.delAllCookies();
		cookie.setCookie("JSESSIONID_COOKIE");
		cookie.setCookie("JSSSID_COOKIE");
		driver.getUrl(pro.getPro("kqcardUrl_1"));
		//driver.maxWindow();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cardpro.enterTeacherTab();
	}
	
	@AfterClass
	public void AfterClass() {
		driver.stop();
	}
	
	@DataProvider(name = "cardno")
	public Object[][] getData() throws IOException{
		String filepath = "/Users/space/eclipse-workspace/card/teacherCardNo.xls";
		return DataProviderExcel.getData(filepath);
	}
	
	@Test(dataProvider = "cardno")
	public void addCard(String no,String cardno) {
		
		int i = Integer.valueOf(no);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cardpro.addTeacherCard(i,cardno);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualCardNo = cardpro.getCardNoTeacher();
		logger.info("-----------------验证卡号新增是否成功");
		Assert.assertEquals(actualCardNo, cardno);
		logger.info("-----------------新增成功"+cardno);
	}
	
	@Test(dataProvider = "cardno",dependsOnMethods = {"addCard"})
	public void delCard(String no,String cardno) {
		int i = Integer.valueOf(no);
		cardpro.delTeacherCard(i);
		logger.info("-----------------删除卡片成功："+cardno);
	}
	
	
}
