package com.selenium.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.base.DriverBase;
import com.selenium.util.getByLocator;


public class BasePage {

	public DriverBase driver;
	public BasePage(DriverBase driver) {
		this.driver = driver;
	}
	//获取alert 文字内容
	public String getAlertText() {
		return driver.getAlertText();
	}
	/*
	 *定位元素 方法
	 * */
	public WebElement element(By by) {
		WebElement element = driver.findElement(by);
		return element;
	}
	/**
	 * 定位子元素的element
	 * */
	public WebElement element(WebElement ele,By by) {
		return ele.findElement(by);
	}
	/**
	 * 获取elementlist
	 * */
	public List<WebElement> elementList(By by){
		return driver.findElements(by);
	}
	
	/**
	 * 元素下的获取elementlist
	 * */
	public List<WebElement> elementList(WebElement ele,By by){
		return ele.findElements(by);
	}
	
	/*
	 * 点击元素方法
	 * */
	public void click(WebElement element) {
		if(element != null) {
			element.click();
		}else {
			System.out.println(element+"元素为空");
		}
	}
	
	/*
	 * 元素输入方法
	 * */
	public void sendKeys(WebElement element,String value) {
		if(element != null) {
			element.clear();
			element.sendKeys(value);
		}else {
			System.out.println(element+"元素为空");
		}
	}
	
	
	/*
	 * 判断元素是否显示
	 * */
	public boolean assertElementIs(WebElement element) {
		return element.isDisplayed();
	}
	
	/**
	 * 获取元素文字信息
	 * */
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	/**
	 * 验证文字是否为预期结果
	 * */
	public void assertTextIs(String actualtext,String expectedtext) {
		Assert.assertEquals(actualtext, expectedtext);
	}
	/**
	 * 遍历应用，进入想要的应用
	 * */
	public void goApplication(String appName) {
		WebElement element = this.element(getByLocator.getLocator("yingyongManage"));
		driver.mouseOverAction(element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> applist = new ArrayList<WebElement>();
		applist = driver.findElements(getByLocator.getLocator("yingyongList"));
		for(int i = 0;i<applist.size();i++) {
			System.out.println(applist.get(i).getText());
			if(applist.get(i).getText().equals(appName)) {
				applist.get(i).click();
				System.out.println("进入"+appName);
				break;
			}
		}
	}
	
	/**
	 * 找栏目
	 * */
	public void goLanmu(String lm) {
		List<WebElement> leftColumn = this.elementList(getByLocator.getLocator("columnList"));
		for(int i=0;i<leftColumn.size();i++) {
			if(leftColumn.get(i).getText().contains(lm)) {
				leftColumn.get(i).click();
				break;
			}
		}
	}
}
