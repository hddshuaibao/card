package com.selenium.page;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.selenium.base.DriverBase;
import com.selenium.util.getByLocator;

public class DormitoryPage extends BasePage{

	public DormitoryPage(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 楼宇新增按钮ele
	 * */
	public WebElement addBuildingEle() {
		return this.element(getByLocator.getLocator("Building"));
	}
	/**
	 * 点击新增按钮
	 * */
	public void clickAddBuilding() {
		this.click(this.addBuildingEle());
	}
	
	/**
	 * 新增楼宇名称ele
	 * */
	public WebElement buildNameEle() {
		return this.element(getByLocator.getLocator("BuildingName"));
	}
	/**
	 * 输入楼宇名称
	 * */
	public void sendkeysBuildName(String buildname) {
		this.sendKeys(this.buildNameEle(), buildname);
	}
	
	/**
	 * 新增楼宇层数ele
	 * */
	public WebElement buildLevelEle() {
		return this.element(getByLocator.getLocator("BuildingLevels"));
	}
	/**
	 * 输入楼宇层数
	 * */
	public void sendkeysBuildLevel(String level) {
		this.sendKeys(this.buildLevelEle(), level);
	}
	/**
	 * 验证新增框是否出现
	 * */
	public boolean assertAddEleIs() {
		return this.assertElementIs(this.buildNameEle());
	}
	
	/**
	 * 获取所要的按钮元素
	 * */
	public List<WebElement> getButtonEle() {
		return this.elementList(getByLocator.getLocator("buttonGroup"));
	}
	
	/**
	 * 获取所要的按钮元素，并点击
	 * */
	public void buttonClick(String buttonName) {
			for(int i = 0;i<this.getButtonEle().size();i++) {
				if(this.getButtonEle().get(i).getText().equals(buttonName)) {
					this.getButtonEle().get(i).click();
					break;
				}
			}
	}
	//获取新增楼宇确定按钮ele
	public WebElement addConfirmBtnEle() {
		return this.element(getByLocator.getLocator("addBuildConfirmBtn"));
	}
	//点击获取新增楼宇确定按钮
	public void clickaddConfirmBtn() {
		this.click(this.addConfirmBtnEle());
	}
	/**
	 * 获取已增楼宇名称ele
	 * */
	public List<WebElement> addedBuildEle(){
		return this.elementList(getByLocator.getLocator("AddedBuildName"));
	}
	/**
	 * 寻找新增楼宇,并返回楼宇所在个数
	 * */
	public int findBuild(String buildname) {
		int levelno = -1;
		List<WebElement> builds = addedBuildEle();
		for(WebElement build:builds) {
			if(build.getText().equals(buildname)) {
				levelno =builds.indexOf(build);
				break;
			}
		}
		return levelno;
	}
	/**
	 * 获取楼宇的删除ele
	 * */
	public List<WebElement> delBuildEle(){
		return this.elementList(getByLocator.getLocator("deletBuild"));
	}
	/**
	 * 获取删除确定按钮ele
	 * */
	public List<WebElement> delBuildCheckEle() {
		return this.elementList(this.element(getByLocator.getLocator("deletBuildBtnsNode")), getByLocator.getLocator("deletBuildBtns"));
	}
	
	/**
	 * 删除某个楼宇
	 * */
	public void clickBuildDel(String buildname,int levelno) {
		List<WebElement> delList = this.delBuildEle();
		for(WebElement del:delList) {
			if(delList.indexOf(del)==levelno) {
				this.click(del);
				this.click(this.delBuildCheckEle().get(1));
			}
		}
	}
	
	/**
	 * 根据所建楼宇的返回的个数，获取楼宇层数ele
	 * */
	public List<WebElement> levelEle(String buildname){
		int levelno = this.findBuild(buildname);
		WebElement levelgroup = this.elementList(getByLocator.getLocator("levelNode")).get(levelno);
		return this.elementList(levelgroup, getByLocator.getLocator("level"));
	}
	
	/**
	 * 返回楼宇层数
	 * */
	public int levelNo(String buildname) {
		return this.levelEle(buildname).size();
	}
	/**
	 * 获取栏目ele
	 * */
	public WebElement DormitoryPageEle(){
		return this.element(getByLocator.getLocator("Dormitory"));
	}
	/**
	 * 点击进入寝室管理页面
	 * */
	public void clickDormitoryPage() {
		this.click(this.DormitoryPageEle());
	}
	
}
