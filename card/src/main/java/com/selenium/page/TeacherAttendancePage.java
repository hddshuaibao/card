package com.selenium.page;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.selenium.base.DriverBase;
import com.selenium.util.getByLocator;

public class TeacherAttendancePage extends BasePage{

	public TeacherAttendancePage(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private  WebElement element;
	private  List<WebElement> elements;
	
	//获取 教师考勤统计 sidebar 元素
	public WebElement teacherAttendBar() {
		return this.element(getByLocator.getLocator("teacherbar"));
	}
	
	//查询按钮元素
	public  WebElement searchButton(WebDriver driver) {
		element=driver.findElement(By.xpath("//*[@id='app']/div/div[7]/div[2]/div[4]/div/div[6]/div/div[1]/div[1]/div/button[1]"));
		return element;                     
	}
	
	
	//考勤明细页查询按钮
	public  WebElement AttendanceDetailSearchButton(WebDriver driver) {
		element=driver.findElement(By.xpath("//*[@id='app']/div/div[7]/div[2]/div[4]/div/div[6]/div/div[3]/div[1]/button[1]"));
		return element;                      
	}
	//日期统计，人员统计，考勤明细，请假记录，tab页列表
	public  List<WebElement> tabLeaf(WebDriver driver){
		elements=driver.findElements(By.className("nav-tab"));
		return elements;
	}
	
	//表头列表元素列表
	public  List<WebElement> dateRecordTabHeader(WebDriver driver){
		elements=driver.findElements(By.className("text-left"));
		return elements;
	}
	
	public  WebElement backTrackingButton(WebDriver driver) {
		element=driver.findElement(By.xpath("//*[@id='app']/div/div[7]/div[2]/div[4]/div/div[6]/div/div[3]/div[1]/button[2]"));                           
		return element;
	}
	
	//补录后选择当天时间
	public  WebElement selectToday(WebDriver driver) {
		element=driver.findElement(By.linkText("当天"));
		return element;
	}
	
	
	
	//补录姓名输入框元素
	public  WebElement backTrackingName(WebDriver driver) {
		element=driver.findElement(By.className("form-control"));
		return element;
	}
	//补录时间框元素
	
	public  WebElement backTrackingTimePicker(WebDriver driver) {
		element=driver.findElement(By.className("datetime-picker"));
		return element;
	}
	//补录时间hour选择框元素
	//*[@id="addwlzy-form"]/fieldset/div[2]/div/div/div/div/select[1]
	public  List<WebElement> backTrackingTimePickerHour(WebDriver driver) {
		elements=driver.findElements(By.tagName("select"));
		return elements;
	}
	
	//补录选择时间确定按钮元素
	
	public  WebElement backTrackingTimePickerConfirm(WebDriver driver) {
		element=driver.findElement(By.xpath("//*[@id='addwlzy-form']/fieldset/div[2]/div/div/div/div/div"));
		return element;
	}
	
	//补录保存按钮元素
	public  WebElement backTrackingSave(WebDriver driver) {
		element=driver.findElement(By.xpath("//*[@id='app']/div/div[7]/div[2]/div[4]/div/div[2]/div/div/div[3]/button[2]"));
		return element;
	}
	
	//补录状态onpage
	public  WebElement backTrackingStateOnPage_lf1(WebDriver driver) {
		//兰芳1，2，倩丽1
		WebElement element=driver.findElement(By.xpath("//*[@id='app']/div/div[7]/div[2]/div[4]/div/div[6]/div/div[3]/div[2]/div[1]/table/tbody/tr[4]/td[5]/span/span"));
		
		return element;
	}
	
	public  WebElement backTrackingStateOnPage_lf2(WebDriver driver) {
		//兰芳1，2，倩丽1
		                                               //*[@id="app"]/div/div[7]/div[2]/div[4]/div/div[6]/div/div[3]/div[2]/div[1]/table/tbody/tr[4]/td[5]/span/span
		WebElement element=driver.findElement(By.xpath("//*[@id='app']/div/div[7]/div[2]/div[4]/div/div[6]/div/div[3]/div[2]/div[1]/table/tbody/tr[4]/td[6]/span/span"));
		
		return element;
	}
	public  WebElement backTrackingStateOnPage_ql1(WebDriver driver) {
		//兰芳1，2，倩丽1
		                                               //*[@id="app"]/div/div[7]/div[2]/div[4]/div/div[6]/div/div[3]/div[2]/div[1]/table/tbody/tr[4]/td[5]/span/span
		WebElement element=driver.findElement(By.xpath("//*[@id='app']/div/div[7]/div[2]/div[4]/div/div[6]/div/div[3]/div[2]/div[1]/table/tbody/tr[8]/td[5]/span/span"));
		
		return element;
	}
	
	
	//表头列表名称判断   n为1：日期统计页，n为2：考勤明细
	public  Boolean headerMatch(WebDriver driver,Integer n) {
		
		boolean result=false;
		List<WebElement> header=this.dateRecordTabHeader(driver);
		System.out.println(header.size());
		String[] pageHeader1=new String[8];
		String[] expectedHeader1= {"日期","总人数","打卡人数","正常到校人数","迟到/早退人数","漏打卡人数","未打卡人数","请假人数"};
		String[] pageHeader2 = new String[6];
		String[] expectedHeader2 = {"姓名","日期","考勤组","今日考勤状态","打卡1","打卡2"};
		if(n==1) {
			for(int i=0;i<8;i++) {
				pageHeader1[i]=header.get(i).getText();
			}
			if(Arrays.equals(pageHeader1, expectedHeader1)) {
				result=true;
				//return result;
			}
		}
		else if(n==2) {
			List<WebElement> header2 = this.dateRecordTabHeader(driver);
			System.out.println("tab 头名称"+header2.size()+header2.get(17).getText()+header2.get(18).getText()+
					header2.get(19).getText()+header2.get(20).getText()+header2.get(21).getText()+header2.get(22).getText());
			for(int i=17;i<23;i++) {
				pageHeader2[i-17]=header2.get(i).getText();
			}
			if(Arrays.equals(pageHeader2, expectedHeader2)) {
				result=true;
				//return result;
			}
			
		}else {
			System.out.println("1为：日期统计页，2为：考勤明细");
		}
		System.out.println("返回结果"+result);
		return result;
		
	}
	
	//补录功能封装
	public  void backTracking(WebDriver driver,String Name,String hour,String min,String state) throws Exception {
		
		
		backTrackingName(driver).sendKeys(Name);
		System.out.println("输入补录名字");
		backTrackingTimePicker(driver).click();
		Select selectHour = new Select(backTrackingTimePickerHour(driver).get(0));
		selectHour.selectByValue(hour);
		Thread.sleep(1000);
		Select selectMin = new Select(backTrackingTimePickerHour(driver).get(1));
		selectMin.selectByValue(min);
		Thread.sleep(1000);
		backTrackingTimePickerConfirm(driver).click();
		backTrackingSave(driver).click();
		
	}
	
	
	
	
	
}
