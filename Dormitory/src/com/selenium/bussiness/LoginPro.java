package com.selenium.bussiness;


import com.selenium.base.DriverBase;
import com.selenium.page.LoginPage;


public class LoginPro {

	public DriverBase driver;
	public LoginPage lph;
	public LoginPro(DriverBase driver) {
		this.driver = driver;
		lph = new LoginPage(driver);
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

}
