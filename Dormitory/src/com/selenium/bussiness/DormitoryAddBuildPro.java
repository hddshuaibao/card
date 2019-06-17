package com.selenium.bussiness;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.selenium.base.DriverBase;
import com.selenium.page.DormitoryPage;

public class DormitoryAddBuildPro {
	public DriverBase driver;
	public DormitoryPage dtp;
	static Logger logger = Logger.getLogger(DormitoryAddBuildPro.class);
	
	public DormitoryAddBuildPro(DriverBase driver) {
		this.driver = driver;
		this.dtp = new DormitoryPage(driver);
	}
	
	/**
	 * 判断是否有所加楼宇
	 * */
	public int ifhasBuild(String buildname) {
		int levelno = dtp.findBuild(buildname);
		return levelno;
	}

	
	
	
	/**
	 * 新增楼宇，如已有该楼宇则删除
	 * */
	public void addBuildPro(String buildname,String level) {
		/**int levelno = this.ifhasBuild(buildname);
		if(levelno == -1) {
			
		}else {
			try {
				dtp.clickBuildDel(buildname, levelno);
				logger.info("删除"+buildname+"成功！");
			} catch(Exception e) {
				logger.info("删除失败");
			}
		}*/
		logger.info("新增楼宇:"+buildname+"开始");
		dtp.clickAddBuilding();
		logger.info("点击新增楼宇按钮成功");
		if(dtp.assertAddEleIs()) {
			dtp.sendkeysBuildName(buildname);
			logger.info("输入"+buildname+"名称成功");
			dtp.sendkeysBuildLevel(level);
			logger.info("输入楼层数成功，楼层数为："+level+"层");
			dtp.clickaddConfirmBtn();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertNotEquals(this.ifhasBuild(buildname), -1);
			logger.info("新增楼宇成功：楼宇名称为  "+buildname+"楼层数为："+level);
		}else {
			logger.info("新增框未显示");
		}
	}
	
	/**
	 * 判断宿舍是否新增成功
	 * */
	public boolean ifRoomAdded(int roomnumber) {
		
		boolean ifroomadded = false;
		logger.info("------------------开始验证宿舍是否新增成功");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int roomno = dtp.addedRoomEles().size();
			if(roomno == 2) {
				ifroomadded = true;
			}
		return ifroomadded;
	}
	
	
	/**
	 * 新增宿舍
	 * */
	public void addRoom(String roomno,String roomscale,int roomnumber) {
		logger.info("------------------开始新增宿舍");
		for(int i = 1;i<3;i++) {
			dtp.clickBuild(i);
			logger.info("------------------点击选择"+i+"层楼");
			dtp.clickAddRoomBtn();
			logger.info("------------------点击新增宿舍");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtp.sendkeysRoomNo(roomno);
			logger.info("------------------输入宿舍数量："+roomno+"个");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtp.sendkeysRoomScale(roomscale);
			logger.info("------------------输入宿舍规模："+roomscale+"人");
			dtp.clickAddRoomCheckBtn();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertEquals(this.ifRoomAdded(roomnumber), true);
			logger.info("------------------宿舍新增成功！");
			
			
		}
		
	}

	
	public void entryRoom(int roomno) {
			dtp.clickStuArrow();
			logger.info("-------------------点击下拉箭头");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dtp.clickBuild(4);
			logger.info("------------------点击选择"+1+"层楼");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtp.clickAddedRoom(roomno-1);
			logger.info("------------------点击第"+1+0+roomno+"宿舍");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	public String getAddedStuInf(int bedno) {
		String stuInfo = dtp.bedOfStuNameEles().get(bedno-1).getText() + dtp.bedOfStuClassEles().get(bedno-1).getText();
		return stuInfo;
	}
	
	public void addStu(int bedno,String stuname) {
		dtp.clickAddStuBtn(bedno-1);
		logger.info("----------------点击床位"+bedno+"开始添加学生");
		if(bedno==1) {
			dtp.clickBjInput();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtp.clickSelectedBj();
			logger.info("-----------------点击选择草莓三班为班级");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dtp.clickStuInput();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtp.selectStu(stuname);
		logger.info("---------------点击选择学生"+stuname);
		dtp.clickAddStuCheck();
		logger.info("---------------点击添加学生确定");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualInfo = this.getAddedStuInf(bedno);
		Assert.assertEquals(actualInfo, "学生姓名:"+stuname+"所属班级:草莓三班");
		logger.info("-------------------学生添加成功！"+stuname);
		
		
	}
	
	
	
}
