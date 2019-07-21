package com.selenium.util;

import org.openqa.selenium.By;

public class getByLocator {

	public static By getLocator(String key) {
		
		ProUtil pro = new ProUtil("F:\\hdd\\gitdir\\card\\card\\element.properties");
		String locator = pro.getPro(key);
		String locatorType = locator.split(">")[0];
		//System.out.println(locatorType);
		String locatorValue = locator.split(">")[1];
		//System.out.println(locatorValue);
		if(locatorType.equals("id") ) {
			return By.id(locatorValue);
			//return By.id(locatorValue);
		}else if(locatorType.equals("name")) {
			return By.name(locatorValue);
		}else if(locatorType.equals("className")) {
			return By.className(locatorValue);
		}else if(locatorType.equals("tagName")) {
			return By.tagName(locatorValue);
		}else if(locatorType.equals("linkText")) {
			return By.linkText(locatorValue);
		}else {
			return By.xpath(locatorValue);
		}
	}
	
}
