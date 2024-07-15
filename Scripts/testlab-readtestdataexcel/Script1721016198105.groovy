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

import com.coedotzmagic.qatools.CoedotzMagic

def c = new CoedotzMagic()

String filePath = "testingCoedotzMagic.xlsx"
String sheetName = "Sheet1"

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('http://coedotzmagic.com')

List<Map<String, String>> testData = c.readTestDataFromExcel(filePath, sheetName)
for (Map<String, String> row : testData) {
		String username = row.get("Username");
		String password = row.get("Password");

		WebUI.setText(findTestObject('Object Repository/username'), username)
		WebUI.setText(findTestObject('Object Repository/password'), password)
		WebUI.click(findTestObject('Object Repository/login'))

		boolean checkLogin = WebUI.verifyElementNotPresent(findTestObject('Object Repository/admin'), 2, FailureHandling.OPTIONAL)
		if (!checkLogin) {
			WebUI.click(findTestObject('Object Repository/admin'))
			WebUI.click(findTestObject('Object Repository/span_Logout'))
			WebUI.click(findTestObject('Object Repository/login'))
		}
}