package com.coedotzmagic.qatools.util

import com.kms.katalon.util.CryptoUtil

/*
 * Created by : Arief Wardhana
 * itasoft gitlab / github : @poncoe
 * Date : 19-02-24
 */

public class MagixSecure {

	String m4n1dkR34lly = "y80eteWfBWtPS4jayukDpg==fujNizpM00E="

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