package com.selenium.page;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.selenium.base.DriverBase;
import com.selenium.util.getByLocator;

public class intelligentPage extends BasePage{

	
	public intelligentPage(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//获取alert 的text
	public String getAlertText() {
		return driver.getAlertText();
	}
	//获取智能硬件tab ele
	public WebElement intelligentTabEle() {
		return this.element(getByLocator.getLocator("intelligentTab"));
	}
	
	//点击取智能硬件tab
	public void clickIntelligentTab() {
		this.click(this.intelligentTabEle());
	}
	
	//获取新增设备按钮ele
	public WebElement newMacBtnEle() {
		return this.element(getByLocator.getLocator("newMacBtn"));
	}
	
	//点击新增设备按钮
	public void clickNewMacBtn() {
		this.click(this.newMacBtnEle());
	}
	
	//获取设备id
	public WebElement macIdInput() {
		return this.element(getByLocator.getLocator("MacId"));
	}
	
	//输入设备id
	public void sendkeysMacId(String macid) {
		this.sendKeys(this.macIdInput(), macid);
	}
	
	//获取设备选择框ele
	public WebElement macNameEle() {
		return this.element(getByLocator.getLocator("MacName"));
	}
	
	//点击设备选择框
	public void clickMacNameInput() {
		this.click(this.macNameEle());
	}
	
	//获取设备名称列eles
	public List<WebElement> macNameEles(){
		return this.elementList(getByLocator.getLocator("MacNameList"));
	}
	
	//输入选择的设备并点击
	public void clickMacNameSelected(String macname) {
		List<WebElement> list = this.macNameEles();
		for(WebElement mac:list) {
			if(mac.getText().equals(macname)) {
				this.click(mac);
				break;
			}
		}
	}
	
	//获取提交按钮ele
	public WebElement addMacBtnEle() {
		return this.element(getByLocator.getLocator("addMacBtn"));
	}
	
	//点击提交按钮
	public void clickAddMacBtn() {
		this.click(this.addMacBtnEle());
	}
	//获取新加设备编号栏ele
	public WebElement addedMacId() {
		List<WebElement> macs = this.elementList(getByLocator.getLocator("addedMac"));
		List<WebElement> macstext = this.elementList(macs.get(macs.size()-1), getByLocator.getLocator("addedMacId"));
		return macstext.get(2);
	}
	//获取新增设备id
	public String addedMacIdText() {
		return this.getElementText(this.addedMacId());
	}
	//获取设备删除ele
	public List<WebElement> macDelEle() {
		return this.elementList(getByLocator.getLocator("macDelBtn"));
	}
	//点击第一个设备的删除按钮
	public void clickMacDel() {
		this.click(this.macDelEle().get(1));
	}
	
	//获取电子班牌班级选择框ele
	public WebElement bjSelectEle() {
		return this.element(getByLocator.getLocator("bjSelect"));
	}
	//点击电子班牌班级选择框
	public void clickBjSelect() {
		this.click(this.bjSelectEle());
	}
	//获取班级选择列表eles
	public List<WebElement> bjListEles(){
		return this.elementList(getByLocator.getLocator("bjList"));
	}
	//点击选择的班级
	public void clickBj(String bjname) {
		List<WebElement> bjlist = this.bjListEles();
		for(WebElement bj:bjlist) {
			if(bj.getText().equals(bjname)) {
				this.click(bj);
				break;
			}
		}
	}
	//人脸考勤机，下发数据范围选择框ele
	public WebElement personSelectEle() {
		return this.element(getByLocator.getLocator("personSelect"));
	}
	//人脸考勤机，点击下发数据范围选择框
	public void clickPersonSelect() {
		this.click(this.personSelectEle());
	}
	//人脸考勤机，下发数据范围elelist
	public List<WebElement> personSelectList(){
		return this.elementList(getByLocator.getLocator("personList"));
	}
	//点击选择的下发数据范围
	public void clickPerson(String per) {
		List<WebElement> personlist = this.personSelectList();
		for(WebElement person:personlist) {
			if(person.getText().equals(per)) {
				this.click(person);
				break;
			}
		}
	}
	//uface，下发数据范围为学生时选择年级框ele
	public WebElement njSelectEle() {
		return this.element(getByLocator.getLocator("njSelect"));
	}
	//uface，点击下发数据范围为学生时年级框
	public void clickNjSelect() {
		this.click(this.njSelectEle());
	}
	//uface,下发数据范围为学生时年级列表eles
	public List<WebElement> njSelectList(){
		return this.elementList(getByLocator.getLocator("njList"));
	}
	//uface,下发数据范围为学生时点击选择的学生
	public void clickNj(String nj) {
		List<WebElement> njlist = this.njSelectList();
		for(WebElement njele:njlist) {
			if(njele.getText().equals(nj)) {
				this.click(njele);
				break;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
