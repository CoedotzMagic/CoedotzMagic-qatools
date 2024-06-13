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

public class WebServices {
	
	/**
	 * @deprecated <b>hitTicketTSAUsingAPI() UNAVAILABLE/TAKEOUT</b>
	 * digunakan untuk melakukan pembuatan tiket MyTSA
	 * Tiket awalnya akan dibuat seperti biasa, tetapi untuk mengintegrasikan MyTSA
	 * perlu adanya pemanggilan API "API/hitTicketTSA"
	 *
	 * <br><br>
	 *
	 * @param masukannya adalah idTicket yang ingin di integrasikan ke MyTSA
	 * @since 1.0
	 */
	void hitTicketTSAUsingAPI(String idTicket) {
		// panggil permintaan object api
		RequestObject requestObject = findTestObject('API/hitTicketTSA')

		// Parsing dan ambil data body
		def requestBody = requestObject.getHttpBody()

		// Parsing data body terbaru sebagai JSON
		def requestBodyJson = new JsonSlurper().parseText(requestBody)

		// Masukan nilai untuk diset pada data body
		requestBodyJson.ticket_id = idTicket

		// Konversi kembali modifikasi JSON menjadi String
		def modifiedBody = new JsonBuilder(requestBodyJson).toPrettyString()

		// Simpan Modifikasi data Body yang dibuat ke Permintaan Objek API
		requestObject.setHttpBody(modifiedBody)

		// Kirim perubahan permintaan Objek API
		def response = WS.sendRequest(requestObject)

		// Verifikasi bahwa response code harus 200
		assert response.getStatusCode() == 200, "Status Code harus 200, dan status codenya adalah : ${response.getStatusCode()}"
	}
	
}