package id.coedotz.QAJWorkflow.coedotzmagic

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.TestObject as TestObject

import id.coedotz.QAJWorkflow.coedotzmagic.util.*


/*
 * Created by : Arief Wardhana
 * itasoft gitlab : @poncoe
 * Date : 19-02-24
 */

public class CoedotzMagic {
	
	def util = new Util()
	def keys = new KeysTyping()
	def integration = new Integration()
	def webservices = new WebServices()
	def jogethelper = new JogetHelper()

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
	 * @param targetText & list yang akan digunakan pada ekstrak
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
	 * <br><br>
	 * 
	 * Method ini hanya bisa digunakan pada method uploadFile di controller!
	 */
	void robotUploadFile(String uploadLocator, String via) {
		jogethelper.robotUploadFile(uploadLocator, via)
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
	 */
	public String checkStatusTicket(){
		String result = jogethelper.checkStatusTicket()
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
	 * <br><br>
	 * 
	 * fungsi ini digunakan untuk pengecekan Elastic (biasanya)
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
	 * <br><br>
	 * 
	 * Parameter masukannya to & text
	 */
	void forceSetText(TestObject to, String text) {
		util.forceSetText(to, text)
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>getCurrentDateTime()</b>
	 * digunakan untuk mendapatkan Tanggal dan Waktu hari ini
	 * biasanya fungsi ini digunakan untuk pembuatan di summary ticketing
	 */
	public String getCurrentDateTime() {
		String dateTime = util.performGetDateTime()
		return dateTime
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>testlinkIntegration()</b>
	 * digunakan untuk melakukan integrasi ke Testlink
	 * dengan cara menjalankan dan menghasilkan report dam reportnya dilaporkan ke Testlink
	 *
	 * <br><br>
	 *
	 * Parameter masukanya status & notes
	 * - status : p (Passed), f (Failed) & a (Abort)
	 * - Notes : Catatan Pengujian
	 */
	void testlinkIntegration(String status, String notes) {
		integration.testlinkIntegration(status, notes)
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * @deprecated <b>hitTicketTSAUsingAPI() UNAVAILABLE/TAKEOUT</b>
	 * digunakan untuk melakukan pembuatan tiket MyTSA
	 * Tiket awalnya akan dibuat seperti biasa, tetapi untuk mengintegrasikan MyTSA
	 * perlu adanya pemanggilan API "API/hitTicketTSA"
	 * 
	 * <br><br>
	 * 
	 * Parameter masukannya adalah idTicket yang ingin di integrasikan ke MyTSA
	 */
	void hitTicketTSAUsingAPI(String idTicket) {
		webservices.hitTicketTSAUsingAPI(idTicket)
	}

	/**
	 * <b>createNewTab()</b>
	 * digunakan untuk membantu membuat tab baru 
	 * dengan url yang ditentukan
	 * 
	 * <br><br>
	 * 
	 * Parameter masukannya yaitu url
	 */
	void createNewTab(String url) {
		util.createNewTab(url)
	}

	/**
	 * <b>takeScreenshot()</b>
	 * digunakan untuk melakukan screenshot
	 * 
	 * <br><br>
	 *
	 * Parameter masukannya yaitu folderName & title
	 */
	void takeScreenshot(String folderName, boolean fullpage) {
		util.takeScreenshot(folderName, fullpage)
	}
}