package com.selenium.page;

public class Sqlconnect {
	

	private static String driver;   //驱动程序名
	private static String url;   //URL指向要访问的数据库名
	private static String user;
	private static String password;
	//static String sql;
	
    public static String getDriver() {
    	return  driver;
    }
    public static void setDriver(String dr) {
    	driver=dr;
    }
    
    public static String getUrl() {
    	return url;
    }
    
    public static void setUrl(String u) {
    	url=u;
    }
    
    public static String getUser() {
    	return user;
    }
    
    public static void setUser(String usr) {
    	user=usr;
    }
    
    public static String getPassword() {
    	return password;
    }
    
    public static void setPassword(String pss) {
    	password=pss;
    }
    
}
