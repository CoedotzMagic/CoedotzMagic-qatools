package com.coedotzmagic.qatools.util;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
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
    private static int timeout = 10;
    private static String authType;
    private static String tokenBearer;
    private static String tokenBasic;
    private static String api_id;
    private static String api_key;

    private static final String TRY_COMMUNICATE_API = "Trying to Hit API... ";
    private static final String ERROR_200 = "Api Successfully Execute, code 200.";
    private static final String ERROR_201 = "Api Successfully Created, code 201.";
    private static final String ERROR_400 = "Bad Request, error code 400.";
    private static final String ERROR_401 = "Unauthorized, error code 401.";
    private static final String ERROR_403 = "Forbidden, error code 403.";
    private static final String ERROR_404 = "Not Found, error code 404.";
    private static final String ERROR_409 = "Failed to execute Api because conflict, error code 409.";
    private static final String ERROR_500 = "Internal Server Error, error code 500.";
    private static final String ERROR_502 = "Bad Gateway, error code 502.";
    private static final String ERROR_503 = "Service Unavailable, error code 503.";
    private static final String ERROR_504 = "Gateway Timeout, error code 504.";
    private static final String UNKNOW_STATUSCODE = "Unknown Status Code: ";

    /**
     * <b>SetAuthBaerer</b>
     * used to set Auth Baerer
     *
     * <br><br>
     *
     * @param value
     *
     * @since 1.4.1
     */
    public static void SetAuthBaerer(String value) {
        authType = "Bearer";
        tokenBearer = value;
    }

    /**
     * <b>SetAuthBasic</b>
     * used to set Auth Basic
     *
     * <br><br>
     *
     * @param value
     *
     * @since 1.4.1
     */
    public static void SetAuthBasic(String value) {
        authType = "Basic";
        tokenBasic = value;
    }

    /**
     * <b>SetAuthApiIdKey</b>
     * used to set Auth Api ID & Api Key
     *
     * <br><br>
     *
     * @param valueId
     * @param valueKey
     *
     * @since 1.4.1
     */
    public static void SetAuthApiIdKey(String valueId, String valueKey) {
        authType = "API_ID_KEY";
        api_id = valueId;
        api_key = valueKey;
    }

    /**
     * <b>HitApi</b>
     * used to make API calls
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
        System.out.println(TellMeWhy.getIdentity() + TRY_COMMUNICATE_API);
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlTarget);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(timeout * 1000);
            conn.setReadTimeout(timeout * 1000);
            conn.setRequestMethod(method.toUpperCase());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            if (authType != null && !authType.equalsIgnoreCase("")) {
                if ("Bearer".equalsIgnoreCase(authType) && tokenBearer != null && !tokenBearer.equalsIgnoreCase("")) {
                    conn.setRequestProperty("Authorization", "Bearer " + tokenBearer);
                } else if ("Basic".equalsIgnoreCase(authType) && tokenBasic != null && !tokenBasic.equalsIgnoreCase("")) {
                    String basicAuth = Base64.getEncoder().encodeToString(tokenBasic.getBytes());
                    conn.setRequestProperty("Authorization", "Basic " + basicAuth);
                } else if ("API_ID_KEY".equalsIgnoreCase(authType) && api_id != null && !api_id.equalsIgnoreCase("") && api_key != null && !api_key.equalsIgnoreCase("")) {
                        conn.setRequestProperty("api_id", api_id);
                        conn.setRequestProperty("api_key", api_key);
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

            try {
                String fieldName = "ERROR_" + setResponseCode;
                Field field = WebServices.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                System.out.println(field.get(null));
            } catch (Exception e) {
                System.out.println(UNKNOW_STATUSCODE + setResponseCode);
            }

        } catch (Exception e) {
            TellMeWhy.getPrintMsgErrActive(e);
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_HITAPI);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
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
        HitApi(urlTarget, method, null);
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
        if (setResponseCode != null && !setResponseCode.isEmpty()) {
            System.out.println();
            System.out.println("------- Status Code -------");
            return setResponseCode;
        } else {
            new TellMeWhy("w", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_GETVALUE_HITAPI);
            return "";
        }
    }

    /**
     * <b>getResponseMessage</b>
     * used getResponseMessage after call HitApi with specific rootJsonObj & keyJsonObject
     *
     * <br><br>
     *
     * @param rootJsonObj
     * @param keyJsonObj
     *
     * @since 1.4.1
     */
    public static String getResponseMessage(String rootJsonObj, String keyJsonObj) {
        String finalOutput = "";
        try {
            if (setResponseMessage != null && !setResponseMessage.isEmpty()) {
                JSONObject jsonObject = new JSONObject(setResponseMessage);
                if (!rootJsonObj.equalsIgnoreCase("")) {
                    if (!keyJsonObj.equalsIgnoreCase("")) {
                        JSONObject data = jsonObject.getJSONObject(rootJsonObj);
                        Object value = data.get(keyJsonObj);
                        finalOutput = String.valueOf(value);
                    } else if (keyJsonObj.equalsIgnoreCase("")) {
                        Object value = jsonObject.get(rootJsonObj);
                        finalOutput = String.valueOf(value);
                    }
                } else {
                    if (!keyJsonObj.equalsIgnoreCase("")) {
                        Object value = jsonObject.get(keyJsonObj);
                        finalOutput = String.valueOf(value);
                    } else {
                        finalOutput = jsonObject.toString(4);
                    }
                }
                System.out.println();
                System.out.println("------- Response Message -------");
                return finalOutput;
            } else {
                new TellMeWhy("w", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_GETVALUE_HITAPI);
                return finalOutput;
            }
        } catch (Exception e) {
            TellMeWhy.getPrintMsgErrActive(e);
            new TellMeWhy("w", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_GETVALUE_HITAPI);
            return finalOutput;
        }
    }

    /**
     * <b>getResponseMessage</b>
     * used getResponseMessage after call HitApi
     *
     * <br><br>
     *
     * @since 1.4.1
     */
    public static String getResponseMessage() {
        return getResponseMessage("", "");
    }

    /**
     * <b>getResponseMessage</b>
     * used getResponseMessage after call HitApi with specific keyJsonObj
     *
     * <br><br>
     *
     * @param keyJsonObj
     *
     * @since 1.4.1
     */
    public static String getResponseMessage(String keyJsonObj) {
        return getResponseMessage("", keyJsonObj);
    }

    /**
     * <b>setTimeoutConnection</b>
     * used Set Timeout Connection when doing HitApi()
     *
     * <br><br>
     *
     * @param seconds
     *
     * @since 1.4.1
     */
    public static void setTimeoutConnection(int seconds) {
        try {
            timeout = seconds;
        } catch (Exception e) {
            TellMeWhy.getPrintMsgErrActive(e);
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.INVALID_NUMBER);
        }
    }
}