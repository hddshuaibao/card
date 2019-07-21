package com.selenium.testCase;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.bussiness.PaxyPro;
import com.selenium.util.ProUtil;
import com.selenium.util.handleCookie;


public class PaxyTest extends CaseBase{

	
	private DriverBase driver;
	private ProUtil pro;
	private handleCookie cookie;
	final String cookiefilepath = "F:\\hdd\\gitdir\\card\\card\\cookie.properties";
	private PaxyPro paxypro;
	private TestsConfig config;
	final String tabname = "智能硬件";
	final String macname = "闸机";
	final String macid = "posttest_zaji";

	
	@BeforeGroups("paxy")
	public void beforeGroups() throws InterruptedException {
		this.driver = InitDriver(Browser.CHROME);
		this.cookie = new handleCookie(driver);
		this.pro = new ProUtil(cookiefilepath);
		this.paxypro = new PaxyPro(driver);
		this.config = new TestsConfig(driver);
		config.cardBefore();
		paxypro.searchMac(tabname,macname, macid);
	}
	
	@DataProvider(name = "postcard")
	public Object[][] getData(Method method){
		Object[][] results = null;
		results = new Object[][] {
			{"0004589001",0},
			{"0004589002",0}
		};
		
		return results;
	}
	
	
	@Test(groups="paxy",dataProvider = "postcard")
	public void postCardTest(String cardno,int reader) throws InterruptedException {
		paxypro.postCard(cardno, reader);
		
	}

}
