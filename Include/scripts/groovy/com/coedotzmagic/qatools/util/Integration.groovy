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

import testlink.api.java.client.TestLinkAPIClient
import testlink.api.java.client.TestLinkAPIException
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI

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
	 * @param testcaseNameID
	 * @param buildName
	 * @param execNotes
	 * @param result
	 * 
	 * @since 1.0
	 */
	static testlinkUpdateResults(String projectname, String testplanName, String testcaseNameID, String buildName, String execNotes, String results) throws TestLinkAPIException{
		TestLinkAPIClient testLink = new TestLinkAPIClient(TESTLINK_KEY, TESTLINK_URL)
		testLink.reportTestCaseResult(projectname, testplanName, testcaseNameID, buildName, execNotes, results)
	}
	
	/**
	 * <b>testlinkCreateTestPlan()</b>
	 * digunakan untuk melakukan pembuatan Test Plan di Testlink
	 *
	 * <br><br>
	 *
	 * @param planName
	 * @param projectName
	 * @param notes
	 * @param isActive
	 * @param isPublic
	 *
	 * @since 1.1
	 */
	static testlinkCreateTestPlan(String planName, String projectName, String notes, boolean isActive, boolean isPublic) {
		TestLinkAPI testLink = new TestLinkAPI(new URL(TESTLINK_URL), TESTLINK_KEY)
		testLink.createTestPlan(planName, projectName, notes, isActive, isPublic)
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

	/**
	 * <b>testlinkCreateBuild()</b>
	 * digunakan untuk melakukan pembuatan build di Testlink
	 *
	 * <br><br>
	 *
	 * @param projectName
	 * @param testplanName
	 * @param buildName
	 * @param buildNotes
	 *
	 * @since 1.1
	 */
	static testlinkCreateBuild(String projectName, String testplanName, String buildName, String buildNotes) throws TestLinkAPIException {
		TestLinkAPIClient testLink = new TestLinkAPIClient(TESTLINK_KEY, TESTLINK_URL)
		testLink.createBuild(projectName, testplanName, buildName, buildNotes)
	}

	/**
	 * <b>testlinkCreateTestProject()</b>
	 * digunakan untuk melakukan pembuatan test project di Testlink
	 *
	 * <br><br>
	 *
	 * @param projectName
	 * @param projectPrefix
	 * @param description
	 *
	 * @since 1.1
	 */
	static testlinkCreateTestProject(String projectName, String projectPrefix, String description) throws TestLinkAPIException {
		TestLinkAPIClient testLink = new TestLinkAPIClient(TESTLINK_KEY, TESTLINK_URL)
		testLink.createTestProject(projectName, projectPrefix, description)
	}

	/**
	 * <b>testlinkAddTestCaseToTestPlan()</b>
	 * digunakan untuk melakukan menambahkan testcase ke testplan di Testlink
	 *
	 * <br><br>
	 *
	 * @param projectName
	 * @param testplanName
	 * @param testcaseName
	 *
	 * @since 1.1
	 */
	static testlinkAddTestCaseToTestPlan(String projectName, String testplanName, String testcaseName) throws TestLinkAPIException {
		TestLinkAPIClient testLink = new TestLinkAPIClient(TESTLINK_KEY, TESTLINK_URL)
		testLink.addTestCaseToTestPlan(projectName, testplanName, testcaseName)
	}

	/**
	 * <b>testlinkAddTestCaseToTestPlan()</b>
	 * digunakan untuk melakukan menambahkan testcase ke testplan di Testlink
	 *
	 * <br><br>
	 *
	 * @param projectName
	 * @param testplanName
	 * @param testcaseName
	 * @param execOrder
	 * @param urgency
	 *
	 * @since 1.1
	 */
	static testlinkAddTestCaseToTestPlan(String projectName, String testplanName, String testcaseName, int execOrder, String urgency) throws TestLinkAPIException {
		TestLinkAPIClient testLink = new TestLinkAPIClient(TESTLINK_KEY, TESTLINK_URL)
		testLink.addTestCaseToTestPlan(projectName, testplanName, testcaseName, execOrder, urgency)
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