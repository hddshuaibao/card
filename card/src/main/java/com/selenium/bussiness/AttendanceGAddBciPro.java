package com.selenium.bussiness;

import org.apache.log4j.Logger;

import com.selenium.base.DriverBase;
import com.selenium.page.AttendanceGroupPage;
import com.selenium.testCase.LoginTest;

public class AttendanceGAddBciPro {

	public DriverBase driver;
	public AttendanceGroupPage agp;
	static Logger logger = Logger.getLogger(LoginTest.class);
	public AttendanceGAddBciPro(DriverBase driver) {
		this.driver = driver;
		this.agp = new AttendanceGroupPage(driver);
	}
	
	/*
	 * 判断是否已有该班次
	 * **/
	public boolean ifHadBanci(String banciName) {
		if(agp.getBanciTrNo(banciName)==0) {
			return false;
		}else {
			return true;
		}
	}
	/*
	 * 判断班次名称是否正确
	 * **/
	public boolean checkBanciTime(String banciName,String banciTime) {
		return agp.checkBanciTime(banciName, banciTime);
	}

	/*
	 * 新建班次
	 * **/
	public void addBanci(String banciName,int[] startTimeNo,int[] endTimeNo) {
		
		agp.buttonClick("班次管理");
		System.out.println("点击进入班次管理");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean ifhadbanci = this.ifHadBanci(banciName);
		if(ifhadbanci == false) {
			agp.buttonClick("新建班次");
		}else {
			agp.deletBance(banciName);
			agp.buttonClick2("确认");
			logger.info("删除已存在班次成功，班次名为"+banciName);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			agp.buttonClick("新建班次");
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		agp.switchToModal();
		logger.info("切换到模态框");
		try {
			agp.assertElementIs(agp.getBanciNameEle()); 
			logger.info("切换到模态框成功");
		}catch(Exception e){
			logger.info("班次模态框没显示");
		}
		agp.sendKeysBanciName(banciName);
		logger.info(banciName+"输入成功");
		agp.clickAddTime();
		logger.info("点击添加时间段按钮");
		try {
			agp.assertElementIs(agp.getTimeEle().get(0)); 
		}catch(Exception e){
			System.out.println("时间框没有显示");
		}
		
			agp.clickTimeEle(0);
			agp.setTime(0,startTimeNo);
			logger.info("开始时间输入完成");
			agp.clickTimeEle(1);
			agp.setTime(1,endTimeNo);
			logger.info("结束时间输入完成");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		agp.buttonClick2("保存");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
