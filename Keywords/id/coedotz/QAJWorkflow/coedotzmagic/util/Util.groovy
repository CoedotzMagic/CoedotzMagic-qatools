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

public class Util {
	
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
	 * @param masukannya adalah targetText (kata yang ingin diekstrak) dan
	 * textIWant (Kata yang mau kita ambil)
	 * @since 1.0
	 */
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
	 * <b>copyToClipboard()</b>
	 * digunakan untuk melakukan salin teks ke clipboard external chrome
	 * biasanya digunakan untuk handle CTRL+F pada chrome atau dialog chooser
	 * 
	 * @since 1.0
	 */
	void copyToClipboard(String text) {
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null)
	}
	
	/**
	 * <b>forceSetText()</b>
	 * digunakan untuk melakukan paksa Set Text
	 * method ini digunakan untuk handling ketika ketika ingin merubah inputan ke nilai terbaru
	 * tetapi masih nyimpen nilai lama
	 *
	 * <br><br>
	 *
	 * @param to & text
	 * @since 1.0
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
	 * 
	 * @since 1.0
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
		
		'Rubah teks : ke .'
		formattedDateTime = formattedDateTime.replaceAll(":", ".")

		'Mengembalikan nilai currentDateTime'
		return formattedDateTime
	}

	/* ------------------------------------------------------------------------- */
	
	/**
	 * <b>createNewTab()</b>
	 * digunakan untuk membantu membuat tab baru
	 * dengan url yang ditentukan
	 *
	 * <br><br>
	 *
	 * @param url
	 * @since 1.0
	 */
	void createNewTab(String url) {
		'inisiasi driverfactory untuk new tab'
		DriverFactory.getWebDriver().executeScript('window.open();')

		'buat dan pindah ke tab baru'
		WebUI.switchToWindowTitle('')

		'Masukan Url'
		WebUI.navigateToUrl(url)
	}
	
	/* ------------------------------------------------------------------------- */

	/**
	 * <b>takeScreenshot()</b>
	 * digunakan untuk melakukan screenshot
	 *
	 * <br><br>
	 *
	 * @param folderName & title
	 * @since 1.0
	 */
	void takeScreenshot(String folderName, boolean fullpage) {
		'Lokasi folder screenshot'
		String pathFolderScreenshot = RunConfiguration.getProjectDir() + File.separator + "Screenshot"

		'Nama Target Folder'
		String nameMasterTestcase = pathFolderScreenshot + File.separator + folderName

		'Target Folder'
		String targetFolder = folderName + File.separator

		'Membuat direktori, jika direktori belum dibuat'
		new File(nameMasterTestcase).mkdirs()

		'Membuat timestamp agar bisa mutiple screenshot dan tidak overwrite file'
		String timestamp = performGetDateTime()
		timestamp = timestamp.replaceAll(":", ".")

		'set text jadi Screenshot'
		String title = 'Screenshot '

		'Kondisi jika nama foldernya insera'
		if (fullpage) {
			'Melakukan Screenshot untuk bukti pengujian'
			WebUI.takeFullPageScreenshot('Screenshot/' + targetFolder + title + ' - ' + timestamp + '.jpg')
		} else {
			'Melakukan Screenshot untuk bukti pengujian'
			WebUI.takeScreenshot('Screenshot/' + targetFolder + title + ' - ' + timestamp + '.jpg')
		}
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
	 * @since 1.0
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
	
}