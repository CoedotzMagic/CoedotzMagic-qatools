package id.coedotz.coedotzmagic

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

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
	 * Parameter masukannya targetText & list yang akan digunakan pada ekstrak
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
}