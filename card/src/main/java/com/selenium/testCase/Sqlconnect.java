package com.selenium.testCase;

import java.sql.DriverManager;

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
    
    public java.sql.Connection sqlCon(String database,String user,String pwd) throws Exception {
		
		java.sql.Connection con;
		Sqlconnect.setDriver("com.mysql.jdbc.Driver");
		Sqlconnect.setUrl("jdbc:mysql://mysql.spacetech.com.cn:3309/"+database);
		String url=Sqlconnect.getUrl();
		String driver=Sqlconnect.getDriver();
		
		//连接数据库
		Class.forName(driver);
    	con=DriverManager.getConnection(url,user,pwd);
    	if(!con.isClosed()) {
    		System.out.println("数据库连接成功！");
    	}
		return con;
	}
    
    
    
    
    
}
