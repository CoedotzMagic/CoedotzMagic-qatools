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
	MagixSecure magixsecure = new MagixSecure();

	public String aaz() {
		return magixsecure.aaz()
	}

	public int aax() {
		return magixsecure.aax()
	}

	public CoedotzMagic() {
		this.helloCoedotzMagic();
	}

	private void helloCoedotzMagic( ) {
		System.out.println("Everyones can be Magician! but Silence is golden.")
	}
}