package id.coedotz.QAJWorkflow.coedotzmagic.util

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


/*
 * Created by : Arief Wardhana
 * itasoft gitlab / github : @poncoe
 * Date : 19-02-24
 */

public class Integration {

	/**
	 * <b>testlinkIntegration()</b>
	 * digunakan untuk melakukan integrasi ke Testlink
	 * dengan cara menjalankan dan menghasilkan report dam reportnya dilaporkan ke Testlink
	 *
	 * <br><br>
	 *
	 * @param masukanya status & notes
	 * - status : p (Passed), f (Failed) & a (Abort)
	 * - Notes : Catatan Pengujian
	 * @since 1.0
	 */
	void testlinkIntegration(String status, String notes) {
		// TestLink API configuration
		def testLinkUrl = "http://your-testlink-instance/lib/api/xmlrpc/v1/xmlrpc.php"
		def apiKey = "your-testlink-api-key"
		def projectName = "your-testlink-project-name"
		def testPlanName = "your-testlink-test-plan"
		def testCaseName = "your-testlink-test-case-name"

		def httpClient = HttpClients.createDefault()

		try {
			def url = new URL(testLinkUrl)
			def uri = new URI(url.protocol, url.userInfo, url.host, url.port, url.path, url.query, url.ref)
			def post = new HttpPost(uri)

			def xmlRequest = """
               <methodCall>
                   <methodName>tl.reportTCResult</methodName>
                   <params>
                       <param>
                           <value>
                               <string>${apiKey}</string>
                           </value>
                       </param>
                       <param>
                           <value>
                               <string>${testPlanName}</string>
                           </value>
                       </param>
                       <param>
                           <value>
                               <string>${projectName}</string>
                           </value>
                       </param>
                       <param>
                           <value>
                               <string>${testCaseName}</string>
                           </value>
                       </param>
                       <param>
                           <value>
                               <string>${status}</string>
                           </value>
                       </param>
                       <param>
                           <value>
                               <string>${notes}</string>
                           </value>
                       </param>
                   </params>
               </methodCall>
           """

			post.setEntity(new StringEntity(xmlRequest))
			post.setHeader("Content-type", "text/xml")

			def response = httpClient.execute(post)
			def responseText = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine()

			// Parse response if needed
			def jsonResponse = new JsonSlurper().parseText(responseText)
		} finally {
			httpClient.close()
		}
	}

	/* ------------------------------------------------------------------------- */
}