package com.selenium.page;

import com.selenium.base.DriverBase;
import com.selenium.testCase.LoginTest;
import com.selenium.util.getByLocator;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;


public class CardPage extends BasePage {
	
	static Logger logger = Logger.getLogger(LoginTest.class); 
	public CardPage(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取班级ele
	 * */
	public List<WebElement> bjEle(){
		return this.elementList(getByLocator.getLocator("bjlist"));
	}
	/**
	 * 获取班级选择框ele
	 * */
	public WebElement bjselectEle(){
		return this.element(getByLocator.getLocator("bjselect"));
	}
	
	//获取老师配卡tab链接ele
	public WebElement teacherCardEle() {
		return this.element(getByLocator.getLocator("teachertab"));
	}
	public WebElement batchCardEle() {
		return this.element(getByLocator.getLocator("batchcard"));
	}
	public WebElement intelligentEle() {
		return this.element(getByLocator.getLocator("intelligent"));
	}
	public WebElement cameraEle() {
		return this.element(getByLocator.getLocator("camera"));
	}
	public WebElement stuCardEle() {
		return this.element(getByLocator.getLocator("stutab"));
	}
	
	//点击老师配卡tab链接ele
	public void teacherCardClick() {
		this.teacherCardEle().click();
	}
	//获取老师选择框elelist
	public List<WebElement> teacherInputList(){
		List<WebElement> teacherinput = this.elementList(getByLocator.getLocator("teacherinputname"));
		return teacherinput;
	}
	//对老师的选择框做一个点击的操作
	public void teacherInputClick(int i) {
		if(i>0) {
			List<WebElement> teacherInputList = this.teacherInputList();
			this.click(teacherInputList.get(i-1));
		}else {
			logger.info("输入的数字不符合要求为"+i);
		}
	}
	//获取卡号inputele
	public WebElement teacherCardInputEle() {
		return this.element(getByLocator.getLocator("teacherCardInput"));
	}
	//输入卡号
	public void sendkeysTeacherCardNO(String cardno) {
		this.sendKeys(this.teacherCardInputEle(), cardno);
	}
	
	//获取新增老师卡片按钮ele
	public WebElement newCardTeacherBtnEle() {
		return this.element(getByLocator.getLocator("addButton_Teacher"));
	}
	//点击新增老师卡片
	public void newCardTeacherClick() {
		this.click(this.newCardTeacherBtnEle());
	}
	//获取新增的卡号 ele
	public WebElement addedTeacherCardEle() {
		WebElement table = this.element(getByLocator.getLocator("cardTable"));
		List<WebElement> addedCard = this.elementList(table, getByLocator.getLocator("tableText"));
		return addedCard.get(4);
	}
	//获取老师已有卡号的删除eles
	public List<WebElement> delTeacherCardEles(){
		List<WebElement> del = this.elementList(getByLocator.getLocator("delTeacherCard"));
		return del;
	}
	//循环点击删除老师已有卡号
	public void clickDelTeacherCard() {
		List<WebElement> dellist = this.delTeacherCardEles();
		for(WebElement del:dellist) {
			this.click(del);
		}
	}
	
	/**
	 * 点击选择框
	 * */
	public void clickselect() {
		this.click(this.bjselectEle());
	}
	
	/**
	 * 点击选择班级
	 * */
	public void clickBj(String bjname) {
		List<WebElement> bjlist = this.bjEle();
		for(WebElement bj:bjlist) {
			if(bj.getText().equals(bjname)) {
				this.click(bj);
				break;
			}
			//logger.info("没有找到班级");
		}
	}
	
	/**
	 * 获取radioele
	 * */
	public WebElement radioele(){
		return this.element(getByLocator.getLocator("radio"));
	}
	//老师tab页radio 元素
	public WebElement radioTeaEle() {
		return this.element(getByLocator.getLocator("radiotea"));
	}
	/**
	 * 点击radio
	 * */
	public void clickradio() {
		this.click(this.radioele());
	}
	/**
	 * 获取卡片数量ele
	 * */
	public WebElement cardNoEle() {
		return this.element(getByLocator.getLocator("cardno"));
	}
	/**
	 * 返回卡片数量
	 * */
	public int cardNo() {
		String no = this.cardNoEle().getText();
		int cardno = Integer.parseInt(no);
		return cardno;
	}
	
	/**
	 * 获取卡号输入框ele
	 * */
	public WebElement inputCardEle() {
		return this.element(getByLocator.getLocator("sendcard"));
	}
	/**
	 * 输入卡号
	 * */
	public void sendkeysCard(String card) {
		this.sendKeys(this.inputCardEle(), card);
	}
	/**
	 * 卡号新增按钮ele
	 * */
	public WebElement newCardEle() {
		return this.element(getByLocator.getLocator("newcard"));
	}
	/**
	 * 点击新增按钮
	 * */
	public void clickNewCard() {
		this.click(this.newCardEle());
	}
	
	/**
	 * 获取新增卡号ele
	 * */
	public WebElement cardlistEle() {
		return this.element(getByLocator.getLocator("cardtext"));
	}
	/**
	 * 获取新增卡号
	 * */
	public String addedCardNo() {
		return cardlistEle().getText();
	}
	/**
	 * 获取导出按钮ele
	 * */
	public WebElement exportBtnEle() {
		return this.element(getByLocator.getLocator("exportbtn"));
	}
	/**
	 * 点击导出按钮
	 * */
	public void clickexport() {
		this.click(this.exportBtnEle());
	}
	/**
	 * 返回导出前后文档中文件数
	 * */
	public int fileNumber(String filepath) {
		File dir = new File(filepath);
		File[] dir_contents = dir.listFiles();
		return dir_contents.length;
	}
	/**
	 * 获取删除卡号ele
	 * */
	public List<WebElement> delcardEle() {
		return this.elementList(getByLocator.getLocator("delStucard"));
	}
	
	/**
	 * 点击删除按钮
	 * */
	public void clickdel(int index) {
		this.click(this.delcardEle().get(index));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.waitAlertVisible();
		driver.alertOk();
	}
	
	//批量上传卡片，上传按钮元素
	public WebElement batchCardUploadInputEle() {
		return this.element(getByLocator.getLocator("carduploadinput"));
	}
	
	//搜索框input 元素
	public WebElement searchInputEle() {
		return this.element(getByLocator.getLocator("searchinput"));
	}
	//老师tab页搜索框input 元素按钮
	public WebElement searchInputTeaEle() {
		return this.element(getByLocator.getLocator("searchinputteacher"));
	}
	
	//stu 搜索按钮元素
	public WebElement stuSearchBtnEle() {
		return this.element(getByLocator.getLocator("stusearchbtn"));
	}
	//teacher 搜索按钮元素
	public WebElement teaSearchBtnEle() {
		return this.element(getByLocator.getLocator("teasearchbtn"));
	}
	
	
	
	
	
}
