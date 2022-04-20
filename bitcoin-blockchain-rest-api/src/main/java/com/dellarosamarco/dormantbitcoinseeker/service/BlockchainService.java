package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.models.BlockchainInfoDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

@Service
public class BlockchainService {

    private final static String BLOCKCHAIN_URL = "https://blockchain.info/balance?cors=true&active=";

    public static Map<String, BlockchainInfoDTO> getBalance(String[] addresses) throws IOException {
        StringBuilder url = new StringBuilder(BLOCKCHAIN_URL);

        for(String address : addresses){
            url.append(URLEncoder.encode(address + ",", StandardCharsets.UTF_8));
        }

        InputStream input = new URL(url.toString()).openStream();

        return new Gson().fromJson(new InputStreamReader(input, StandardCharsets.UTF_8), new TypeToken<Map<String, BlockchainInfoDTO>>(){}.getType());
    }

    public static Map<String, BlockchainInfoDTO> getBalance(String address) throws IOException {
        InputStream input = new URL(BLOCKCHAIN_URL + URLEncoder.encode(address, StandardCharsets.UTF_8)).openStream();

        return new Gson().fromJson(new InputStreamReader(input, StandardCharsets.UTF_8), new TypeToken<Map<String, BlockchainInfoDTO>>(){}.getType());
    }
}
