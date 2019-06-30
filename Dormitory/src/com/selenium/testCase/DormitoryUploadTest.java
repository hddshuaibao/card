package com.selenium.testCase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.bussiness.UploadStuToBuildPro;
import com.selenium.util.ProUtil;
import com.selenium.util.handleCookie;

public class DormitoryUploadTest extends CaseBase{

	public DriverBase driver;
	public UploadStuToBuildPro uppro;
	public handleCookie hc;
    public ProUtil pro;
    
    
	
	@BeforeClass
	public void BeforeClass() {
		this.driver = InitDriver(Browser.CHROME);
		this.uppro = new UploadStuToBuildPro(driver);
		this.hc = new handleCookie(driver);
		this.pro = new ProUtil("F:\\hdd\\eclipseworkspace\\Dormitory\\cookie.properties");
		driver.getUrl(pro.getPro("kqgpUrl_1"));
		driver.delAllCookies();
		hc.setCookie("JSESSIONID_COOKIE");
		hc.setCookie("JSSSID_COOKIE");
		hc.setBase("BASE_DATA");
		driver.getUrl(pro.getPro("kqdomUrl_1"));
		driver.maxWindow();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@DataProvider(name = "operateData")
	public Object[][] operateData(){
		return new Object[][] {
			{"导入寝室信息","F:\\hdd\\eclipseworkspace\\Dormitory\\dormitory_uploadStuList.xls","4"}
		};
	}
	
	@Test(dataProvider = "operateData")
	public void uploadStuTest(String operateName,String uploadFilepath,String checkInNum) {
		uppro.uploadStu(operateName,uploadFilepath,checkInNum);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
