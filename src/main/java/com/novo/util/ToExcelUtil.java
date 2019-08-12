package com.novo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.novo.entity.BuyPlanEntity;
import com.novo.entity.BuySchemeEntity;

public class ToExcelUtil {
	public static void toEx1(List<BuyPlanEntity>list,String name) throws Exception
    {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商品表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("通用名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("规格");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("生产厂家");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("批准文号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("条形码");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("剂量");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("单位");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("ERP编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("预估价格");
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);
        cell.setCellValue("采购数量");
        cell.setCellStyle(style);
        cell = row.createCell((short) 10);
        cell.setCellValue("总价格");
        cell.setCellStyle(style);


        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，


        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            BuyPlanEntity pullVo = (BuyPlanEntity) list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(pullVo.getComName());
            row.createCell((short) 1).setCellValue(pullVo.getSpec());
            row.createCell((short) 2).setCellValue(pullVo.getProduceFact());
            row.createCell((short) 3).setCellValue(pullVo.getLicenseNo());
            row.createCell((short) 4).setCellValue(pullVo.getBarCode());
            row.createCell((short) 5).setCellValue(pullVo.getDrug());
            row.createCell((short) 6).setCellValue(pullVo.getUnit());
            row.createCell((short) 7).setCellValue(pullVo.getErpNo());
            row.createCell((short) 8).setCellValue(pullVo.getEvaluate());
            row.createCell((short) 9).setCellValue(pullVo.getBuyNum());
            row.createCell((short) 10).setCellValue(pullVo.getTotalPrivce());

        }
        //创建文件
        Boolean bool = false;
        File file = new File("D:\\download\\buyPlan\\"+name);
        File parent = file.getParentFile(); // 获取父文件
        try {
        	if( !parent.exists() ) {
        		parent.mkdirs(); //创建所有父文件夹 
        		//如果文件不存在，则创建新的文件
	            if(!file.exists()){
	                file.createNewFile();
	                bool = true;
	            }
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 第六步，将文件存到指定位置
        try
        {
            FileOutputStream fout = new FileOutputStream("D:/download/buyPlan/"+name);
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
	
	
	public static void toEx2(List<BuySchemeEntity>list,String name) throws Exception
    {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商品表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("供应商");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("通用名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("生产厂家");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("批准文号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("规格");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("ERP编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("计划采购数量");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("实际采购数量");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("未采购数量");
        cell.setCellStyle(style);
        


        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，


        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            BuySchemeEntity pullVo = (BuySchemeEntity) list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(pullVo.getSupp().getName());
            row.createCell((short) 1).setCellValue(pullVo.getComName());
            row.createCell((short) 2).setCellValue(pullVo.getProduceFact());
            row.createCell((short) 3).setCellValue(pullVo.getLicenseNo());
            row.createCell((short) 4).setCellValue(pullVo.getSpec());
            row.createCell((short) 5).setCellValue(pullVo.getErpNo());
            row.createCell((short) 6).setCellValue(pullVo.getPlanBuyNum());
            row.createCell((short) 7).setCellValue(pullVo.getReBuyNum());
            row.createCell((short) 8).setCellValue(pullVo.getPlanBuyNum()-pullVo.getReBuyNum());
            

        }
        //创建文件
        Boolean bool = false;
        File file = new File("D:\\download\\buyscheme\\"+name);
        File parent = file.getParentFile(); // 获取父文件
        try {
        	if( !parent.exists() ) {
        		parent.mkdirs(); //创建所有父文件夹 
        		//如果文件不存在，则创建新的文件
	            if(!file.exists()){
	                file.createNewFile();
	                bool = true;
	            }
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 第六步，将文件存到指定位置
        try
        {
            FileOutputStream fout = new FileOutputStream("D:/download/buyscheme/"+name);
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

