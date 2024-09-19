package com.coedotzmagic.qatools.util

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.setting.BundleSettingStore
import org.apache.commons.lang3.StringUtils
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map

/*
 * Created by : Arief Wardhana
 * itasoft gitlab / github : @poncoe
 * Date : 19-02-24
 */

public class Integration {

	/**
	 * <b>readTestDataFromExcel</b>
	 * digunakan untuk membaca data dan isi dari file excel
	 * 
	 * @param filePath
	 * @param sheetName
	 * 
	 * @since 1.0
	 */
	public static List<Map<String, String>> readTestDataFromExcel(String filePath, String sheetName) {
		try {
			String pathFolderTestdata = RunConfiguration.getProjectDir() + File.separator + "TestData"
			new File(pathFolderTestdata).mkdirs()
			String getFilePath = pathFolderTestdata + File.separator + filePath

			FileInputStream fis = new FileInputStream(new File(getFilePath))
			Workbook workbook = new XSSFWorkbook(fis)
			Sheet sheet = workbook.getSheet(sheetName)

			int rowCount = sheet.getLastRowNum()
			int colCount = sheet.getRow(0).getLastCellNum()

			List<Map<String, String>> data = new ArrayList<>()

			// Membaca header dari row pertama
			Row headerRow = sheet.getRow(0)
			String[] headers = new String[colCount]
			for (int j = 0; j < colCount; j++) {
				headers[j] = headerRow.getCell(j).toString().trim()
			}

			// Membaca data dari baris berikutnya
			for (int i = 1; i <= rowCount; i++) {
				Row dataRow = sheet.getRow(i)
				Map<String, String> rowMap = new HashMap<>()

				for (int j = 0; j < colCount; j++) {
					String key = headers[j]
					String value = dataRow.getCell(j).toString().trim()
					rowMap.put(key, value)
				}

				data.add(rowMap)
			}

			workbook.close()
			fis.close()

			return data;
		} catch (Exception e) {
			e.printStackTrace()
			return null
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
	}

	/* ------------------------------------------------------------------------- */
}