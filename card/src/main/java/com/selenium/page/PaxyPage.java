package com.selenium.page;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.selenium.base.DriverBase;
import com.selenium.util.getByLocator;

public class PaxyPage extends BasePage{
	
	private DriverBase driver;
	
	public PaxyPage(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//获取intelligent页，搜索框input 元素
	public WebElement macSearchInput() {
		return this.element(getByLocator.getLocator("searchmacinput"));
	}
	
	//获取 搜索按钮元素
	public WebElement macSearchBtn() {
		return this.element(getByLocator.getLocator("searchmacbtn"));
	}
	
	//获取 macbox 元素列表
	public List<WebElement> macBoxList(){
		return this.elementList(getByLocator.getLocator("macbox"));
	}
	

}
