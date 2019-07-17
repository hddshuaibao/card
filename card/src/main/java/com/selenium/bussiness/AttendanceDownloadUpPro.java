package com.selenium.bussiness;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.selenium.base.DriverBase;
import com.selenium.page.AttendanceGroupPage;


public class AttendanceDownloadUpPro {

	public DriverBase driver;
	public AttendanceGroupPage agp;
	static Logger logger = Logger.getLogger(AttendanceDownloadUpPro.class);
	public AttendanceDownloadUpPro(DriverBase driver) {
		this.driver = driver;
		this.agp = new AttendanceGroupPage(driver);
	}
	
	/**
	 * 进入tab页
	 * */
	public void entryGroupTab(int index) {
		agp.clickTab(index);
		logger.info("-----------------点击进入"+agp.tabsElement().get(index).getText()+"页");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 点击上传 按钮上传，index=1：老师，index=2：学生          老师配对考勤组数据导入 ：成功 :0条;失败:1条  
	 * 
	 *导入成功
	 * */
	public void uploadAttGp(int index,String filepath,String alertinfo) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("----------------开始上传考勤组信息");
		agp.sendkeysAttGp(index-1,filepath);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		agp.waitAlert();
		String actualalertinfo = agp.getAlert().getText();
		System.out.println(actualalertinfo);
		if(!actualalertinfo.contains(alertinfo)) {
			Assert.fail("actual:"+actualalertinfo+"expect:"+alertinfo);
			
		}
		//Assert.assertSame(agp.getAlert().getText(), alertinfo);
		//Assert.assertEquals(agp.getAlert().getText(), alertinfo);
		logger.info("-----------------导入起作用，提示信息"+actualalertinfo);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 老师页面循环选择考勤组，进行导出
	 * */
	public void downloadAsGroup() {
		for(int i = 0;i<agp.AttendanceGroupElements().size();i++) {
			agp.clickAttendanceGroup(i);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int beforefile = agp.fileNumber();
			agp.clickDownload();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int afterfile = agp.fileNumber();
			Assert.assertEquals(afterfile, beforefile+1);
			logger.info("-----------------------"+agp.AttendanceGroupElements().get(i).getText()+"下载成功");
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 学生页面循环选择考勤组，导出
	 * */
	public void downloadStuAsGroup(String claname) {
		
		if(claname !="") {
			logger.info("----------------选择班级");
			Select cla = agp.select(agp.getSelectClass());
			cla.selectByValue(claname);;
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			for(int i = 0;i<agp.StuAttendanceGroupElements().size();i++) {
				agp.clickStuAttendanceGroup(i);;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int beforefile = agp.fileNumber();
				agp.clickStuDownload();;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int afterfile = agp.fileNumber();
				Assert.assertEquals(afterfile, beforefile+1);
				logger.info("-----------------------"+agp.StuAttendanceGroupElements().get(i).getText()+"下载成功");
			}
		
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
