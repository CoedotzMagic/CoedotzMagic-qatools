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


/*
 * Created by : Arief Wardhana
 * itasoft gitlab / github : @poncoe
 * Date : 19-02-24
 */

public class WebServices {

	/**
	 * <b>hitApi</b>
	 * digunakan untuk melakukan pemanggilan API tanpa data
	 *
	 * <br><br>
	 *
	 * @param pathApi
	 * @since 1.0
	 */
	void hitApi(String pathApi) {
		// panggil permintaan object api
		RequestObject requestObject = findTestObject(pathApi)

		// Kirim perubahan permintaan Objek API
		def response = WS.sendRequest(requestObject)

		// Verifikasi bahwa response code harus 200
		assert response.getStatusCode() == 200, "Status Code harus 200, dan status codenya adalah : ${response.getStatusCode()}"

		// Mengambil data response dari API
		def responseText = response.getResponseText()

		// Parsing data response terbaru sebagai JSON
		def jsonResponse = new JsonSlurper().parseText(responseText)

		// mengambil dan menyimpan nilai status code
		String statusCode = jsonResponse.code

		// cek kondisi API
		readStatusCode(statusCode)
	}

	/**
	 * <b>hitApiWithData</b>
	 * digunakan untuk melakukan pemanggilan API dengan data
	 *
	 * <br><br>
	 *
	 * @param pathApi
	 * @since 1.0
	 */
	void hitApiWithData(String pathApi, Map<String, Object> dataMap) {
		// panggil permintaan object api
		RequestObject requestObject = findTestObject(pathApi)

		// Parsing dan ambil data body
		def requestBody = requestObject.getHttpBody()

		// Parsing data body terbaru sebagai JSON
		def requestBodyJson = new JsonSlurper().parseText(requestBody)

		// Masukan nilai untuk diset pada data body
		dataMap.each { key, value ->
			requestBodyJson[key] = value
		}

		// Konversi kembali modifikasi JSON menjadi String
		def modifiedBody = new JsonBuilder(requestBodyJson).toPrettyString()

		// Simpan Modifikasi data Body yang dibuat ke Permintaan Objek API
		requestObject.setHttpBody(modifiedBody)

		// Kirim perubahan permintaan Objek API
		def response = WS.sendRequest(requestObject)

		// Verifikasi bahwa response code harus 200
		assert response.getStatusCode() == 200, "Status Code harus 200, dan status codenya adalah : ${response.getStatusCode()}"

		// Mengambil data response dari API
		def responseText = response.getResponseText()

		// Parsing data response terbaru sebagai JSON
		def jsonResponse = new JsonSlurper().parseText(responseText)

		// mengambil dan menyimpan nilai status code
		String statusCode = jsonResponse.code

		// cek kondisi API
		readStatusCode(statusCode)
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>readStatusCode<b>
	 * digunakan untuk membaca status kode ketika panggil api
	 *
	 * @param statusCode
	 * @since 1.0
	 */
	void readStatusCode(String statusCode) {
		switch(statusCode) {
			case '409' :
				println("Failed to execute Api because conflict, error code 409.")
				break

			case '200' :
				println("Api Successfully Execute, code 200.")
				break

			case '400' :
				println("Bad Request, error code 400.")
				break

			case '401' :
				println("Unauthorized, error code 401.")
				break

			case '403' :
				println("Forbidden, error code 403.")
				break

			case '404' :
				println("Not found, error code 404.")
				break

			case '500' :
				println("Internal Server Error, 500.")
				break

			case '502' :
				println("Bad Gateway, 502.")
				break

			case '503' :
				println("Service Unavailable, 503.")
				break

			case '504' :
				println("Gateway Timeout, 504.")
				break
		}
	}
}