package com.selenium.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;

public class ProUtil {
	private Properties prop;
	private String filepath;
	/**
	 * 构造函数
	 * @throws Exception 
	 * */
	public ProUtil(String filepath){
		this.filepath = filepath;
		this.prop = readProperties();
	}
	/**
	 * 读取配置文件
	 * @throws Exception 
	 * */
	private Properties readProperties(){
		Properties properties = new Properties();
		try {
			
		FileInputStream file = new FileInputStream(filepath);
		InputStream in = new BufferedInputStream(file);	
		properties.load(in);
		in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	/**
	 * 获取配置文件value
	 * */
	public String getPro(String key) {
		if(prop.containsKey(key)) {
			String keyValue = prop.getProperty(key);
			return keyValue;
		}else {
			System.out.println("关键字不存在");
			return "";
		}
	}
	
	/**
	 * cookies写入配置文件
	 * */
	public void writePro(String key,String value) {
		try {
			OutputStream out = new FileOutputStream(filepath);
			prop.setProperty(key, value);
			try {
				prop.store(out, "登录获取到的cookie");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * localstorage basedata 写入配置文件
	 * */
	public void writeObject(Object basedata) {
		try {
			
			HashMap<Object,Object> map = new HashMap<Object,Object>();
			map.put("name", basedata);
			FileOutputStream outStream = new FileOutputStream("E:\\1.text");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
			objectOutputStream.writeObject(map);
			outStream.close();
			System.out.println("successful");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * localstorage basedata 读取内容
	 * */
	@SuppressWarnings("unchecked")
	public void readObject(){
		FileInputStream freader;
		try {
			freader = new FileInputStream("E:\\1.text");
			@SuppressWarnings("resource")
			ObjectInputStream objectInputStream = new ObjectInputStream(freader);
			HashMap<Object,Object> map = new HashMap<Object,Object>();
			 map = (HashMap<Object, Object>) objectInputStream.readObject();
			
			 System.out.println("The name is " + map.get("name"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	
	
}
