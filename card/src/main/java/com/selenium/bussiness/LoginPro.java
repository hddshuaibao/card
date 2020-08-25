package com.selenium.bussiness;


import org.apache.log4j.Logger;

import com.selenium.base.DriverBase;
import com.selenium.page.BasePage;
import com.selenium.page.LoginPage;



public class LoginPro {

	public DriverBase driver;
	public LoginPage lph;
	private BasePage base;
	static Logger logger = Logger.getLogger(LoginPro.class);
	public LoginPro(DriverBase driver) {
		this.driver = driver;
		lph = new LoginPage(driver);
		base = new BasePage(driver);
	}
	
	/**
	 * 登录流程，输入用户名，输入密码，点击登录
	 * */
	public void login(String username,String password) {
		if(lph.assertLoginPageIs()) {
			lph.sendkeysUser(username);
			lph.sendkeysPassword(password);
			lph.clickLoginBtn();
		}else {
			System.out.println("页面元素不存在");
		}
	}
	/**
	 * 验证用户信息
	 * */
	public void assertEquals(String expectedtext) {
		lph.assertTextIs(lph.getElementText(lph.getUserInfoEle()), expectedtext);
	}

	/**
	 * 登录流程，输入用户名，输入密码，点击登录
	 * */
	public void loginNewVersion(String username,String password,String expected) {
		lph.inputItems(0).sendKeys(username);
		lph.inputItems(1).sendKeys(password);
		lph.loginButton().click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		base.assertTextIs(lph.loginError().getText(), expected);
		
	}
	
	
}
