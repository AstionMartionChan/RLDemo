package com.rili.demo.excel;


import com.rili.demo.entity.CustomerEntity;
import com.rili.demo.utils.MySqlUtil;
import com.rili.demo.utils.POIUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

public class RLExcelReader {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
//        String filePath = "/Users/LeoChan/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/078875760424de797bc9c4af43faf5c4/Message/MessageTemp/9e20f478899dc29eb19741386f9343c8/File/白金客户信息管理V3.xlsm";
        String filePath = "C:\\Users\\chenfuyun\\Desktop\\rl.xlsx";

        Connection connection = MySqlUtil.getConnection();
        try (InputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(2);
            Iterator<Row> rows = sheet.rowIterator();

            Row row;
            Cell cell;
            Integer idx = 1;

            while (rows.hasNext()){
                row = rows.next();
                int rowNum = row.getRowNum();
                if (rowNum == 0){
                    continue;
                }

                // 获取单元格
                Iterator<Cell> cells = row.cellIterator();

                CustomerEntity entity = new CustomerEntity();
                while (cells.hasNext()) {
                    cell = cells.next();
                    int columnIndex = cell.getColumnIndex();

                    String value = POIUtil.getCellValue(cell);
                    if (columnIndex == 0){
                        entity.setCustomerOrgName(value);
                    } else if (columnIndex == 1){
                        if (value.equals("1.矿山客户")){
                            entity.setCustomerType(1);
                        } else if (value.equals("2.营业客户")){
                            entity.setCustomerType(2);
                        }
                    } else if (columnIndex == 2){
                        entity.setCustomerId(value);
                    } else if (columnIndex == 3){
                        entity.setCustomerName(value);
                    } else if (columnIndex == 4){
                        entity.setWholeMachineId(value);
                    } else if (columnIndex == 5){
                        entity.setJyMachineName(value);
                    } else if (columnIndex == 6){
                        entity.setJyCustomerCode(value);
                    } else if (columnIndex == 7){
                        entity.setJyMachineCode(value);
                    } else if (columnIndex == 8){
                        entity.setJyCustomerName(value);
                    } else if (columnIndex == 9){
                        if (value.equals("一级白金")){
                            entity.setServiceLevel(1);
                        } else if (value.equals("二级白金")){
                            entity.setServiceLevel(2);
                        } else if (value.equals("三级白金")){
                            entity.setServiceLevel(3);
                        } else if (value.equals("四级白金")){
                            entity.setServiceLevel(4);
                        } else if (value.equals("#N/A")){
                            entity.setServiceLevel(0);
                        } else {
                            entity.setServiceLevel(0);
                        }
                    } else if (columnIndex == 10){
                        if (value.equals("#N/A") || value.equals("非法字符")){
                            entity.setMineLevel(0);
                        } else {
                            entity.setMineLevel(Integer.valueOf(value));
                        }
                    } else if (columnIndex == 11){
                        if (value.equals("白金客户")){
                            entity.setBusinessLevel(1);
                        } else {
                            entity.setBusinessLevel(0);
                        }
                    }

                }
                MySqlUtil.insertRLCustomer(entity);
                System.out.println("写入第" + idx + "行数据: " + entity.toString());
                idx++;
            }

        }

        MySqlUtil.close();

    }

}
