package com.selenium.bussiness;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import com.selenium.base.DriverBase;
import com.selenium.page.CardPage;



public class CardPro {

	public DriverBase driver;
	public CardPage cp;
	static Logger logger = Logger.getLogger(CardPro.class);
	
	public CardPro(DriverBase driver) {
		this.driver = driver;
		this.cp = new CardPage(driver);
	}
	
	/**
	 * search class method
	 * */
	public void searchClass(String classname) {
		cp.clickselect();
		cp.clickBj(classname);
		if(cp.bjselectEle().getText().equals(classname)) {
			logger.info("点击班级成功"+classname);
		}else {
			logger.info("点击班级失败"+classname);
		}
	}
	
	/**
	 * 新增学生卡号
	 * */
	public void addCard(String cardno,String classname) {
		logger.info("开始新增卡号---------------");
		this.searchClass(classname);
		cp.clickradio();
		if(cp.radioele().isSelected()) {
			logger.info("点击学生成功");
		}else {
			logger.info("学生点击不成功");
		}
		//if(cp.addedCardNo().equals(cardno)) {
			//cp.clickdel();
			//logger.info("有相同卡号，删除卡号"+cardno);
			//try {
				//Thread.sleep(15000);
			//} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
			//driver.fresh();
		//}
		cp.sendkeysCard(cardno);
		logger.info("输入卡号："+cardno);
		cp.clickNewCard();
		logger.info("点击新增");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualno = cp.addedCardNo();
	    Assert.assertEquals(cardno, actualno);
	    logger.info("卡号"+cardno+"新增成功");
	}
	
	/**
	 * 导出卡片名单,验证按钮能否导出
	 * */
	public boolean exportCard(String filepath) {
		int beforeno;
		int afterno;
		beforeno = cp.fileNumber(filepath);
		logger.info("开始导出卡号---------------");
		logger.info("导出前的文件数是"+beforeno);
		cp.clickexport();
		logger.info("点击卡片导出按钮");
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		afterno = cp.fileNumber(filepath);
		logger.info("导出后的文件数"+afterno);
		if(beforeno+1==afterno) {
			logger.info("导出卡片成功");
			return true;
		}else {
			logger.info("导出卡片失败");
			return false;
		}
	}
	/**
	 * 删除卡号
	 * */
	public void delcard(String cardno,String classname) {
		boolean ifdelCard = false;
		logger.info("开始删除卡号---------------");
		this.searchClass(classname);
		cp.clickradio();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cp.radioele().isSelected()) {
			logger.info("点击选择学生成功");
		}else {
			logger.info("点击选择学生失败");
		}
		if(cp.assertElementIs(cp.delcardEle())) {
			
		}else { 
			logger.info("并没有要删除的卡号");
			this.addCard(cardno,classname);
		}
		cp.clickdel();
		logger.info("点击删除卡号");
		try {
			cp.addedCardNo();
		}catch(Exception e) {
			ifdelCard = true;
		}
		Assert.assertEquals(ifdelCard, true);
		logger.info("删除卡号成功");
	}
	
	/**
	 * 进入老师配卡页
	 * */
	public void enterTeacherTab() {
		logger.info("-------------------开始进入老师配卡页面");
		cp.teacherCardClick();
		logger.info("-------------------已进入老配卡页");
		
	}
	
	/**
	 * 新增老师卡号
	 * */
	public void addTeacherCard(int i,String cardno) {
		logger.info("-------------------开始选择老师");
		cp.teacherInputClick(i);
		if(cp.teacherInputList().get(i-1).isSelected()) {
			logger.info("-------------------点击选择老师成功");
		}else {
			logger.info("-------------------点击选择老师失败"+i);
		}
		logger.info("-------------------输入卡号");
		cp.sendkeysTeacherCardNO(cardno);
		logger.info("-------------------点击新增");
		cp.newCardTeacherClick();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取新增的卡号
	 * */
	public String getCardNoTeacher() {
		return cp.getElementText(cp.addedTeacherCardEle());
	}
	
	//在aftercalss 里del 老师卡片
	public void delTeacherCard(int i) {
		boolean ifdelCard = false;
		logger.info("-------------------开始删除老师卡片数据");
		cp.teacherInputClick(i);
		if(cp.teacherInputList().get(i-1).isSelected()) {
			logger.info("-------------------点击选择老师成功");
		}else {
			logger.info("-------------------点击选择老师失败"+i);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			cp.delTeacherCardEles();
			logger.info("-------------------开始删除卡片");
			cp.clickDelTeacherCard();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cp.driver.alertOk();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				cp.addedCardNo();
			}catch(Exception e) {
				ifdelCard = true;
			}
			Assert.assertEquals(ifdelCard, true);
			logger.info("删除卡号成功");
		} catch (Exception e) {
			logger.info("-------------------该老师没有卡片，不需要删除");
		}
		
		
		
	}
	
	

}
