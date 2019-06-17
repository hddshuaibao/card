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
	//点击楼宇下拉arrow
	public void clickArrow(int index) {
		this.click(this.arrowEles().get(index));
	}
	//获取删除楼宇提示信息ele
	public WebElement delBuildTextEle() {
		return this.element(getByLocator.getLocator("deletBuildInfo"));
	}
	
	//获取删除楼宇提示信息
	public String getDelBuildText() {
		WebElement delAlert = this.element(getByLocator.getLocator("deletBuildInfo"));
		return delAlert.getText();
	}
	//获取下拉箭头eles
	public List<WebElement> arrowEles(){
		return this.elementList(getByLocator.getLocator("arrow"));
	}
	//获取新增寝室按钮ele
		public WebElement addRoomBtnEle() {
			return this.element(getByLocator.getLocator("addRoomBtn"));
		}
	//获取宿舍数量输入框ele
		public WebElement roomNoEle() {
			WebElement roomNoNode = this.element(getByLocator.getLocator("addRoomNumber"));
			return this.element(roomNoNode, getByLocator.getLocator("addRoomNumberInput"));
			
		}
	//获取宿舍规模输入框ele
		public WebElement roomScaleEle() {
			WebElement roomScaleNode = this.element(getByLocator.getLocator("addRoomScale"));
			return this.element(roomScaleNode, getByLocator.getLocator("addRoomScaleInput"));
		}
	//获取新增宿舍确定按钮ele
		public WebElement addRoomCheckBtnEle() {
			return this.element(getByLocator.getLocator("addRoomConfirmBtn"));
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
	
	//点击楼宇删除按钮
	public void clickDelBtn(int index) {
		this.click(this.delBuildEle().get(index));
	}
	//点击删除确定按钮
	public void clickBuildCheckBtn(int index) {
		this.click(this.delBuildCheckEle().get(index));
	}
	/**
	 * 点击某个楼宇删除按钮
	 * */
	public void clickBuildDel(String buildname) {
		List<WebElement> delList = this.delBuildEle();
		int index = delList.size()-2;
		this.click(delList.get(index));
		this.clickBuildCheckBtn(1);
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
	
	//点击新增寝室按钮
	public void clickAddRoomBtn() {
		this.click(this.addRoomBtnEle());
	}
	
	//点击楼宇名
	public void clickBuild(int index) {
		this.click(this.addedBuildEle().get(index));
	}
	//输入宿舍数量
	public void sendkeysRoomNo(String roomno) {
		this.sendKeys(this.roomNoEle(), roomno);
	}
	//输入宿舍规模
	public void sendkeysRoomScale(String roomscale) {
		this.roomScaleEle().clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.sendKeys(this.roomScaleEle(), roomscale);
	}
	//点击新增宿舍确定按钮
	public void clickAddRoomCheckBtn() {
		this.click(this.addRoomCheckBtnEle());
	}
	//获取新增的宿舍的ele list
		public List<WebElement> addedRoomEles(){
			return this.elementList(getByLocator.getLocator("roomList"));
		}
		//鼠标移到新增的宿舍上
		public void mouseoverAddedRoom(WebElement ele) {
			driver.mouseOverAction(ele);
		}
		//获取宿舍删除按钮的ele list
		public List<WebElement> addedRoomDelEles(){
			return this.elementList(getByLocator.getLocator("roomListDel"));
		}
		
		//宿舍删除确定按钮ele
		public WebElement confirmDelEle() {
			return this.element(getByLocator.getLocator("roomDelConfrim"));
		}
		//点击宿舍删除按钮
		public void clickAddedRoomDel(int i) {
			this.click(this.addedRoomDelEles().get(i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.click(this.confirmDelEle());
		}
		
		//点击楼宇
		
		//点击宿舍cloumn
		public void clickAddedRoom(int i) {
			this.click(this.addedRoomEles().get(i));
		}
		//获取添加学生按钮的eles
		public List<WebElement> addStuBtnEles(){
			return this.elementList(getByLocator.getLocator("addStuBtn"));
		}
		//点击添加学生按钮
		public void clickAddStuBtn(int i) {
			this.click(this.addStuBtnEles().get(i));
		}
		//点击第二个arrow，添加学生宿舍楼
		public void clickStuArrow() {
			this.click(this.arrowEles().get(3));
		}
	
		//班级input ele
		public WebElement bjInput() {
			return this.element(getByLocator.getLocator("bjinput"));
		}
		//点击班级input
		public void clickBjInput() {
			this.click(this.bjInput());
		}
		//获取草莓三班班级ele
		public WebElement selectedBj() {
			return this.element(getByLocator.getLocator("bjcaomei"));
		}
		//点击选择班级
		public void clickSelectedBj() {
			this.click(this.selectedBj());
		}
		
		//获取学生姓名input ele
		public WebElement StuInput() {
			return this.element(getByLocator.getLocator("Stuinput"));
		}
		//点击学生姓名input
		public void clickStuInput() {
			 this.click(this.StuInput());
		}
		//获取草莓三班，学生选择列表
		public List<WebElement> StuList(){
			return this.elementList(getByLocator.getLocator("StuList"));
			
		}
		//根据学生姓名，输入选择的学生姓名
		public void selectStu(String stuname) {
			List<WebElement> Stulist = this.StuList();
			for(WebElement stu: Stulist ) {
				if(stu.getText().equals(stuname)) {
					this.click(stu);
					break;
				}
			}
		}
		//获取添加学生确定按钮ele
		public WebElement addStuCheckBtnEle() {
			return this.element(getByLocator.getLocator("addStuCheckBtn"));
		}
		//点击添加学生确定按钮
		public void clickAddStuCheck() {
			this.click(this.addStuCheckBtnEle());
		}
		//获取宿舍下学生床位信息：学生姓名
		public List<WebElement> bedOfStuNameEles() {
			return this.elementList(getByLocator.getLocator("bedOfStuName"));
		}
		//获取宿舍下学生床位信息：学生班级
		public List<WebElement> bedOfStuClassEles(){
			return this.elementList(getByLocator.getLocator("bedOfStuClass"));
		}
		//获取宿舍下学生的床位ele
		public List<WebElement> bedOfStu(){
			return this.elementList(getByLocator.getLocator("cardImg"));
		}
		//鼠标移动至学生的床位上
		public void mouseOverBed(int index) {
			driver.mouseOverAction(this.bedOfStu().get(index));
		}
		//获取学生床位上的删除按钮
		public List<WebElement> delBed() {
			return this.elementList(getByLocator.getLocator("delStu"));
		}
		//获取学生床位删除按钮的style 信息
		public String delBtnStyle(int index) {
			return this.delBed().get(index).getAttribute("style");
		}
		//点击学生床位的删除按钮
		public void clickDelBtnOfBed(int index) {
			this.click(this.delBed().get(index));
		}
		//获取删除学生床位的确定按钮元素
		public WebElement delStuCheckBtnEle() {
			return this.element(getByLocator.getLocator("delStuCheckBtn"));
		}
		//点击删除学生床位确定按钮
		public void clickdelStuCheckBtn() {
			this.click(this.delStuCheckBtnEle());
		}
		
}
