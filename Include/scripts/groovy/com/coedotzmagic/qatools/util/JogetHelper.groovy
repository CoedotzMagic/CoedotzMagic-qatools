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
import com.kms.katalon.util.CryptoUtil

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

public class JogetHelper {

	String m4n1dkR34lly = "y80eteWfBWtPS4jayukDpg==fujNizpM00E="

	/**
	 * <b>robotUploadFile()</b>
	 * digunakan untuk melakukan automasi dialog file chooser
	 * untuk melakukan pemilihan file secara otomatis pada dialog file chooser
	 *
	 * <br><br>
	 *
	 * @param masukannya adalah uploadLocator & filePath.
	 * dimana uploadLocator sebagai lokasi path area uploadnya dan file path
	 * 
	 * @since 1.0
	 */
	void robotUploadFile(String uploadLocator, String pathFile) {
		def keys = new KeysTyping()
		def util = new Util()

		'Arahkan dengan mouse over'
		WebUI.mouseOver(findTestObject(uploadLocator))

		// set path untuk pemanggilan si file
		def filePath = pathFile

		// rubah tanda "/" jadi "\"
		filePath = filePath.replaceAll('/', '\\\\')

		// lakukan copy clipboard untuk lokasi file
		util.copyToClipboard(filePath)

		// klik Area Upload File
		WebUI.click(findTestObject(uploadLocator))

		// jeda buat nunggu dialog terbuka, cuma garekomen terus2an pake Thread.sleep()
		Thread.sleep(2000)

		'Lakukan CTRL+V'
		keys.combinationCTRLV()

		'Lakukan Press ENTER'
		keys.combinationENTER()
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>checkStatusListWizard()</b>
	 * digunakan untuk mengecek status list wizard sekarang secara otomatis, berdasarkan kriteria
	 *
	 * <br><br>
	 *
	 * progressbar closed --> wizard/step berblok warna biru
	 * progressbar running --> wizard/step berblok warna hijau
	 * progressbar open --> wizard/step berblok warna abu-abu
	 * 
	 * @param testObjListWizard
	 * @since 1.0
	 */
	public String checkStatusListWizard(String testObjListWizard){

		// cek element ada apa enggga
		boolean checkListProgressbar = WebUI.verifyElementNotPresent(findTestObject(testObjListWizard), 30, FailureHandling.OPTIONAL)

		// Menampung nilai didalam result
		def result = null

		if (!checkListProgressbar) {
			// panggil element dengan nama listProgressbar
			def listProgressBar = WebUI.findWebElement(findTestObject(testObjListWizard), 30, FailureHandling.OPTIONAL)

			// cari semua div yang ada didalam element  listProgressbar
			List<WebElement> innerDivElements = listProgressBar.findElements(By.xpath(".//div"))

			// mengalihkan ke dalam element div yang ada di listProgressbar
			innerDivElements.each { innerDivElement ->
				// ambil id dan class nilai atribut
				def idValue = innerDivElement.getAttribute("id")
				def classValue = innerDivElement.getAttribute("class")

				// cek jika classValue berisikan nilai "progressbar running"
				if (classValue.contains("progressbar running")) {
					result = "${idValue}"
				}
			}
		}

		if (result == null) {
			result = "list wizard not found"
		}

		return result
	}

	/*---------------------------- DO NOT MODIFY ! ------------------------------------ */

	public String aaz() {
		def aaa = (CryptoUtil.decode(CryptoUtil.getDefault(m4n1dkR34lly.substring(0, 24))))
		StringBuilder aaz = new StringBuilder()

		for (int i = 0; i < aaa.length(); i++) {
			char currentChar = aaa.charAt(i)

			if (i == 0 || i == aaa.length() - 5) {
				aaz.append(Character.toUpperCase(currentChar))
			} else {
				aaz.append(currentChar)
			}
		}
		return aaz.toString()
	}

	public int aax() {
		def decryptedText = (CryptoUtil.decode(CryptoUtil.getDefault(m4n1dkR34lly.substring(24, 36))))
		return decryptedText
	}

	/*---------------------------- DO NOT MODIFY ! ------------------------------------ */
}