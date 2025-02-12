package com.coedotzmagic.qatools.util;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.coedotzmagic.qatools.integration.ReadDataExcel;

/*
 * write by Coedotz
 * 19-02-2024
 */

public class SanityChecker {

    void processSanityCheckAuto(String excelFilePath, String sheetName, Map<String, String> dataPreparation, Consumer<Void> afterInputAction, Consumer<Void> positiveAction, Consumer<Void> negativeAction) {
        List<Map<String, String>> testData = ReadDataExcel.readTestDataFromExcel(excelFilePath, sheetName);

        assert testData != null;
        for (Map<String, String> row : testData) {
            String status = row.get("Status");

            for (Map.Entry<String, String> entry : dataPreparation.entrySet()) {
                String xpath = entry.getKey();
                String value = entry.getValue();

                if ((xpath != null && !xpath.equalsIgnoreCase("")) && (value != null && !value.equalsIgnoreCase(""))) {
                    System.out.println("Set text for " + xpath + " with value: " + value);
                }
            }

            if (afterInputAction != null) {
                afterInputAction.accept(null);
            }

            if (status.equalsIgnoreCase("Positive")) {
                if (positiveAction != null) {
                    positiveAction.accept(null);
                }
            } else if (status.equalsIgnoreCase("Negative")) {
                if (negativeAction != null) {
                    negativeAction.accept(null);
                }
            }
        }
    }

    void processSanityCheckManual(Map<String, String> dataPreparation, Consumer<Void> afterInputAction, Consumer<Void> positiveAction, Consumer<Void> negativeAction) {
        for (Map.Entry<String, String> entry : dataPreparation.entrySet()) {
            String xpath = entry.getKey();
            String value = entry.getValue();

            if ((xpath != null && !xpath.equalsIgnoreCase("")) && (value != null && !value.equalsIgnoreCase(""))) {
                System.out.println("Set text for " + xpath + " with value: " + value);
            }
        }

        if (afterInputAction != null) {
            afterInputAction.accept(null);
        }

        if (positiveAction != null) {
            positiveAction.accept(null);
        }

        if (negativeAction != null) {
            negativeAction.accept(null);
        }
    }
}

//// Sample data
//Map<String, String> dataPreparation = new HashMap<>();
//dataPreparation.put("Key1", "Object1");
//dataPreparation.put("Key2", "Object2");
//
//// Example afterInputAction, positiveAction, and negativeAction
//Consumer<Void> afterInputAction = (Void v) -> System.out.println("After input action");
//Consumer<Void> positiveAction = (Void v) -> System.out.println("Positive action");
//Consumer<Void> negativeAction = (Void v) -> System.out.println("Negative action");
//
//// Instantiate the processor and call the method
//SanityCheckProcessor processor = new SanityCheckProcessor();
//processor.processSanityCheckManual(dataPreparation, afterInputAction, positiveAction, negativeAction);
