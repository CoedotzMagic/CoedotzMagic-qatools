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

public class KeysTyping {

	/**
	 * <b>combinationCTRLF()</b>
	 * digunakan untuk melakukan perintah CTRL+F (Find) pada Chrome
	 * biasanya digunakan untuk di elastic atau dilainnnya.
	 * 
	 * @since 1.0
	 */
	void combinationCTRLF () {
		// implementasi java robot
		Robot robot = new Robot()

		// Tekan CTRL
		robot.keyPress(KeyEvent.VK_CONTROL)

		// Tekan F
		robot.keyPress(KeyEvent.VK_F)

		// Lepaskan F
		robot.keyRelease(KeyEvent.VK_F)

		// Lepaskan CTRL
		robot.keyRelease(KeyEvent.VK_CONTROL)
	}

	/**
	 * <b>combinationCTRLV()</b>
	 * digunakan untuk melakukan perintah CTRL+V (Paste) pada chrome
	 * 
	 * @since 1.0
	 */
	void combinationCTRLV() {
		// implementasi java robot
		Robot robot = new Robot()

		// Tekan CTRL
		robot.keyPress(KeyEvent.VK_CONTROL)

		// Tekan V
		robot.keyPress(KeyEvent.VK_V)

		// Lepasin CTRL
		robot.keyRelease(KeyEvent.VK_V)

		// Lepasin V
		robot.keyRelease(KeyEvent.VK_CONTROL)
	}

	/**
	 * <b>combinationENTER()</b>
	 * digunakan untuk melakukan perintah ENTER pada chrome
	 * biasanya untuk handle external dialog atau apapun itu untuk melakukan ENTER/OK pada external dialog
	 * 
	 * @since 1.0
	 */
	void combinationENTER() {
		// implementasi java robot
		Robot robot = new Robot()

		// Tekan Enter untuk konfirmasi
		robot.keyPress(KeyEvent.VK_ENTER)
		robot.keyRelease(KeyEvent.VK_ENTER)
	}
}