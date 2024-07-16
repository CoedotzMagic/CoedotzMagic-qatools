package com.coedotzmagic.qatools

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.coedotzmagic.qatools.util.*

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger


/*
 * Created by : Arief Wardhana
 * itasoft gitlab : @poncoe
 * Date : 19-02-24
 */

public class CoedotzMagic {

	String TESTLINK_TEST_PASSED = "p"
	String TESTLINK_TEST_BLOCKED = "b"
	String TESTLINK_TEST_FAILED = "f"
	String TESTLINK_TEST_WRONG = "w"
	String TESTLINK_TEST_DEPARTED = "d"
	String TESTLINK_TEST_SET_HIGH = "3"
	String TESTLINK_TEST_SET_MEDIUM = "2"
	String TESTLINK_TEST_SET_LOW = "1"

	private static final Logger LOG = LogManager.getLogger()

	def util = new Util()
	def keys = new KeysTyping()
	def webservices = new WebServices()
	def jogethelper = new JogetHelper()

	public String aaz() {
		return jogethelper.aaz()
	}

	public int aax() {
		return jogethelper.aax()
	}

	/**
	 * <b>extractTextFromList()</b>
	 * digunakan untuk melakukan ekstraksi teks dari data list
	 * maksudnya misal, ada service id 50250205_122848263712_INTERNET
	 * nah dia akan melakukan pencarian dengan mencocokan data di list, apakah pada targetText
	 * ada kata yang cocok dengan yang dilist, jika ada nanti akan dioutputkan, jadi nanti dilist ada
	 * data "internet" nah nanti outputnya jadi "internet" karena di target textnya ada kata "internet"
	 *
	 *<br><br>
	 *
	 * @param targetText
	 * @param listToSearch, list yang akan digunakan pada ekstrak
	 * @since 1.0
	 */
	public String extractTextFromList(String targetText, List<String> listToSearch) {
		util.extractTextFromList(targetText, listToSearch)
	}

	/**
	 * <b>extractText()</b>
	 * digunakan untuk melakukan ekstraksi teks berdasarkan teks yang kita mau
	 * misal, teks "COEDOTZ ITA 12 SOFT 4 SKYYLUVIE dan kita mau kata COEDOTZ aja berati nanti outputnya "COEDOTZ"
	 * 
	 * <br><br>
	 * 
	 * Parameter masukannya adalah targetText (kata yang ingin diekstrak) dan
	 * textIWant (Kata yang mau kita ambil)
	 * 
	 * @param targetText
	 * @param textIWant
	 * 
	 * @since 1.0
	 */
	public String extractText(String targetText, String textIWant) {
		util.extractText(targetText, textIWant)
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>robotUploadFile()</b>
	 * digunakan untuk melakukan automasi dialog file chooser
	 * untuk melakukan pemilihan file secara otomatis pada dialog file chooser
	 * 
	 * <br><br>
	 * 
	 * parameter masukannya adalah uploadLocator & via.
	 * dimana uploadLocator sebagai lokasi path area uploadnya dan via itu rutenya
	 * 
	 * @param uploadLocator
	 * @param pathFile
	 * 
	 * @since 1.0
	 */
	void robotUploadFile(String uploadLocator, String pathFile) {
		jogethelper.robotUploadFile(uploadLocator, pathFile)
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>checkStatusTicket()</b>
	 * digunakan untuk mengecek status tiket sekarang secara otomatis, berdasarkan kriteria
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
	public String checkStatusTicket(String testObjListWizard){
		String result = jogethelper.checkStatusTicket(testObjListWizard)
		return result
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>combinationCTRLF()</b>
	 * digunakan untuk melakukan perintah CTRL+F (Find) pada Chrome
	 * biasanya digunakan untuk di elastic atau dilainnnya.
	 * 
	 * @since 1.0
	 */
	void combinationCTRLF () {
		keys.combinationCTRLF()
	}

	/**
	 * <b>combinationCTRLV()</b>
	 * digunakan untuk melakukan perintah CTRL+V (Paste) pada chrome
	 * 
	 * @since 1.0
	 */
	void combinationCTRLV() {
		keys.combinationCTRLV()
	}

	/**
	 * <b>combinationENTER()</b>
	 * digunakan untuk melakukan perintah ENTER pada chrome
	 * biasanya untuk handle external dialog atau apapun itu untuk melakukan ENTER/OK pada external dialog
	 * 
	 * @since 1.0
	 */
	void combinationENTER() {
		keys.combinationENTER()
	}

	/**
	 * <b>copyToClipboard()</b>
	 * digunakan untuk melakukan salin teks ke clipboard external chrome
	 * biasanya digunakan untuk handle CTRL+F pada chrome atau dialog chooser
	 * 
	 * @param text
	 * @since 1.0
	 */
	void copyToClipboard(String text) {
		util.copyToClipboard(text)
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>checkTabBrowser()</b>
	 * digunakan untuk mengecek apakah ada Tab Aktif dengan website khusus atau tidak
	 * semisal kita ingin cek apakah situs google aktif atau tidak di tab browser atau tab lainnya
	 * jika aktif nanti akan mengembalikan nilai "Tab Found" jika tidak "Tab Not Found"
	 * 
	 * @param targetUrl
	 * @since 1.0
	 */
	public String checkTabBrowser(String targetUrl) {
		String result = util.checkTabBrowser(targetUrl)
		return result
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>forceSetText()</b>
	 * digunakan untuk melakukan paksa Set Text
	 * method ini digunakan untuk handling ketika ketika ingin merubah inputan ke nilai terbaru
	 * tetapi masih nyimpen nilai lama
	 * 
	 * @param to
	 * @param text
	 * 
	 * @since 1.0
	 */
	void forceSetText(TestObject to, String text) {
		util.forceSetText(to, text)
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>getCurrentDateTime()</b>
	 * digunakan untuk mendapatkan Tanggal dan Waktu hari ini
	 * biasanya fungsi ini digunakan untuk data unik / timestamp
	 * 
	 * @since 1.0
	 */
	public String getCurrentDateTime() {
		String dateTime = util.performGetDateTime()
		return dateTime
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>testlinkUpdateResults()</b>
	 * digunakan untuk melakukan integrasi ke Testlink
	 * dengan cara menjalankan dan menghasilkan report dan reportnya dilaporkan ke Testlink
	 *
	 * @param projectName
	 * @param testplanName
	 * @param testcaseName
	 * @param buildName
	 * @param execNotes
	 * @param result
	 * 
	 * @since 1.0
	 */
	void testlinkUpdateResults(String projectname, String testplanName, String testcaseName, String buildName, String notes, String status) {
		Integration.testlinkUpdateResults(projectname, testplanName, testcaseName, buildName, notes, status)
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>hitApi</b>
	 * digunakan untuk melakukan pemanggilan API tanpa data
	 *
	 * @param apiPath
	 * @since 1.0
	 */
	void hitApi(String apiPath) {
		webservices.hitApi(apiPath)
	}

	/**
	 * <b>hitApiWithData</b>
	 * digunakan untuk melakukan pemanggilan API dengan data
	 *
	 * @param apiPath
	 * @param dataMap
	 * 
	 * @since 1.0
	 */
	void hitApiWithData(String apiPath, Map<String, Object> dataMap) {
		webservices.hitApiWithData(apiPath, dataMap)
	}

	/**
	 * <b>createNewTab()</b>
	 * digunakan untuk membantu membuat tab baru 
	 * dengan url yang ditentukan
	 * 
	 * @param url
	 * @since 1.0
	 */
	void createNewTab(String url) {
		util.createNewTab(url)
	}

	/**
	 * <b>takeScreenshot()</b>
	 * digunakan untuk melakukan screenshot
	 * 
	 * @param folderName
	 * @param fullpage
	 * 
	 * @since 1.0
	 */
	void takeScreenshot(String folderName, boolean fullpage) {
		util.takeScreenshot(folderName, fullpage)
	}

	/**
	 * <b>startScreenRecording()</b>
	 * digunakan untuk memulai Screen Recording
	 *
	 * @since 1.0
	 */
	public void startScreenRecording() {
		try {
			ScreenRecordingHelper.startRecording()
		} catch (final Exception e) {
			LOG.error(e)
		}
	}

	/**
	 * <b>stopScreenRecording()</b>
	 * digunakan untuk menghentikan Screen Recording
	 *
	 * @since 1.0
	 */
	public void stopScreenRecording() {
		try {
			ScreenRecordingHelper.stopRecording()
		} catch (final Exception e) {
			LOG.error(e)
		}
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
	public void testlinkCreateTestSuite(String projectName, String suiteName, String description) {
		Integration.testlinkCreateTestSuite(projectName, suiteName, description)
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
	public void testlinkCreateBuild(String projectName, String testplanName, String buildName, String buildNotes) {
		Integration.testlinkCreateBuild(projectName, testplanName, buildName, buildNotes)
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
	public void testlinkCreateTestProject(String projectName, String projectPrefix, String description) {
		Integration.testlinkCreateTestProject(projectName, projectPrefix, description)
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
	public void testlinkAddTestCaseToTestPlan(String projectName, String testplanName, String testcaseName) {
		Integration.testlinkAddTestCaseToTestPlan(projectName, testplanName, testcaseName)
	}

	/**
	 * <b>readTestDataFromExcel()</b>
	 * digunakan untuk membaca data dan isi dari file excel
	 * 
	 * @param filePath
	 * @param sheetName
	 *
	 * @since 1.1
	 */
	public void readTestDataFromExcel(String filePath, String sheetName) {
		Integration.readTestDataFromExcel(filePath, sheetName)
	}
}