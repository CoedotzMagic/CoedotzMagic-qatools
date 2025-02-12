package com.coedotzmagic.qatools.util;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * write by Coedotz
 * 19-02-2024
 */

public class TextUtil {

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
     * @param listToSearch
     *
     * @since 1.0
     */
    public String extractTextFromList(String targetText, List<String> listToSearch) {
        for (String textIWant : listToSearch) {
            if (targetText.toLowerCase().contains(textIWant.toLowerCase())) {
                return textIWant;
            }
            if (textIWant.toLowerCase().contains(targetText.toLowerCase())) {
                return textIWant;
            }
        }

        return null;
    }

    /**
     * <b>extractText()</b>
     * digunakan untuk melakukan ekstraksi teks berdasarkan teks yang kita mau
     * misal, teks "COEDOTZ ITA 12 SOFT 4 SKYYLUVIE dan kita mau kata COEDOTZ aja berati nanti outputnya "COEDOTZ"
     *
     * <br><br>
     *
     * @param targetText
     * @param textIWant
     *
     * @since 1.0
     */
    public String extractText(String targetText, String textIWant) {
        String pattern = "(?i)" + textIWant;
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(targetText);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }

    /**
     * <b>copyToClipboard()</b>
     * digunakan untuk melakukan salin teks ke clipboard external chrome
     * biasanya digunakan untuk handle CTRL+F pada chrome atau dialog chooser
     *
     * @since 1.0
     */
    public void copyToClipboard(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
    }
}