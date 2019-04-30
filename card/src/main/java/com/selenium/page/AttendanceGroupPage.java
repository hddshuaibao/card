package com.selenium.page;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;

import com.selenium.base.DriverBase;
import com.selenium.util.getByLocator;

public class AttendanceGroupPage extends BasePage{

	Random rand = new Random();
	public AttendanceGroupPage(DriverBase driver) {
		super(driver);
		
	}
	
	/**
	 * 获取班次管理，新建班次和新增时间button element 按钮list
	 * */
	public List<WebElement> getButtonEle() {
		WebElement eleNode = this.element(getByLocator.getLocator("buttonNode"));
		List<WebElement> buttonEle = eleNode.findElements(getByLocator.getLocator("buttonGroup"));
		return buttonEle;
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
	/**
	 * 获取新建班次关闭button element 按钮list
	 * */
	public List<WebElement> getButtonEle2() {
		List<WebElement> buttonEle = this.elementList(getByLocator.getLocator("buttonGroup"));
		return buttonEle;
	}
	/**
	 *点击新建班次关闭button element
	 * */
	public void buttonClick2(String buttonName) {
		for(int i = 0;i<this.getButtonEle2().size();i++) {
			if(this.getButtonEle2().get(i).getText().equals(buttonName)) {
				this.getButtonEle2().get(i).click();
				break;
			}
		}
}
	
	/**
	 * 获取班次名称element
	 * */
	public WebElement getBanciNameEle() {
		return this.element(getByLocator.getLocator("banciName"));
	}
	/*
	 * 输入班次名称
	 * **/
	public void sendKeysBanciName(String banciName) {
		this.sendKeys(this.getBanciNameEle(), banciName);
	}
	/**
	 * 获取添加时段element
	 * */
	public WebElement getAddTimeEle() {
		return this.element(getByLocator.getLocator("addTime"));
		
	}
	/**
	 * 点击添加时段
	 * */
	public void clickAddTime() {
		this.click(this.getAddTimeEle());
	}
	/**
	 * 获取时间列元素
	 * */
	public List<WebElement> getTimeEle() {
		return this.elementList(getByLocator.getLocator("dateTimePick"));
	}
	/**
	 * 点击时间列元素
	 * */
	public void clickTimeEle(int i) {
		this.click(getTimeEle().get(i));
	}
	/**
	 * 获取时间list
	 * */
	public List<WebElement> getTimeSelectList(WebElement ele){
		return this.elementList(ele, getByLocator.getLocator("selectTime"));
	}
	
	/**
	 * 设置时间 startTime1,String startTime2,String endTime1,String endTime2
	 * */
	public void setTime(int timePickeNo,int[] timeNo) {
		List<WebElement> select = this.getTimeSelectList(getTimeEle().get(timePickeNo));
		for(WebElement ele:select) {
			List<WebElement> selectOption =	this.elementList(ele, getByLocator.getLocator("TimeOption"));
			selectOption.get(timeNo[select.indexOf(ele)]).click();	
		}
		
	}
	
	/**
	 * 获取保存buttonelement,获取模态框，调用buttonClick
	 * */
	/**
	 * 获取保存后的班次名称和时间段的tr列数
	 *
	 * */
	public int getBanciTrNo(String banciName) {
		int trNo = 0;
		List<WebElement> banciTableTr =this.elementList(getByLocator.getLocator("banciTableTr"));
		for(WebElement tr:banciTableTr) {
			if(!this.elementList(tr, getByLocator.getLocator("banciTableTrTd")).isEmpty()) {
				List<WebElement> tdList = this.elementList(tr, getByLocator.getLocator("banciTableTrTd"));
				if(tdList.get(0).getText().equals(banciName)) {
					trNo = banciTableTr.indexOf(tr);
					//System.out.println(trNo);
					break;
				}else {
					trNo = 0;
				}
			}
		}
			
		return trNo;
	}
	
	
	/**
	 * 根据tr列数，验证班次时间是否正确
	 * */
	public boolean checkBanciTime(String banciName,String banciTime) {
		int trNo = this.getBanciTrNo(banciName);
		System.out.println(trNo);
		List<WebElement> banciTableTr =this.elementList(getByLocator.getLocator("banciTableTr"));
		List<WebElement> tdList = this.elementList(banciTableTr.get(trNo), getByLocator.getLocator("banciTableTrTd"));
		if(tdList.get(1).getText().equals(banciTime)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 根据tr列数，获取删除element
	 * */
	public WebElement deletEle(String banciName) {
		int deletNO = this.getBanciTrNo(banciName);
		System.out.println(banciName+"班次在第"+deletNO+"行");
		List<WebElement> deletList = this.elementList(getByLocator.getLocator("deleteBanci"));
		//System.out.println("删除列表的长度"+deletList.size());
		return deletList.get(deletNO-1);
	}
	
	/**
	 * 点击删除当前班次
	 * */
	public void deletBance(String banciName) {
		this.click(this.deletEle(banciName));
	}
	
	/**
	 * 转换到模态窗口
	 * */
	public void switchToModal() {
		driver.switchToModal();
	}
	
	/**
	 * 获取考勤组定位设置开关
	 * */
	public WebElement switchButtonEle() {
		return this.element(this.element(getByLocator.getLocator("modalTop")), getByLocator.getLocator("switchButton"));
	}
	/**
	 * 获取考勤组定位设置开关switch_container
	 * */
	public WebElement switchContainerEle() {
		return this.element(this.element(getByLocator.getLocator("modalTop")), getByLocator.getLocator("switchContainer"));
	}
	/**
	 * 获取打卡位置打卡范围elelist
	 * */
	public List<WebElement> clockInfoEle(){
		List<WebElement> clockinfo = this.elementList(this.element(getByLocator.getLocator("modalTop")), getByLocator.getLocator("clockInput"));
		return clockinfo;
	}
	/**
	 * 获取坐标地址ele
	 * */
	public WebElement addressEle() {
		return element(element(getByLocator.getLocator("modalTop")),getByLocator.getLocator("address"));
	}
	/**
	 * 点击考勤组定位设置开关
	 * */
	public void clickSwitch() {
		click(switchButtonEle());
	}
	/**
	 * 返回考勤组定位设置开关style
	 * */
	public String getSwitchStyle() {
		return switchContainerEle().getAttribute("Style");
	}
	/**
	 * 点击坐标地址链接
	 * */
	public void clickAddress() {
		click(addressEle());
	}
	/**
	 * 验证坐标地址链接，按钮置灰不可点
	 * */
	public int  ifToAmap() {
		return driver.getWindHand().size();
	}
	
	
	/**
	 * 输入打卡位置范围
	 * */
	public String sendkeysClockInfo(String position) {
		int random = rand.nextInt(1000)+1;
		String range = String.valueOf(random);
		this.sendKeys(clockInfoEle().get(0), position);
		this.sendKeys(clockInfoEle().get(1), range);
		return range;
	}
	/**
	 * 验证打卡位置范围输入框是否disabled
	 * */
	public boolean ifDisabled() {
		//System.out.println(clockInfoEle().get(0).getAttribute("disabled"));
		if(clockInfoEle().get(0).getAttribute("disabled").equals("true")) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
}
