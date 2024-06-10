package id.coedotz.QAJWorkflow.coedotzmagic

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
 * itasoft gitlab : @poncoe
 * Date : 19-02-24
 */

public class CoedotzMagic {

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
	 */
	@Keyword
	public String extractTextFromList(String targetText, List<String> listToSearch) {
		// melakukan pengecekan secara menyeluruh pada data list untuk mendapatkan kata yang diinginkan
		for (String textIWant : listToSearch) {
			// gunakan komparasi case-insensitive untuk mengecek jika targetText berkontaminasi dengan kata yang diinginkan
			if (targetText.toLowerCase().contains(textIWant.toLowerCase())) {
				return textIWant
			}

			// cek jika kata yang diinginkan berkontaminasi dengan yang ada di targetText
			if (textIWant.toLowerCase().contains(targetText.toLowerCase())) {
				return textIWant
			}
		}

		// mengembalikan nilai kosong jika tidak ada kata yang diinginkan di targetText
		return null
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
	 */
	@Keyword
	public String extractText(String targetText, String textIWant) {
		def inputText = targetText

		// gunakan case-insensitif regular ekspresi untuk mencari kata yang diinginkan
		def pattern = "(?i)${textIWant}"
		def matcher = (inputText =~ pattern)

		// jika ditemukan dan sesuai, maka kembalikan nilai yang sesuai
		if (matcher.find()) {
			return matcher.group(0)
		}

		// mengembalikan nilai kosong jika tidak ada kata yang diinginkan di targetText
		return null
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
		'Arahkan dengan mouse over'
		WebUI.mouseOver(findTestObject(uploadLocator))

		// set path untuk pemanggilan si file (Gambar)
		def fileImgPath = RunConfiguration.getProjectDir() + '/testImgBorr.png'

		// set path untuk pemanggilan si file (Dokumen)
		def fileDocPath = RunConfiguration.getProjectDir() + '/testDocBorr.docx'

		// rubah tanda "/" jadi "\" (Gambar)
		fileImgPath = fileImgPath.replaceAll('/', '\\\\')

		// rubah tanda "/" jadi "\" (Dokumen)
		fileDocPath = fileDocPath.replaceAll('/', '\\\\')

		// melakukan pengecekan jika attachment & evidence dia akan upload img, dan worklogs upload doc
		switch(via.toLowerCase()) {
			case "tiket attachments":
			case "tiket evidence after":
			// lakukan copy clipboard untuk lokasi file
				copyToClipboard(fileImgPath)
				break

			case "worklogs":
			// lakukan copy clipboard untuk lokasi file
				copyToClipboard(fileDocPath)
				break
		}

		// klik Area Upload File
		WebUI.click(findTestObject(uploadLocator))

		// jeda buat nunggu dialog terbuka, cuma garekomen terus2an pake Thread.sleep()
		Thread.sleep(2000)

		'Lakukan CTRL+V'
		combinationCTRLV()

		'Lakukan Press ENTER'
		combinationENTER()
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

		// cek element ada apa enggga
		boolean checkListProgressbar = WebUI.verifyElementNotPresent(findTestObject('Page/Insera/Ticketing/Components/List/list_status_ticket'), GlobalVariable.VERIFY_TIMEOUT, FailureHandling.OPTIONAL)

		// Menampung nilai didalam result
		def result = null

		if (!checkListProgressbar) {
			// panggil element dengan nama listProgressbar
			def listProgressBar = WebUI.findWebElement(findTestObject('Page/Insera/Ticketing/Components/List/list_status_ticket'), GlobalVariable.VERIFY_TIMEOUT, FailureHandling.OPTIONAL)

			// cari semua div yang ada didalam element  listProgressbar
			List<WebElement> innerDivElements = listProgressBar.findElements(By.xpath(".//div"))

			// mengalihkan ke dalam element div yang ada di listProgressbar
			innerDivElements.each { innerDivElement ->
				// ambil id dan class nilai atribut
				def idValue = innerDivElement.getAttribute("id")
				def classValue = innerDivElement.getAttribute("class")

				// cek jika classValue berisikan nilai "progressbar running"
				if (classValue.contains("progressbar running")) {
					result = "Action ${idValue}"
				}
			}
		}

		if (result == null) {
			result = "not in ticketing page"
		}

		return result
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>combinationCTRLF()</b>
	 * digunakan untuk melakukan perintah CTRL+F (Find) pada Chrome
	 * biasanya digunakan untuk di elastic atau dilainnnya.
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
	 */
	void combinationENTER() {
		// implementasi java robot
		Robot robot = new Robot()

		// Tekan Enter untuk konfirmasi
		robot.keyPress(KeyEvent.VK_ENTER)
		robot.keyRelease(KeyEvent.VK_ENTER)
	}

	/**
	 * <b>copyToClipboard()</b>
	 * digunakan untuk melakukan salin teks ke clipboard external chrome
	 * biasanya digunakan untuk handle CTRL+F pada chrome atau dialog chooser
	 */
	void copyToClipboard(String text) {
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null)
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
		// masukan target url yang ingin dicari
		def urlToFind = targetUrl

		// jalankan javascript untuk mendapatkan jumlah angka dari tab yang terbuka
		String jsCodeNumberWindows = "return window.top.frames.length;"
		JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getWebDriver()
		jsExecutor.executeScript(jsCodeNumberWindows)
		int numberOfOpenWindows = DriverFactory.getWebDriver().getWindowHandles().size()

		// jalankan javascript untuk mendapatkan url sekarang dari masing2 tab
		String result = null
		for (int i = 0; i < numberOfOpenWindows; i++) {
			// arahkan tab ke target
			WebUI.switchToWindowIndex(i)

			// jalankan javascript untuk mendapatkan url root saat ini
			String jsCode = "return window.top.location.href;"

			try {
				String currentURL = WebUI.executeJavaScript(jsCode, null)

				// Periksa apakah URL yang diinginkan ada di URL saat ini
				if (currentURL.contains(urlToFind)) {
					result = "tab found"
					break
				}
			} catch (Exception e) {
				// Handle jika terjadi eksepsi
				println("gagal mendapatkan situs web : ${e.message}")
				result = "Error checking"
			}
		}

		// Arahkan ke tab sebelumnya
		WebUI.switchToWindowIndex(0)

		// jika tidak ditemukan / kosong
		if (result == null) {
			result = "tab not found"
		}

		// kembalikan nilai result
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
		'Lakukan Clear Text pada inputfield / teks area'
		WebUI.clearText(to)

		'Lakukan CTRL+A pada inputfield/ teks area'
		WebUI.sendKeys(to, Keys.chord(Keys.CONTROL, 'a'));

		'Lakukan Clear Text pada inputfield / teks area'
		WebUI.clearText(to)

		'Lakukan CTRL+A pada inputfield/ teks area'
		WebUI.sendKeys(to, Keys.chord(Keys.CONTROL, 'a'));

		'Lakukan pengisian inputfield / teks area'
		WebUI.sendKeys(to, text);

		'Lakukan Aksi Enter pada inputfield / teks area'
		WebUI.sendKeys(to, Keys.chord(Keys.ENTER));
	}

	/* ------------------------------------------------------------------------- */

	/**
	 * <b>performGetDateTime()</b>
	 * digunakan untuk mendapatkan Tanggal dan Waktu hari ini
	 * biasanya fungsi ini digunakan untuk pembuatan di summary ticketing
	 */
	public String performGetDateTime() {
		'Inisialisasi Kalender'
		Calendar cal = Calendar.getInstance()

		'Ambil tanggal & waktu sekarang'
		Date currentDate = cal.time

		'Inisialisasi & membuat format tanggal ke dd-mm-yyyy HH:mm:ss'
		SimpleDateFormat dateFormat = new SimpleDateFormat('dd-MM-yyyy HH:mm:ss')

		'Menggambil tanggal hari ini dan merubah format ke dd-mm-yyyy'
		String formattedDateTime = dateFormat.format(currentDate)

		'Mengembalikan nilai currentDateTime'
		return formattedDateTime
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
		'inisiasi driverfactory untuk new tab'
		DriverFactory.getWebDriver().executeScript('window.open();')

		'buat dan pindah ke tab baru'
		WebUI.switchToWindowTitle('')

		'Masukan Url'
		WebUI.navigateToUrl(url)
	}

	/**
	 * <b>takeScreenshot()</b>
	 * digunakan untuk melakukan screenshot
	 * 
	 * <br><br>
	 *
	 * Parameter masukannya yaitu folderName & title
	 */
	void takeScreenshot(String folderName) {
		'Lokasi folder screenshot'
		String pathFolderScreenshot = RunConfiguration.getProjectDir() + File.separator + "Screenshot"

		'Nama Target Folder'
		String nameMasterTestcase = pathFolderScreenshot + File.separator + folderName

		'Target Folder'
		String targetFolder = folderName + File.separator

		'Membuat direktori, jika direktori belum dibuat'
		new File(nameMasterTestcase).mkdirs()

		'Membuat timestamp agar bisa mutiple screenshot dan tidak overwrite file'
		String timestamp = getCurrentDateTime()
		timestamp = timestamp.replaceAll(":", ".")

		'set text jadi Screenshot'
		String title = 'Screenshot '

		'Kondisi jika nama foldernya insera'
		if (folderName.equalsIgnoreCase("Insera")) {
			'Melakukan Screenshot untuk bukti pengujian'
			WebUI.takeScreenshot('Screenshot/' + targetFolder + title + ' - ' + timestamp + '.jpg')
		} else {
			'Melakukan Screenshot untuk bukti pengujian'
			WebUI.takeFullPageScreenshot('Screenshot/' + targetFolder + title + ' - ' + timestamp + '.jpg')
		}
	}

	public String getCurrentDateTime() {
		'Panggil current date time'
		String getDateTime = coedotzMagic.getCurrentDateTime()

		'Rubah teks : ke .'
		getDateTime = getDateTime.replaceAll(":", ".")

		'Mengembalikan nilai currentDateTime'
		return getDateTime
	}



}