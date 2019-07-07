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
	
	//获取 tab 元素
	public List<WebElement> tabsElement(){
		return this.elementList(getByLocator.getLocator("tabs"));
	}
	
	//点击 tab，索引0:考勤组管理，1：教师考勤组设置，2：学生考勤设置，3：学生考勤组设置，4：教师宿舍门禁组设置
	public void clickTab(int indext) {
		this.click(this.tabsElement().get(indext));
	}
	
	//获取 老师考勤管理的选择框元素
	public WebElement selectElement(){
		return this.element(getByLocator.getLocator("teacherSelect"));
	} 
	
	//获取  老师考勤管理的选择框内容元素
	public List<WebElement> AttendanceGroupElements(){
		return this.elementList(selectElement(), getByLocator.getLocator("teacherSelectOption"));
	}
	//点击 选择 考勤组
	public void clickAttendanceGroup(int index) {
		this.click(this.AttendanceGroupElements().get(index));
	}
	
	//获取导出元素
	public WebElement downloadElement(){
		return this.element(getByLocator.getLocator("downloadBtn"));
	} 
	//点击导出
	public void clickDownload() {
		this.click(this.downloadElement());
	}
	
	//获取 学生考勤组选择框
	public WebElement selectStuElement(){
		return this.element(getByLocator.getLocator("stuSelect"));
	} 
	
	//获取  学生考勤管理的选择框内容元素
	public List<WebElement> StuAttendanceGroupElements(){
		return this.elementList(selectStuElement(), getByLocator.getLocator("teacherSelectOption"));
	}
	//点击 选择 考勤组
	public void clickStuAttendanceGroup(int index) {
		this.click(this.StuAttendanceGroupElements().get(index));
	}
	//获取 学生班级选择框的元素
	public WebElement getSelectClass(){
		return this.element(getByLocator.getLocator("classSelect"));
	} 
	
	//获取导出元素
	public WebElement downloadStuElement(){
		return this.element(getByLocator.getLocator("downloadStuBtn"));
	} 
	//点击导出
	public void clickStuDownload() {
		this.click(this.downloadStuElement());
	}
	
	// 获取 导入按钮元素
	public List<WebElement> getUpload() {
		return this.elementList(getByLocator.getLocator("uploadInput"));
	}
	
	//上传 导入 考勤组
	public void sendkeysAttGp(int index,String value) {
		this.sendKeys1(getUpload().get(index), value);
		
	}
	
	//上传 alert 信息
	public WebElement getAlert() {
		return this.element(getByLocator.getLocator("alertInfo"));
	}
	
	//等待alert 信息
	public void waitAlert() {
		driver.waitElementPresence(getByLocator.getLocator("alertInfo"));
	}
	
	//获取 总数 的元素
	public List<WebElement> getSum(){
		return this.elementList(getByLocator.getLocator("strong"));
	}
	
	//获取 总数，把总数转化为int 类型
	public int getSumInt() {
		String sumS = this.getSum().get(2).getText();
		int sumInt = Integer.valueOf(sumS);
		return sumInt;
	}
	
	
	
	
	
	
	
	
	
	
}
