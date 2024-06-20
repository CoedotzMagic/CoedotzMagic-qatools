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

import testlink.api.java.client.TestLinkAPIClient
import testlink.api.java.client.TestLinkAPIException
import testlink.api.java.client.TestLinkAPIResults

import id.coedotz.QAJWorkflow.coedotzmagic.CoedotzMagic
import id.coedotz.QAJWorkflow.coedotzmagic.util.*

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus

String projectName = 'KatalonAutomation'
String testPlanName = 'AutomationTestPlan'
String testCaseName = 'KA-56'

def c = new CoedotzMagic()
def testLink = new TestLinkIntegration()

List<TestCaseStep> steps = [
        new TestCaseStep(null, null, "Step 1: Open Browser", c.TESTLINK_TEST_PASSED, null, null, null)
    ]
//testLink.reportTestCaseResult(projectName, testPlanName, testCaseName, 'SprintBuild', 'coedotz', ExecutionStatus.PASSED, "Opened browser", steps)


//TestLinkAPIClient testLink = new TestLinkAPIClient('1af4731c11a9f89513207b44a48258dd', 'https://testlink.itasoft.co.id/lib/api/xmlrpc/v1/xmlrpc.php')

//testLink.reportTestCaseResult(projectName, testPlanName, testCaseName, 'SprintBuild', 'oke coe via coedotzmagic-1', c.TESTLINK_TEST_PASSED)
