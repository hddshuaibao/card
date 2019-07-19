package com.selenium.testCase;

import java.lang.reflect.Method;

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

	private DriverBase driver;
	private CardPro cpro;
	private ProUtil pro;
	private handleCookie handleC;
	private String filepath = "/Users/space/Downloads/";
	
	@BeforeClass
	public void beforeClass() {
		this.driver = InitDriver(Browser.CHROME);
		this.cpro = new CardPro(driver);
		this.handleC = new handleCookie(driver);
		this.pro = new ProUtil(pro.getPro("cookielocation"));
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
	public Object[][] createData(Method method) {
		Object[][] result = null;
		if(method.getName().equals("testDelCard")) {
			result = new Object[][] {
				{"1234877441","草莓三班"  },
			};
		}else if(method.getName().equals("batchCardTest")) {
			result = new Object[][] {
				{"批量配卡","/Users/space/gitdir/card/card/cardTemplateNew.xls","学生配卡数据导入:成功:6张；失败：0张。\n老师配卡数据导入:成功:2张；失败：0张。"},
			};
		}else if(method.getName().equals("delBatchCardTest")) {
			result = new Object[][] {
				{"学生配卡","自动化批量配卡1"},
				{"学生配卡","自动化批量配卡2"},
				{"学生配卡","自动化批量配卡3"},
				{"老师配卡","自动化配卡老师1"},
				{"老师配卡","自动化配卡老师2"},
			};
		}
		
		
		return result;
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
	//添加卡号
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
	
	@Test(dataProvider = "data1")
	public void batchCardTest(String tabname,String filepath,String expectalerttext) throws InterruptedException {
		cpro.batchUploadCard(tabname, filepath, expectalerttext);
	}
	
	@Test(dependsOnMethods = {"batchCardTest"},dataProvider = "data1")
	public void delBatchCardTest(String tabname,String name) throws InterruptedException {
		cpro.delBatchCard(tabname, name);
	}
	
	
	
	
	
}
