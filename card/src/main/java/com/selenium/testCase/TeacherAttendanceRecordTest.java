package com.selenium.testCase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.base.Browser;
import com.selenium.base.DriverBase;
import com.selenium.page.TeacherAttendancePage;
import com.selenium.util.ProUtil;
import com.selenium.util.handleCookie;



//@Listeners({ScreenFailtureListener.class})
public class TeacherAttendanceRecordTest extends CaseBase{

	public DriverBase driver;
	public handleCookie handC;
	public ProUtil pro;
	public TeacherAttendancePage tap;
	
	@BeforeClass
	public void BeforeClass() {
		this.driver = InitDriver(Browser.CHROME);
		this.handC = new handleCookie(driver);
		this.tap = new TeacherAttendancePage(driver);
		this.pro = new ProUtil(pro.getPro("cookielocation"));
		driver.getUrl(pro.getPro("kqgpUrl_1"));
		driver.delAllCookies();
		handC.setCookie("JSESSIONID_COOKIE");
		handC.setCookie("JSSSID_COOKIE");
		driver.getUrl(pro.getPro("kqgpUrl_1"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*测试功能点：
	 * 
	 * 1：教师考勤统计页->日期统计页->考勤明细页-页面正常没有错乱
	 * 2、补录-兰芳补录时间点8：05分-页面考勤状态显示迟到早退，显示打卡时间8：05：00
	 * 3、补录-兰芳补录时间点8：00分-页面考勤状态显示正常打卡，显示打卡时间8：00：00
	 * 4、补录-兰芳补录时间点14：00-页面考勤状态显示早退，显示打卡时间14：00：00
	 * 5、补录-倩丽补录时间点14：00-页面考勤状态显示漏打卡
	 * 6、补录-兰芳-点击时间弹出框显示姓名点击删除
	 * 
	 * 
	 * */
//	@Test
//	public void gotoTeacherAttendanceRecordPage() throws Exception {
//		
//		Thread.sleep(1000);
//		tap.teacherAttendBar().click();
//		System.out.println("进入教师考勤统计页");
//		tap.searchButton(driver.driver).click();
//		tap.tabLeaf(driver.driver).get(2).click();//来回切换页面
//		Thread.sleep(1000);
//		tap.tabLeaf(driver.driver).get(0).click();
//		Thread.sleep(1000);
//		Assert.assertTrue(tap.headerMatch(driver.driver,1));//匹配日期统计页标题
//		System.out.println("日期统计页没有错乱");
//		Reporter.log("日期统计页没有错乱");
//		tap.tabLeaf(driver.driver).get(2).click();
//		Thread.sleep(1000);
//		tap.AttendanceDetailSearchButton(driver.driver).click();
//		Thread.sleep(1000);
//		Assert.assertTrue(tap.headerMatch(driver.driver,2));//匹配考勤明细页标题
//		System.out.println("考勤明细页没有错乱");
//		Reporter.log("考勤明细页没有错乱");
//		
//	}
	
	/*
	 * 补录功能测试用例
	 * 测试用例1-5
	 * */
	
	@DataProvider(name = "BackTrackingData")
	  public Object[][] createData1() {
	   return new Object[][] {
	     { "兰芳","08","05","lf1","[08:00到校]迟到"},
	     { "兰芳","08","00","lf1_2","[08:00到校]正常打卡"},
	     { "兰芳","14","00","lf2","[18:00离校]早退"},
	     { "倩丽","14","00","ql1","[08:00到校]漏打卡"},
	   };
	  }
	
	@Test
	public void gotoAttendanceDetailPage() throws Exception{
		Thread.sleep(1000);
		tap.teacherAttendBar().click();
		System.out.println("进入教师考勤统计页");
		//System.out.println("进入教师考勤统计页");
		tap.tabLeaf(driver.driver).get(2).click();//进入考勤明细页
		System.out.println("进入考勤明细页");
		Thread.sleep(1000);
		tap.selectToday(driver.driver).click();
		tap.AttendanceDetailSearchButton(driver.driver).click();
		System.out.println("搜索当天完成");
		Reporter.log("进入考勤明细");
		Thread.sleep(2000);
		
	}
	
	@Test(dataProvider =("BackTrackingData"),dependsOnMethods = "gotoAttendanceDetailPage")
	public void AttendanceDetailBackTracking(String name,String hour,String min,String stateNo,String state)throws Exception{
		
		//补卡
		tap.backTrackingButton(driver.driver).click();
		tap.backTracking(driver.driver,name,hour,min,state);
		Thread.sleep(1000);
		System.out.println(name+"打卡，"+"状态"+state);
		tap.AttendanceDetailSearchButton(driver.driver).click();
		Thread.sleep(1000);
		Reporter.log("补卡："+name+"打卡，"+"状态"+state);
		
		//验证页面补卡状态正确
		if(stateNo=="lf1") {
			String actualState=tap.backTrackingStateOnPage_lf1(driver.driver).getText();
			Assert.assertEquals(actualState, state);
			System.out.println("补卡："+name+"打卡，"+"页面状态验证成功"+state);
			Reporter.log("补卡："+name+"打卡，"+"页面状态验证成功"+state);
		}else if(stateNo=="lf1_2") {
			String actualState=tap.backTrackingStateOnPage_lf1(driver.driver).getText();
			Assert.assertEquals(actualState, state);
			System.out.println("补卡："+name+"打卡，"+"页面状态验证成功"+state);
			Reporter.log("补卡："+name+"打卡，"+"页面状态验证成功"+state);
		}else if(stateNo=="lf2") {
			String actualState=tap.backTrackingStateOnPage_lf2(driver.driver).getText();
			Assert.assertEquals(actualState, state);
			System.out.println("补卡："+name+"打卡，"+"页面状态验证成功"+state);
			Reporter.log("补卡："+name+"打卡，"+"页面状态验证成功"+state);
		}else if(stateNo=="ql1") {
			String actualState=tap.backTrackingStateOnPage_ql1(driver.driver).getText();
			Assert.assertEquals(actualState, state);
			System.out.println("补卡："+name+"打卡，"+"页面状态验证成功"+state);
			Reporter.log("补卡："+name+"打卡，"+"页面状态验证成功"+state);
		}else {
			
			System.out.println("补卡："+name+"打卡，"+"页面状态验证失败"+state);
			Reporter.log("补卡："+name+"打卡，"+"页面状态验证失败"+state);
		}
	
	}
	
	
	
//	@Test(dependsOnMethods={"AttendanceDetailBackTracking"})
//	public void VerifyBackTrackingBySql() throws Exception {
//		
//		String date = tap.datePicer();//取得今天的时间
//		//进行数据库验证
//		java.sql.Connection con = tap.sqlCon("weixin", "yunying", "Space78901234");
//		PreparedStatement pstm = null;
//		ResultSet rs = null;
//		String[] xm = new String[5];
//		String[] expectedxm = {"兰芳","兰芳","嘉怡","嘉怡","倩丽"};
//		String[] expecteddqrq = {date+" 12:00:00.0",date+" 15:00:00.0",date+" 19:00:00.0",date+" 08:00:00.0",date+" 15:00:00.0"};
//		String[] dqrq = new String[5];
//		int i = 0;
//		pstm = con.prepareStatement("SELECT * from wx_xx_paxy WHERE campusid = '33921' and remark LIKE '%补打卡人%' "
//				+ "ORDER BY id DESC Limit 5");
//		rs = pstm.executeQuery();
//		while(rs.next()){
//			xm[i] = rs.getString("xm");
//			dqrq[i] = rs.getString("dqrq");
//			System.out.println("数据库xm和dqrq字段：" + xm[i]+" "+dqrq[i]);
//			i++;
//			}
//		if(Arrays.equals(xm,expectedxm)&& Arrays.equals(dqrq,expecteddqrq) ) {
//			
//			System.out.println("补录成功");
//		}
//		Reporter.log("补录成功SQL验证成功");
//		
//	}
	
	
	
	
	@AfterTest
	  public void shutDown() throws Exception
		{
			//driver.driver.quit();
		}
	
	
}
