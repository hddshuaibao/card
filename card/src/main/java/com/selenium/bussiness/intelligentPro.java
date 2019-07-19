package com.selenium.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.base.DriverBase;
import com.selenium.page.intelligentPage;

public class intelligentPro {

	public DriverBase driver;
	public intelligentPage intelpage;
	static Logger logger = Logger.getLogger(intelligentPro.class);
	
	public intelligentPro(DriverBase driver) {
		this.intelpage = new intelligentPage(driver);
		this.driver = driver;
	}
	public void postMac(String macname,String macid) {
		intelpage.clickAddMacBtn();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("---------------------点击提交");
		String alertText = intelpage.getAlertText();
		if(alertText.equals("设备新增成功")) {
			driver.alertOk();
			logger.info("---------------------确认提交"+macname+macid);
		}else if(alertText.equals("设备号已存在")) {
			driver.alertOk();
			
			logger.info("---------------------该设备号已存在"+macname+macid);
		}else {
			logger.error("---------------------alertText 不正确"+alertText);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 新增设备1 (GH32F,GH215F,CZ2015,电子校徽,GH156W,闸机在线离线，A200不拍照，鑫诺考勤机，教务大屏，监控大屏，车载轨迹，人脸识别柱)
	 * */
	public void newMac1(String macname,String macid) {
		logger.info("---------------------开始新增设备");
		intelpage.clickNewMacBtn();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("---------------------点击新增设备");
		intelpage.clickMacNameInput();
		logger.info("---------------------点击选择设备");
		intelpage.clickMacNameSelected(macname);
		intelpage.sendkeysMacId(macid);
		logger.info("---------------------输入设备id"+macid);
		this.postMac(macname, macid);

	}
	/**
	 * 添加电子班牌
	 * */
	public void newMac2(String macname,String macid,String bjname) {
		logger.info("---------------------开始新增电子班牌设备");
		intelpage.clickNewMacBtn();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("---------------------点击新增设备");
		intelpage.clickMacNameInput();
		logger.info("---------------------点击选择设备");
		intelpage.clickMacNameSelected(macname);
		intelpage.sendkeysMacId(macid);
		logger.info("---------------------输入设备id");
		intelpage.clickBjSelect();
		intelpage.clickBj(bjname);
		logger.info("---------------------点击班级"+bjname);
		this.postMac(macname, macid);
	}
	/**
	 * 添加人脸识别设备 (人脸识别考勤机，uface竖，uface横)
	 *
	 * */
	public void newMac3(String macname,String macid,String per,String nj) {
		logger.info("----------------------开始新增人脸识别设备");
		intelpage.clickNewMacBtn();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("---------------------点击新增设备");
		logger.info("---------------------点击新增设备");
		intelpage.clickMacNameInput();
		logger.info("---------------------点击选择设备");
		intelpage.clickMacNameSelected(macname);
		intelpage.sendkeysMacId(macid);
		logger.info("---------------------输入设备id");
		intelpage.clickPersonSelect();
		logger.info("---------------------点击选择的下发范围选择框");
		intelpage.clickPerson(per);
		logger.info("---------------------点击选择的下发范围");
		if(per.equals("老师")||per.equals("家长")) {
			this.postMac(macname, macid);
		}else if(per.equals("学生")&&(macname.equals("人脸识别闸机竖(UFACE)")||macname.equals("人脸识别闸机横(UFACE)"))) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			intelpage.clickNjSelect();
			logger.info("--------------------点击选择年级");
			intelpage.clickNj(nj);
			logger.info("--------------------选择年级成功"+nj);
			this.postMac(macname, macid);
		}
		
	}
	
	/**
	 * 验证设备添加成功
	 * */
	public void assertMac(String macid) {
		String actualMacId = intelpage.addedMacIdText();
		logger.info("---------------------添加的设备id为："+actualMacId);
		Assert.assertEquals(actualMacId, macid);
		logger.info("--------------------设备添加成功");
	}
	/**
	 * 删除设备数据
	 * */
	public void delMacData() {
		logger.info("-------------------------开始删除设备数据");
		 int n = 0;
		 int a = 0;
		try {
		List<WebElement> delBtns =	intelpage.macDelEle();
		 n = delBtns.size();
		 a = n/2;
		 logger.info("------------------------设备总数为"+a);
		}catch(Exception e) {
			logger.info("------------------------没有要删除的设备");
		}
		while(a !=0) {
			intelpage.clickMacDel();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String alertText = intelpage.getAlertText();
			if(alertText.equals("是否删除该设备?")) {
				driver.alertOk();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.info("---------------------确认删除第"+a+"设备");
			}else {
				logger.info("---------------------alertText 不正确"+alertText);
			}
			a--;
		}
		logger.info("---------------------完成删除设备数据");
	}
	
}
