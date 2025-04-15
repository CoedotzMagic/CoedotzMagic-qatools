package com.coedotzmagic.qatools_sample;

import com.coedotzmagic.qatools.util.WebServices;

/*
 * write by Coedotz
 * 11-04-2025
 *
 * only for testing api use.
 */

public class TestCallAPI {

    public static void main(String[] args) {
        //String apiUrl = "https://catfact.ninja/fact";
        //String apiUrl = "https://api.agify.io/?name=meelad";
        String apiUrl = "https://api.example.com/data";
        String method = "GET";
        String jsonPayload = """
            {
                             "name": "Coedotz",
                             "job": "Punkrockstar"
                         }
        """;

        WebServices.SetAuthApiIdKey("API-0c5a1011-4193-4edf-acb8-ad11244d3d51", "f936d977ea114fd4951afdc09e896bf2");
        //WebServices.SetAuthBaerer("abc123def456gh789ijklmnopqrstuv");
        WebServices.HitApi(apiUrl, method, jsonPayload);
        //System.out.println(WebServices.getResponseCode());
        System.out.println(WebServices.getResponseMessage());
        //System.out.println(WebServices.getResponseMessage("name"));
    }
}
