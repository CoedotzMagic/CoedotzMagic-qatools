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
import com.coedotzmagic.qatools.CoedotzMagic

import br.eti.kinoshita.testlinkjavaapi.constants.*
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI
import br.eti.kinoshita.testlinkjavaapi.model.*

def u = new CoedotzMagic()

String projectName = 'KatalonAutomation'
int projectID = 3189
String testPlanName = 'AutomationTestPlan'
int testPlanID = 3190
String testCaseID = 'KA-56'
String testCaseName = 'Integrated'
String tlKey = '1af4731c11a9f89513207b44a48258dd'
String tlUrl = 'https://testlink.itasoft.co.id/lib/api/xmlrpc/v1/xmlrpc.php'

TestLinkAPI testLink = new TestLinkAPI(new URL(tlUrl), tlKey)
//testLink.createTestPlan('SIT', projectName, 'dibuat via coedotzmagic', true, true)
//testLink.createTestProject('createViaCoedotzMagic Katalon', 'COEDOTZ', 'dibuat via plugin coedotzmagic di katalon', false, false, false, false, false, false)
//testLink.addPlatformToTestPlan(projectID, testPlanID, 'CoedotzMagic')
//testLink.removePlatformFromTestPlan(projectID, testPlanID, 'CoedotzMagic')

//testLink.createTestSuite(projectID, projectName, testCaseID, projectID, projectID, false, "CREATE_NEW_VERSION")
//testLink.createBuild(testPlanID, "BuildCoe", "From coedotzmagic plugin")

List<TestCaseStep> steps = new ArrayList<TestCaseStep>();
TestCaseStep step = new TestCaseStep();
steps.add(step)

testLink.createTestCase(testCaseName, testSuiteId, projectID, authorLogin, 
	summary, step, precondition, TestCaseStatus.DRAFT, TestImportance.HIGH, ExecutionType.AUTOMATED, order, internalId, true, ActionOnDuplicate.CREATE_NEW_VERSION)





//TestLinkAPIClient testLink = new TestLinkAPIClient(tlKey, tlUrl)
//testLink.createTestCase('coedotz', projectName, 'contoh', 'testcase-coedozmagic', 'testcase via coedotzmagic', 'nostep', 'noexpect', u.TESTLINK_TEST_SET_HIGH)
//testLink.addTestCaseToTestPlan(projectName, 'SIT', testCaseName, 0, u.TESTLINK_TEST_SET_HIGH)
//testLink.addTestCaseToTestPlan(projectName, 'SIT', testCaseName)
//testLink.createTestProject('CreateFromCoedotzMagic', 'COEDTZ', 'ini deskripsi')
//testLink.createBuild(projectName, testPlanName, 'CoedotzMagic', 'Create via CoedotzMagic Plugin Katalon')
//testLink.createTestSuite(projectName, 'tsbaru-via-automation', 'test-via-katalon')
//testLink.reportTestCaseResult(projectName, testPlanName, testCaseID, 'SprintBuild', 'oke coe via coedotzmagic-1', c.TESTLINK_TEST_PASSED)