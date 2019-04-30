package com.selenium.testCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class DataProviderExcel {

	public static Object[][] getData(String filepath) throws IOException {
		
			// 指定excel的路径
			File src=new File(filepath);
			// 加载文件
			FileInputStream fis = new FileInputStream(src);
			// 加载workbook
			@SuppressWarnings("resource")
			HSSFWorkbook wb=new HSSFWorkbook(fis);
			//加载sheet，这里我们只有一个sheet,默认是sheet1
			HSSFSheet sh= wb.getSheetAt(0);
			List<Object[]> list = new ArrayList<Object[]>();
			
			// i 控制行
			for(int i=1;i<sh.getPhysicalNumberOfRows();i++){
				// j是控制列
				Row row = sh.getRow(i);
				String[] data = new String[row.getLastCellNum()];
				for(int j=0;j<row.getLastCellNum();j++){
					Cell cell = row.getCell(j);
					cell.setCellType(CellType.STRING);
					data[j]=cell.getStringCellValue();
				}
				list.add(data);
			}
			
			Object[][] results = new Object[list.size()][];
			for(int i = 0;i<list.size();i++) {
				results[i]=list.get(i);
			}
			return results;
		}
	     
}
