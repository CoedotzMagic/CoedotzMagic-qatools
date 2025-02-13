package com.coedotzmagic.qatools.integration;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.StringJoiner;

/*
 * write by Coedotz
 * 09-02-2025
 */

public class ImprovImgCaptcha {
    private static final int DELTA = 3;

    static boolean isEligible(BufferedImage img, int x, int y) {
        int left = x - 1;
        while (left >= 0 && (x - left) < 2 * DELTA) {
            if (img.getRGB(left, y) == Color.WHITE.getRGB()) {
                break;
            }
            left--;
        }
        if (left < 0) {
            return false;
        }

        int right = x + 1;
        while (right < img.getWidth() && (right - left) < 2 * DELTA) {
            if (img.getRGB(right, y) == Color.WHITE.getRGB()) {
                break;
            }
            right++;
        }
        if (right >= img.getWidth()) {
            return false;
        }

        int top = y - 1;
        while (top >= 0 && (y - top) < 2 * DELTA) {
            if (img.getRGB(x, top) == Color.WHITE.getRGB()) {
                break;
            }
            top--;
        }
        if (top < 0) {
            return false;
        }

        int bottom = y + 1;
        while (bottom < img.getHeight() && (bottom - top) < 2 * DELTA) {
            if (img.getRGB(x, bottom) == Color.WHITE.getRGB()) {
                break;
            }
            bottom++;
        }
        if (bottom >= img.getHeight()) {
            return false;
        }

        int width = right - left;
        int height = bottom - top;
        return width >= DELTA && height >= DELTA;
    }

    public static BufferedImage cleanImage(BufferedImage source) {
        BufferedImage clone = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics2D g2d = clone.createGraphics();
        g2d.drawImage(source, 0, 0, null);
        g2d.dispose();

        for (int i = 0; i < clone.getWidth(); i++) {
            for (int j = 0; j < clone.getHeight(); j++) {
                int rgb = clone.getRGB(i, j);
                if (rgb == Color.WHITE.getRGB()) {
                    continue;
                }
                if (isEligible(clone, i, j)) {
                    continue;
                } else {
                    clone.setRGB(i, j, Color.WHITE.getRGB());
                }
            }
        }

        return clone;
    }

    static String cleanResult(String result) {
        StringJoiner sb = new StringJoiner("");
        for (int i = 0; i < result.length(); i++) {
            char c = result.charAt(i);
            if (Character.isAlphabetic(c) || Character.isDigit(c)) {
                sb.add(String.valueOf(c));
            }
        }
        return sb.toString();
    }
}
