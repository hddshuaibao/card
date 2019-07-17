package com.selenium.testCase;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.bussiness.AttendanceDownloadUpPro;
import com.selenium.page.AttendanceGroupPage;
import com.selenium.util.ProUtil;
import com.selenium.util.handleCookie;

public class AttendanceDownloadUpTest extends CaseBase{

	public DriverBase driver;
	static Logger logger = Logger.getLogger(AttendanceDownloadUpTest.class);
	public AttendanceGroupPage agpage;
	public AttendanceDownloadUpPro adup;
	public ProUtil pro;
	public handleCookie handC;
	
	@BeforeClass
	public void BeforeClass() {
		this.driver = InitDriver(Browser.CHROME);
		this.agpage = new AttendanceGroupPage(driver);
		this.adup = new AttendanceDownloadUpPro(driver);
		this.handC = new handleCookie(driver);
		this.pro = new ProUtil("cookie.properties");
		driver.getUrl(pro.getPro("kqgpUrl_190"));
		driver.delAllCookies();
		handC.setCookie("JSESSIONID_COOKIE");
		handC.setCookie("JSSSID_COOKIE");
		driver.getUrl(pro.getPro("kqgpUrl_190"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//401426784  --高三（1）班  
	@DataProvider(name = "grouptabdata")
	public Object[][] grouptabdata() {
		return new Object[][] {
		{1,""},
		{2,""},
		{2,"49271"},
		};
	}
	
	@DataProvider(name = "uploaddata")
	public Object[][] uploaddata() {
		return new Object[][] {
		{1,"/Users/space/gitdir/card/card/老师考勤组信息.xls","导入成功"},
		{1,"/Users/space/gitdir/card/card/老师考勤组信息2.xls","老师配对考勤组数据导入 ：成功 :0条;失败:2条"},
		{1,"/Users/space/gitdir/card/card/老师考勤组信息3.xls","导入成功"},
		{2,"/Users/space/gitdir/card/card/学生考勤组信息.xls","导入成功"},
		{2,"/Users/space/gitdir/card/card/学生考勤组信息2.xls","学生配对考勤组数据导入 ：成功 :0条;失败:2条"},
		{2,"/Users/space/gitdir/card/card/学生考勤组信息3.xls","导入成功"},
		};
	}
	
	@Test(dataProvider = "grouptabdata")
	public void downloadGpTest(int index,String claname) {
		adup.entryGroupTab(index);
		if(index==1) {
			adup.downloadAsGroup();
		}else if(index==2) {
			adup.downloadStuAsGroup(claname);
		}
		
	}
	
	@Test(dataProvider = "uploaddata")
	public void uploadGpTest(int index,String filepath,String alertinfo) {
		adup.entryGroupTab(index);
		adup.uploadAttGp(index, filepath, alertinfo);
	}
	
	
	
	
	
	
	
	
}
