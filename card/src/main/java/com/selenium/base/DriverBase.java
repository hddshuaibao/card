package com.selenium.base;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;


public class DriverBase{
	
	public WebDriver driver;
	public DriverBase(Browser browser) {
		 this.driver = DriverFactory.getDriver(browser);
	}
	
	/*
	 * 进入网页
	 * */
	public void getUrl(String url) {
		driver.get(url);
		System.out.println("进入"+url);
	}

	/*
	 * 关闭浏览器
	 * */
	public void stop() {
		driver.quit();
		System.out.println("关闭浏览器");
		
	}
	/**
	 * 确认alert
	 * */
	public void alertOk() {
		driver.switchTo().alert().accept();
	}
	/**
	 * 取消alert
	 * */
	public void alertNo() {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * 定位元素封装
	 * */
	public WebElement findElement(By by) {
		WebElement ele = driver.findElement(by);
		return ele;
	}
	
	/**
	 * 定位元素集合封装
	 * */
	public List<WebElement> findElements(By by) {
		List<WebElement> ele = driver.findElements(by);
		return ele;
	}
	
	/**
	 * 放大页面窗口
	 * */
	public void maxWindow() {
		driver.manage().window().maximize();
		System.out.println("放大浏览器窗口");
	}
	
	/**
	 * 鼠标mouseover操作
	 * */
	public void mouseOverAction(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	/**
	 * 转换到模态框
	 * */
	public void switchToModal() {
		driver.switchTo().activeElement();
	
	}
	
	/**
	 * 获取当前窗口url
	 * */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	/**
	 * 获取cookie值
	 * */
	public void setCookie(Cookie cookie) {
		driver.manage().addCookie(cookie);
	}
	public void delAllCookies() {
		driver.manage().deleteAllCookies();
	}
	public Set<Cookie> getCookies(){
		Set<Cookie> cookies = driver.manage().getCookies();
		return cookies;
	}
	/**
	 * 切换窗口
	 * */
	public void switchWind(String wname) {
		driver.switchTo().window(wname);
	}
	/**
	 * 获取窗口句柄
	 * */
	public Set<String> getWindHand() {
		return driver.getWindowHandles();
	}
	/**
	 * 刷新
	 * */
	public void fresh() {
		driver.navigate().refresh();
	}
	
	public JavascriptExecutor JavascriptExecutor() {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		return js;
	}
	
}
