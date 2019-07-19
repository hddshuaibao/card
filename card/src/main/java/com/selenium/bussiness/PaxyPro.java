package com.selenium.bussiness;

import org.apache.log4j.Logger;

import com.selenium.base.DriverBase;
import com.selenium.page.CardPage;
import com.selenium.page.PaxyPage;

public class PaxyPro{

	private DriverBase driver;
	private PaxyPage pp;
	private CardPage cpage;
	private CardPro cp;
	private intelligentPro intelpro;
	static Logger logger = Logger.getLogger(PaxyPro.class);
	private String macid = "testpaxy";
	private String defalturl = "http://card.weixiaotong.com.cn/";
	
	public PaxyPro(DriverBase driver) {
		this.driver = driver;
		this.cp =new CardPro(driver);
		this.pp = new PaxyPage(driver);
		this.cpage = new CardPage(driver);
		this.intelpro = new intelligentPro(driver);
		
	}
	
	/**
	 * 进入智能硬件tab页，
	 * @throws InterruptedException 
	 * */
	public void entryIntelligent(String tabname) throws InterruptedException {
		cp.entryTabs(tabname);
	}
	
	/**
	 * 搜索 打卡的闸机设备是否已经存在，存在则返回true，不存在则false
	 * @throws InterruptedException 
	 * */
	public boolean searchMac() throws InterruptedException {
		boolean ismacset = true;
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
			ismacset = false;
			
		}
		return ismacset;
	}
	
	/**
	 * 如果mac不在新增，新增mac,发出打卡请求  /data/Acs?Card=0001234567&Serial=1AA048&Reader=1
	 * @throws InterruptedException 
	 * */
	public void postCard(String macname,String macid,String cardno,int reader) throws InterruptedException {
		String url = "data/Acs?Card="+cardno+"/&"+"Serial="+macid+"/&"+"Reader="+reader;
		if(this.searchMac()) {
			
		}else {
			intelpro.newMac1(macname, macid);
			logger.info("----------------新增设备成功！");
		}
		url = defalturl +url;
		logger.info(url);
		driver.getUrl(url);
	}
	
	
	
	
	
	
	
	
	
	
}
