package com.selenium.bussiness;

import org.apache.log4j.Logger;

import com.selenium.base.DriverBase;
import com.selenium.page.PaxyPage;

public class PaxyPro{

	private DriverBase driver;
	private PaxyPage pp;
	private CardPro cp;
	private intelligentPro intelpro;
	static Logger logger = Logger.getLogger(PaxyPro.class);
	private String macid = "posttest_zaji";
	private String defalturl = "http://card.weixiaotong.com.cn/";
	
	public PaxyPro(DriverBase driver) {
		this.driver = driver;
		this.cp =new CardPro(driver);
		this.pp = new PaxyPage(driver);
		this.intelpro = new intelligentPro(driver);
		
	}
	
	
	/**
	 * 搜索 打卡的闸机设备是否已经存在， 如果mac不在新增，新增mac
	 * @throws InterruptedException 
	 * */
	public void searchMac(String tabname,String macname,String macid) throws InterruptedException {
		cp.entryTabs(tabname);
		pp.macSearchInput().clear();
		pp.macSearchInput().sendKeys(macid);
		pp.macSearchBtn().click();
		logger.info("--------------------点击搜索"+macid);
		Thread.sleep(2000);
		try {
			pp.macBoxList().get(0);
			logger.info("-----------------"+macid+"已存在");
		}catch(Exception e) {
			logger.info("-----------------"+macid+"不存在需重新增加");
			intelpro.newMac1(macname, macid);
			logger.info("----------------新增设备成功！");
			
		}
		
	}
	
	/**
	 *发出打卡请求  /data/Acs?Card=0001234567&Serial=1AA048&Reader=1
	 * @throws InterruptedException 
	 * */
	public void postCard(String cardno,int reader) throws InterruptedException {
		String url = "data/Acs?Card="+cardno+"/&"+"Serial="+macid+"/&"+"Reader="+reader;
		url = defalturl +url;
		logger.info(url);
		driver.getUrl(url);
		Thread.sleep(2000);
	}
	
	
	
	
	
	
	
	
	
	
}
