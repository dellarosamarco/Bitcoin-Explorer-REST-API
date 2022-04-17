package com.dellarosamarco.dormantbitcoinseeker.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class BlockchainService {

    private final String BLOCKCHAIN_URL = "https://blockchain.info/balance?cors=true&active=";

    public String getBalance(String address) throws IOException {
        URL url = new URL(BLOCKCHAIN_URL + address + ",1CrxjhkEsizXk9Zqj8sEis8GtXkVqxCVHn," + "151iCA6g6UbVjqKw3rEbmaoT6Adhw4qCL3");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String tmp;
        StringBuilder response = new StringBuilder();

        while((tmp = in.readLine()) != null){
            response.append(tmp);
        }

        in.close();
        return response.toString();
    }

    public String addParam(String key, String value, String url) throws UnsupportedEncodingException {
        return url + String.format("key=%s", URLEncoder.encode(key, value));
    }
}
