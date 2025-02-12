package com.coedotzmagic.qatools.integration;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static com.coedotzmagic.qatools.integration.ScreenRecordingHelper.USER_DIR;

/*
 * write by Coedotz
 * 19-02-2024
 */

public class ReadDataExcel {

    /**
     * <b>readTestDataFromExcel</b>
     * digunakan untuk membaca data dan isi dari file excel
     *
     * @param filePath
     * @param sheetName
     *
     * @since 1.1
     */
    public static List<Map<String, String>> readTestDataFromExcel(String filePath, String sheetName) {
        try {
            String pathFolderTestdata = System.getProperty(USER_DIR) + File.separator + "TestData";
            new File(pathFolderTestdata).mkdirs();
            String getFilePath = pathFolderTestdata + File.separator + filePath;

            FileInputStream fis = new FileInputStream(getFilePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();

            List<Map<String, String>> data = new ArrayList<>();

            // Membaca header dari row pertama
            Row headerRow = sheet.getRow(0);
            String[] headers = new String[colCount];
            for (int j = 0; j < colCount; j++) {
                headers[j] = headerRow.getCell(j).toString().trim();
            }

            // Membaca data dari baris berikutnya
            for (int i = 1; i <= rowCount; i++) {
                Row dataRow = sheet.getRow(i);
                Map<String, String> rowMap = new HashMap<>();

                for (int j = 0; j < colCount; j++) {
                    String key = headers[j];
                    String value = dataRow.getCell(j).toString().trim();
                    rowMap.put(key, value);
                }

                data.add(rowMap);
            }

            workbook.close();
            fis.close();

            return data;
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_READ_EXCEL + e.getMessage());
            return null;
        }
    }

}
