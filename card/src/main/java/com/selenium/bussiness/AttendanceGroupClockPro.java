package com.selenium.bussiness;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.selenium.base.DriverBase;
import com.selenium.page.AttendanceGroupPage;
import com.selenium.util.ProUtil;


public class AttendanceGroupClockPro {

	public DriverBase driver;
	public AttendanceGroupPage agp;
	static Logger logger = Logger.getLogger(AttendanceGroupClockPro.class);
	public ProUtil pro;
	public AttendanceGroupClockPro(DriverBase driver) {
		this.driver = driver;
		this.agp = new AttendanceGroupPage(driver);
		this.pro = new ProUtil("E:\\eclipseJEE\\workplace\\weixt_02\\element.properties");
	}
	
	/**
	 * 点击地图链接pro
	 * */
	public void clickAddressOK() {
		agp.clickAddress();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Assert.assertEquals(agp.ifToAmap(), 2);
			logger.info("开关开启下，点击地图链接成功");
	}
	public void clickAddressfail() {
		agp.clickAddress();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Assert.assertNotEquals(agp.ifToAmap(), 2);
			logger.info("开关关闭下，不可点击地图链接成功");
	}
	
	
	/**
	 * switch 按钮关闭下的pro
	 * */
	public void switchBtnDown() {
		String style = agp.getSwitchStyle();
		if(style.equals(pro.getPro("switchShutDStyle"))) {
			Assert.assertEquals(agp.ifDisabled(),true);
			logger.info("开关置灰下，打卡范围不可输入");
			this.clickAddressfail();
		}else {
			agp.clickSwitch();
			logger.info("关闭开关");
			Assert.assertEquals(agp.ifDisabled(),true);
			logger.info("按钮置灰下，打卡范围不可输入");
			this.clickAddressfail();
		}
		
	}
	
	/**
	 * switch按钮开启下的pro
	 * */
	public void switchBtnUp(String position) {
		String style = agp.getSwitchStyle();
		if(style.equals(pro.getPro("switchTurnUStyle"))) {
			logger.info("进行正常操作，输入地址和打卡范围");
		}else {
			agp.clickSwitch();
			logger.info("打卡开关");
		}
		String range = agp.sendkeysClockInfo(position);
		logger.info("输入成功，地址为"+position+"范围为"+range);
		agp.buttonClick2("保存");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		agp.buttonClick2("设置扫码签到/考勤提醒");
		Assert.assertEquals(agp.clockInfoEle().get(1).getAttribute("value"), range);
		logger.info("保存成功"+"位置为"+position+"范围为"+range);
		this.clickAddressOK();
	}
	
	
	
	
}
