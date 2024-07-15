package com.coedotzmagic.qatools.util

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Calendar as Calendar
import java.io.File as File
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.entity.StringEntity
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.JavascriptExecutor

import com.kms.katalon.core.setting.BundleSettingStore
import org.apache.commons.lang3.StringUtils

import testlink.api.java.client.TestLinkAPIClient
import testlink.api.java.client.TestLinkAPIException

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

	static BundleSettingStore bundleSetting
	static String TESTLINK_KEY
	static String TESTLINK_URL

	static {
		try {
			bundleSetting = new BundleSettingStore(RunConfiguration.getProjectDir(), 'com.coedotzmagic.qatools', true)
			TESTLINK_KEY = bundleSetting.getString('testlinkKey', '')
			if (StringUtils.isBlank(TESTLINK_KEY)) {
				KeywordUtil.logInfo("Testlink's Key is missing.")
				throw new IllegalStateException("Testlink's Key is missing.")
			}
			TESTLINK_URL = bundleSetting.getString('testlinkUrl', '')
			if (StringUtils.isBlank(TESTLINK_URL)) {
				KeywordUtil.logInfo("Testlink Url is missing.")
				throw new IllegalStateException("Testlink Url is missing.")
			}
		} catch (Exception e) {
			e.printStackTrace()
			throw e
		}
	}

	/**
	 * <b>testlinkUpdateResults()</b>
	 * digunakan untuk melakukan integrasi ke Testlink
	 * dengan cara menjalankan dan menghasilkan report dam reportnya dilaporkan ke Testlink
	 *
	 * <br><br>
	 *
	 * @param projectName
	 * @param testplanName
	 * @param testcaseName
	 * @param buildName
	 * @param execNotes
	 * @param result
	 * 
	 * @since 1.0
	 */
	static testlinkUpdateResults(String projectname, String testplanName, String testcaseName, String buildName, String execNotes, String results) throws TestLinkAPIException{
		TestLinkAPIClient testLink = new TestLinkAPIClient(TESTLINK_KEY, TESTLINK_URL)
		testLink.reportTestCaseResult(projectname, testplanName, testcaseName, buildName, execNotes, results)
	}
	
	/**
	 * <b>testlinkCreateTestSuite()</b>
	 * digunakan untuk melakukan pembuatan test suite di Testlink
	 *
	 * <br><br>
	 *
	 * @param projectName
	 * @param suiteName
	 * @param description
	 *
	 * @since 1.1
	 */
	static testlinkCreateTestSuite(String projectName, String suiteName, String description) throws TestLinkAPIException {
		TestLinkAPIClient testLink = new TestLinkAPIClient(TESTLINK_KEY, TESTLINK_URL)
		testLink.createTestSuite(projectName, suiteName, description)
	}

	/* ------------------------------------------------------------------------- */

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