package com.selenium.page;

import org.openqa.selenium.WebElement;

import com.selenium.base.DriverBase;
import com.selenium.util.getByLocator;

public class LoginPage extends BasePage{


	//public LoginPage lp;
	public LoginPage(DriverBase driver) {
		super(driver);
		//this.lp = new LoginPage(driver);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 获取用户名的元素
	 * */
	public WebElement getuserEle() {
		return element(getByLocator.getLocator("username"));
	}
	/**
	 * 获取密码元素
	 * */
	public WebElement getpasswordEle() {
		return element(getByLocator.getLocator("password"));
	}
	
	/**
	 * 获取确定按钮元素
	 * */
	public WebElement getloginBtnEle() {
		return element(getByLocator.getLocator("loginBtn"));
	}
	
	/**
	 * 获取登录后的用户信息
	 * */
	public WebElement getUserInfoEle() {
		return element(getByLocator.getLocator("userInfo"));
	}
	
	
	/**
	 * 输入用户名
	 * */
	public void sendkeysUser(String username) {
		sendKeys(getuserEle(), username);
		
	}
	
	/**
	 * 输入密码
	 * */
	public void sendkeysPassword(String password) {
		sendKeys(getpasswordEle(), password);
		
	}
	
	/**
	 * 点击登录按钮
	 * */
	public void clickLoginBtn() {
		click(getloginBtnEle());
		
	}
	
	/**
	 * 验证是否是登录页面
	 * */
	public boolean assertLoginPageIs() {
		return assertElementIs(getuserEle());
	}
	
	/**
	 * 获取登录用户信息
	 * */
	public String getUserInfo() {
		return getElementText(getUserInfoEle());
	}
	
	
}
