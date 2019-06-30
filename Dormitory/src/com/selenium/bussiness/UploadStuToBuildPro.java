package com.selenium.bussiness;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.selenium.base.DriverBase;
import com.selenium.page.DormitoryPage;

public class UploadStuToBuildPro {

	public DriverBase driver;
	public DormitoryPage dp;
	static Logger logger = Logger.getLogger(UploadStuToBuildPro.class);
	
	public UploadStuToBuildPro(DriverBase driver) {
		this.driver = driver;
		this.dp = new DormitoryPage(driver);
	}
	
	/**
	 * 进入 导入框
	 * */
	public void operateFrame(String operateName) {
		dp.mouseoverOperateBtn();
		logger.info("-----------------------鼠标移至管理寝室信息按钮");
		dp.getOperatesElements().get(0);
		logger.info("----------------------导入导出按钮显示");
		driver.waitElementClickable(dp.getOperatesElements().get(0));
		dp.clickOperate(operateName);
		logger.info("------------------------点击 "+operateName+" 按钮");
		dp.getUploadTitleElement();
		logger.info("----------------------导入框已显示");
	}
	
	/**
	 * 导入框 下载未入住学生模板
	 * */
	public void downloadModel(String operateName) {
		this.operateFrame(operateName);
		int beforeDownload = dp.fileNumber();
		dp.clickDownloadModel();
		logger.info("----------------------点击下载未入住学生模板");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int afterDownload = dp.fileNumber();
		Assert.assertEquals(afterDownload, beforeDownload+1);
		logger.info("--------------------下载未入住学生模板成功");
	}
	
	
	
	
	/**
	 * 导入未入住学生
	 * */
	public void uploadStu(String operateName,String uploadFilepath,String expectCheckInNum) {
		boolean errorTable = true;
		this.operateFrame(operateName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dp.sendkeysUploadDormitoryRecord(uploadFilepath);
		logger.info("---------------------上传成功");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.waitElementClickable(dp.getUploadCheckElement());
		dp.clickUploadCheck();
		logger.info("----------------------点击确定成功");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dp.errorTableElement();
			
		}catch(Exception e) {
			errorTable = false;
		}
		Assert.assertEquals(errorTable,false, "测试数据重复，请先删除测试数据");
		logger.info("----------------------开始验证是否导入成功");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.waitElementClickable(dp.arrowEles().get(4));
		dp.clickUploadArrow();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dp.clickUploadFloor();
		logger.info("--------------------进入1楼层");
		dp.waitUntilCheckInNum();
		String actualCheckInNum = dp.getCheckInNum();
		Assert.assertEquals(actualCheckInNum, expectCheckInNum);
		logger.info("----------------------导入成功");
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
