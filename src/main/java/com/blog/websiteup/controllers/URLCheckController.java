package com.blog.websiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLCheckController {

    private final String SITE_UP = "Site is up";
    private final String SITE_DOWN = "Site is down";
    private final String INCORRECT_URL = "URL is Incorrect";

    @GetMapping("/check")
    public String getWebsiteStatus(@RequestParam String url) {

        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            
            int responseCode = conn.getResponseCode()/100;
            System.out.println("Conn response code " + conn.getResponseCode());
            System.out.println("ResponseCode"+responseCode);

            if(responseCode== 2 || responseCode==3){
                returnMessage = SITE_UP;
            }
            else{
                returnMessage =  SITE_DOWN;
            }

        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;

        } catch (IOException e) {
            returnMessage = SITE_DOWN;
        }

        return returnMessage;
    }

}
