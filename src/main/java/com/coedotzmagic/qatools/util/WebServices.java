package com.coedotzmagic.qatools.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import com.coedotzmagic.qatools.failurehandling.TellMeWhy;

/*
 * write by Coedotz
 * 19-02-2024
 */

public class WebServices {
    private static String setResponseCode;
    private static String setResponseMessage;
    private static final String ERROR_409 = "Failed to execute Api because conflict, error code 409.";
    private static final String ERROR_200 = "Api Successfully Execute, code 200.";
    private static final String ERROR_400 = "Bad Request, error code 400.";
    private static final String ERROR_401 = "Unauthorized, error code 401.";
    private static final String ERROR_403 = "Forbidden, error code 403.";
    private static final String ERROR_404 = "Not Found, error code 404.";
    private static final String ERROR_500 = "Internal Server Error, error code 500.";
    private static final String ERROR_502 = "Bad Gateway, error code 502.";
    private static final String ERROR_503 = "Service Unavailable, error code 503.";
    private static final String ERROR_504 = "Gateway Timeout, error code 504.";
    private static final String UNKNOW_STATUSCODE = "Unknown Status Code: ";

    /**
     * <b>HitApi</b>
     * used to make API calls
     *
     * <br><br>
     *
     * @param urlTarget
     * @param method
     * @param payloadBody
     * @param authType
     * @param tokenOrCreds
     *
     * @since 1.4.1
     */
    public static void HitApi(String urlTarget, String method, String payloadBody, String authType, String tokenOrCreds) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlTarget);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method.toUpperCase());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            if (authType != null && tokenOrCreds != null) {
                if (authType.equalsIgnoreCase("Bearer")) {
                    conn.setRequestProperty("Authorization", "Bearer " + tokenOrCreds);
                } else if (authType.equalsIgnoreCase("Basic")) {
                    String basicAuth = Base64.getEncoder().encodeToString(tokenOrCreds.getBytes());
                    conn.setRequestProperty("Authorization", "Basic " + basicAuth);
                }
            }

            if (payloadBody != null && !payloadBody.isEmpty()) {
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = payloadBody.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            int responseCode = conn.getResponseCode();
            BufferedReader reader;
            if (responseCode >= 200 && responseCode < 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
            }

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line.trim());
            }
            reader.close();

            setResponseCode = String.valueOf(responseCode);
            setResponseMessage = response.toString();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_HITAPI);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * <b>HitApi</b>
     * used to make API calls with payload and without auth
     *
     * <br><br>
     *
     * @param urlTarget
     * @param method
     * @param payloadBody
     *
     * @since 1.4.1
     */
    public static void HitApi(String urlTarget, String method, String payloadBody) {
        HitApi(urlTarget, method, payloadBody, null, null);
    }

    /**
     * <b>HitApi</b>
     * used to make API calls without data
     *
     * <br><br>
     *
     * @param urlTarget
     * @param method
     *
     * @since 1.4.1
     */
    public static void HitApi(String urlTarget, String method) {
        HitApi(urlTarget, method, null, null, null);
    }

    /**
     * <b>HitApi</b>
     * used to make API calls with auth and without data
     *
     * <br><br>
     *
     * @param urlTarget
     * @param method
     * @param authType
     * @param tokenOrCreds
     *
     * @since 1.4.1
     */
    public static void HitApi(String urlTarget, String method, String authType, String tokenOrCreds) {
        HitApi(urlTarget, method, null, authType, tokenOrCreds);
    }

    /**
     * <b>getResponseCode</b>
     * used getResponseCode after call HitApi
     *
     * <br><br>
     *
     * @since 1.4.1
     */
    public static String getResponseCode() {
        if (!setResponseCode.equalsIgnoreCase("")) {
            try {
                String fieldName = "ERROR_" + setResponseCode;
                java.lang.reflect.Field field = WebServices.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                System.out.println(field.get(null));
            } catch (Exception e) {
                System.out.println(UNKNOW_STATUSCODE + setResponseCode);
            }
            return setResponseCode;
        } else {
            return "";
        }
    }

    /**
     * <b>getResponseMessage</b>
     * used getResponseMessage after call HitApi or HitApiWithData
     *
     * <br><br>
     *
     * @since 1.4.1
     */
    public static String getResponseMessage() {
        if (!setResponseMessage.equalsIgnoreCase("")) {
            return setResponseMessage;
        } else {
            return "";
        }
    }
}