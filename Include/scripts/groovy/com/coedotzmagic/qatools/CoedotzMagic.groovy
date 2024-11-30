package com.coedotzmagic.qatools

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.coedotzmagic.qatools.util.*
import com.coedotzmagic.magixsecure.*

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger


/*
 * Created by : Arief Wardhana
 * itasoft gitlab : @poncoe
 * Date : 19-02-24
 */

public class CoedotzMagic {

	private static final Logger LOG = LogManager.getLogger()

	def util = new Util()
	def keys = new KeysTyping()
	def webservices = new WebServices()
	MagixSecure magixsecure = new MagixSecure();

	public String aaz() {
		return magixsecure.aaz()
	}

	public int aax() {
		return magixsecure.aax()
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
	 * <b>createNewTabV2()</b>
	 * digunakan untuk membantu membuat tab baru
	 * dengan url yang ditentukan
	 *
	 * @param url
	 * @since 1.1
	 */
	void createNewTabV2(String url) {
		util.createNewTabV2(url)
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
	 * <b>takeScreenshot()</b>
	 * digunakan untuk melakukan screenshot
	 *
	 * <br><br>
	 *
	 * @param fullpage
	 * @since 1.0
	 */
	void takeScreenshot(boolean fullpage) {
		util.takeScreenshot(fullpage)
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
	 * <b>readTestDataFromExcel()</b>
	 * digunakan untuk membaca data dan isi dari file excel
	 * 
	 * @param filePath
	 * @param sheetName
	 *
	 * @since 1.0
	 */
	public void readTestDataFromExcel(String filePath, String sheetName) {
		Integration.readTestDataFromExcel(filePath, sheetName)
	}
}