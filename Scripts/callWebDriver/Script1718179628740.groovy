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

import com.coedotzmagic.qatools.util.*

public static void dataTestingViaExcel(List<Map<String, String>> testData) {
	for (Map<String, String> row : testData) {
		String testCaseName = row.get("TestCaseName");
		String expectedResult = row.get("ExpectedResult");
		String username = row.get("Username");
		String password = row.get("Password");

		WebUI.openBrowser('')
		WebUI.navigateToUrl('https://coedotzmagic.com')
		WebUI.setText(findTestObject('username_field'), username)
		WebUI.setText(findTestObject('password_field'), password)
		WebUI.click(findTestObject('login_button'))

		assert WebUI.getText(findTestObject('result_element')) == expectedResult : "Test Case Failed: $testCaseName"

		WebUI.closeBrowser()
	}
}

String filePath = "excel_file.xlsx"
String sheetName = "Sheet1"

List<Map<String, String>> testData = Integration.readTestDataFromExcel(filePath, sheetName)
dataTestingViaExcel(testData)