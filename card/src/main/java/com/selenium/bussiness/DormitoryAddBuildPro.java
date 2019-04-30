package com.selenium.bussiness;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.selenium.base.DriverBase;
import com.selenium.page.DormitoryPage;

public class DormitoryAddBuildPro {
	public DriverBase driver;
	public DormitoryPage dtp;
	static Logger logger = Logger.getLogger(DormitoryAddBuildPro.class);
	
	public DormitoryAddBuildPro(DriverBase driver) {
		this.driver = driver;
		this.dtp = new DormitoryPage(driver);
	}
	
	/**
	 * 判断是否有所加楼宇
	 * */
	public int ifhasBuild(String buildname) {
		int levelno = dtp.findBuild(buildname);
		return levelno;
	}
	
	/**
	 * 新增楼宇，如已有该楼宇则删除
	 * */
	public void addBuildPro(String buildname,String level) {
		int levelno = this.ifhasBuild(buildname);
		if(levelno == -1) {
			
		}else {
			try {
				dtp.clickBuildDel(buildname, levelno);
				logger.info("删除"+buildname+"成功！");
			} catch(Exception e) {
				logger.info("删除失败");
			}
		}
		logger.info("新增楼宇:"+buildname+"开始");
		dtp.clickAddBuilding();
		logger.info("点击新增楼宇按钮成功");
		if(dtp.assertAddEleIs()) {
			dtp.sendkeysBuildName(buildname);
			logger.info("输入"+buildname+"名称成功");
			dtp.sendkeysBuildLevel(level);
			logger.info("输入楼层数成功，楼层数为："+level+"层");
			dtp.clickaddConfirmBtn();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertNotEquals(this.ifhasBuild(buildname), -1);
			logger.info("新增楼宇成功：楼宇名称为  "+buildname+"楼层数为："+level);
		}else {
			logger.info("新增框未显示");
		}
		
	}
	
}
