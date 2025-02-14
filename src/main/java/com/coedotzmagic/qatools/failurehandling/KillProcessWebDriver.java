package com.coedotzmagic.qatools.failurehandling;

import java.io.IOException;

/*
 * write by Coedotz
 * 14-02-2025
 */

public class KillProcessWebDriver {
   public KillProcessWebDriver() {
       this.killWebDriverProcesses();
   }

    private void killWebDriverProcesses() {
        try {
            // Windows
            if (isWindows()) {
                String[] processNames = {
                        "chromedriver.exe",
                        "geckodriver.exe",
                        "msedgedriver.exe",
                        "IEDriverServer.exe",
                        "safaridriver.exe"
                };
                for (String process : processNames) {
                    killProcessOnWindows(process);
                }
            }
            // Linux/macOS
            else if (isUnix()) {
                String[] processNames = {
                        "chromedriver",
                        "geckodriver",
                        "msedgedriver",
                        "safaridriver",
                        "IEDriverServer"
                };
                for (String process : processNames) {
                    killProcessOnUnix(process);
                }
            }
        } catch (IOException e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_KILL_PROCESS + e.getMessage());
        }
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    private static boolean isUnix() {
        return System.getProperty("os.name").toLowerCase().contains("nix") ||
                System.getProperty("os.name").toLowerCase().contains("nux") ||
                System.getProperty("os.name").toLowerCase().contains("mac");
    }

    private static void killProcessOnWindows(String processName) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("taskkill", "/F", "/IM", processName);
        builder.start();
        System.out.println("Killed process: " + processName);
    }

    private static void killProcessOnUnix(String processName) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("pkill", "-f", processName);
        builder.start();
        System.out.println("Killed process: " + processName);
    }
}