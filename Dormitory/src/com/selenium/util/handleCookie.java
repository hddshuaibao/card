package com.selenium.util;

import java.util.Set;

import org.openqa.selenium.Cookie;
import com.selenium.base.DriverBase;

public class handleCookie {

	public DriverBase driver;
	public ProUtil pro;
	public handleCookie(DriverBase driver) {
		this.driver = driver;
		this.pro = new ProUtil("/Users/space/eclipse-workspace/card/cookie.properties");
	}
	
	//public void setCookie190(String cookieName) {
		//String value = pro.getPro(cookieName);
		//Cookie cookie = new Cookie(cookieName, value);
		//driver.setCookie(cookie);
	//}
	
	public void setCookie(String cookieName) {
		String value = pro.getPro(cookieName);
		Cookie cookie = new Cookie(cookieName, value);
		driver.setCookie(cookie);
	}
	
	public void setBase(String baseName) {
		String base = pro.getPro(baseName);
		driver.JavascriptExecutor().executeScript(String.format("localStorage.setItem('BASE_DATA','%s');",base));
	}
	
	public void writeBase(String base) {
		pro.writePro("BASE_DATA", base);
		System.out.println("BASE_DATA写入成功");
	}
	
	public void writeCookie() {
		Set<Cookie> cookies = driver.getCookies();
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("JSSSID_COOKIE")) {
				pro.writePro(cookie.getName(), cookie.getValue());
				System.out.println("存入cookie 成功");
			}else if(cookie.getName().equals("JSESSIONID_COOKIE")) {
				pro.writePro(cookie.getName(), cookie.getValue());
			}
		}
	}
	
	public void writeBaseData(Object Basedata) {
		//Object Basedata = this.getLocalStorage();
		if (Basedata !="") {
			//pro.writeObjPro("BASE_DATA", Basedata);
		}else {
			System.out.println("没有要写入的BASE_DATA");
		}
		
	}
	
}
