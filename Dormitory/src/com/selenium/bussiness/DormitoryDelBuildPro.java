package com.selenium.bussiness;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.selenium.base.DriverBase;
import com.selenium.page.DormitoryPage;

public class DormitoryDelBuildPro {

	public DriverBase driver;
	public DormitoryPage dtp;
	public DormitoryAddBuildPro dabp;
	static Logger logger = Logger.getLogger(DormitoryDelBuildPro.class);
	
	public DormitoryDelBuildPro(DriverBase driver) {
		this.driver = driver;
		this.dtp = new DormitoryPage(driver);
		this.dabp = new DormitoryAddBuildPro(driver);
	}
	
	
	public void delBuild(String buildname,String delText) {
		logger.info("---------------------------开始找楼宇："+buildname);
		int levelno = dtp.findBuild(buildname);
		if(levelno == -1) {
			logger.error("-----------------------没有找到楼宇"+levelno+"，不需要删除");
		}else {
			logger.info("-----------------------楼宇找到在第"+levelno+"层，删除楼宇开始");
			dtp.clickBuildDel(buildname);
			logger.info("-----------------------点击楼宇删除");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(dtp.delBuildTextEle().isDisplayed()) {
				String actualText = dtp.getDelBuildText();
				if(actualText.equals(delText)) {
					logger.info("-----------------------删除楼宇不成功！提示信息："+actualText);
				}else {
					logger.error("----------------------删除提示信息不对"+actualText);
				}
			}else {
				logger.error("---------------------删除的提示信息未出现");
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtp.clickArrow(levelno);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("-----------------------点击下拉箭头，开始删除宿舍");
			for(int i=0;i<3;i++) {
				int delno = dtp.delBuildEle().size();
				delno = delno -2;
				dtp.clickDelBtn(delno);
				dtp.clickBuildCheckBtn(1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				levelno = dtp.findBuild(buildname);
				if(levelno == -1) {
					logger.info("-----------------------删除楼宇成功");
					break;
				}
				
			}
			levelno = dtp.findBuild(buildname);
			if(levelno == -1) {
				logger.info("-----------------------删除楼宇成功");
			}else {
				logger.info("-----------------------楼宇删除失败");
			}
		}

	}
	
	public void delRoom() {
		logger.info("----------------------开始删除楼层的宿舍");
		int roomsize = 0;
		for(int i =1;i<3;i++) {
			dtp.clickBuild(i);
			logger.info("----------------------点击第"+i+"楼层");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			roomsize = dtp.addedRoomEles().size();
			logger.info("-------------------该楼层下有"+roomsize+"层宿舍");
			
			if(roomsize !=0) {
				for(int j=0;j<roomsize;j++) {
					//鼠标移到到要删除的宿舍
					dtp.mouseoverAddedRoom(dtp.addedRoomEles().get(0));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logger.info("---------------------鼠标移动至第"+i+"楼层"+(j+1)+"宿舍");
					dtp.clickAddedRoomDel(0);
					logger.info("---------------------点击删除宿舍");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			roomsize = dtp.addedRoomEles().size();
			if(roomsize==0) {
				logger.info("-------------------该楼层下有宿舍"+roomsize+"层,宿舍删除成功");
			}else {
				logger.error("------------------------删除宿舍失败");
			}
			
		}
	}

	public String getAddedStuInf(int bedno) {
		String stuInfo = dtp.bedOfStuNameEles().get(bedno).getText() + dtp.bedOfStuClassEles().get(bedno).getText();
		return stuInfo;
	}
	
	public void delStu() {
		logger.info("-------------------开始删除宿舍中的学生");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int delsize = dtp.delBed().size();
		for(int i = 0;i< delsize;i++) {
			dtp.mouseOverBed(i);
			logger.info("-------------------鼠标移动至"+(i+1)+"床位");
			if(dtp.delBed().get(i).isDisplayed()) {
				dtp.clickDelBtnOfBed(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dtp.clickdelStuCheckBtn();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.info("--------------删除学生床位"+(i+1));
			}	
		}

	}
	
	public void assertDelStu() {
		List<String> stuinfoListActual = new ArrayList<String>();
		List<String> stuinfoListExpect = new ArrayList<String>();
		String stuinfo="";
		driver.fresh();
		logger.info("-------------------刷新页面");
		dabp.entryRoom(1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("-------------------开始验证宿舍学生是否删除成功");
		for(int i = 0;i<dtp.delBed().size();i++) {
			stuinfoListExpect.add(stuinfo);
			String stuinfoActual= this.getAddedStuInf(i);
			stuinfoListActual.add(stuinfoActual);
		}
		Assert.assertEquals(stuinfoListActual, stuinfoListExpect);
		logger.info("------------------学生删除成功！");
		
	}
	
	
	
	
	
}
