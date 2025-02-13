package com.coedotzmagic.qatools.util;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextUtilTest {

    TextUtil textUtil = new TextUtil();

    @org.junit.jupiter.api.Test
    void extractTextFromList() {
        String targetText1 = "50250205_122848263712_INTERNET";
        List<String> listToSearch1 = List.of("internet", "voice", "iptv");
        String result1 = textUtil.extractTextFromList(targetText1, listToSearch1);
        assertEquals("internet", result1, "The method should return 'internet'");
    }

    @org.junit.jupiter.api.Test
    void extractText() {
        String targetText1 = "COEDOTZ ITA 12 SOFT 4 SKYYLUVIE";
        String textIWant = "Coedotz";
        String result = textUtil.extractText(targetText1, textIWant);
        assertEquals("COEDOTZ", result, "The method should return 'COEDOTZ'");
    }

    @org.junit.jupiter.api.Test
    void copyToClipboard() {
        String targetText1 = "coedotzmagic";
        textUtil.copyToClipboard(targetText1);
        String result = textUtil.getClipboardContent();
        assertEquals("coedotzmagic", result, "The method should return 'coedotzmagic'");
    }
}