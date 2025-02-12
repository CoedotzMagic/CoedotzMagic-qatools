package com.coedotzmagic.qatools.util;

import java.awt.*;
import java.awt.event.*;

/*
 * write by Coedotz
 * 19-02-2024
 */

public class KeysTyping {

    /**
     * <b>CombinationCTRLF()</b>
     * digunakan untuk melakukan perintah CTRL+F (Find)
     *
     * <br><br>
     *
     * @since 1.0
     */
    void CombinationCTRLF() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * <b>CombinationCTRLV()</b>
     * digunakan untuk melakukan perintah CTRL+V (Paste)
     *
     * <br><br>
     *
     * @since 1.0
     */
    void CombinationCTRLV() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * <b>CombinationCTRLC()</b>
     * digunakan untuk melakukan perintah CTRL+C (Copy)
     *
     * <br><br>
     *
     * @since 1.0
     */
    void CombinationCTRLC() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * <b>CombinationENTER()</b>
     * digunakan untuk melakukan perintah ENTER
     *
     * <br><br>
     *
     * @since 1.0
     */
    void CombinationENTER() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * <b>CombinationESC()</b>
     * digunakan untuk melakukan perintah ESC
     *
     * <br><br>
     *
     * @since 1.0
     */
    void CombinationESC() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }
}