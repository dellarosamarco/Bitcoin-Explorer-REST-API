package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.models.Address;
import com.dellarosamarco.dormantbitcoinseeker.models.BlockchainInfoDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Service
public class BlockchainService {

    private final static String BLOCKCHAIN_URL = "https://blockchain.info/balance?cors=true&active=";

    public static Map<String, BlockchainInfoDTO> getBalanceByAddress(String[] addresses) throws IOException {
        StringBuilder url = new StringBuilder(BLOCKCHAIN_URL);

        for(String address : addresses){
            url.append(URLEncoder.encode(address + ",", StandardCharsets.UTF_8));
        }

        InputStream input = new URL(url.toString()).openStream();

        return new Gson().fromJson(new InputStreamReader(input, StandardCharsets.UTF_8), new TypeToken<Map<String, BlockchainInfoDTO>>(){}.getType());
    }

    public static Map<String, BlockchainInfoDTO> getBalanceByAddress(String address) throws IOException {
        InputStream input = new URL(BLOCKCHAIN_URL + URLEncoder.encode(address, StandardCharsets.UTF_8)).openStream();

        return new Gson().fromJson(new InputStreamReader(input, StandardCharsets.UTF_8), new TypeToken<Map<String, BlockchainInfoDTO>>(){}.getType());
    }

    public static Map<String, BlockchainInfoDTO> getBalanceByPrivateKey(String[] privateKeys) throws IOException {
        StringBuilder url = new StringBuilder(BLOCKCHAIN_URL);

        Address[] addresses = BitcoinUtilsService.privateKeyToAddress(privateKeys);

        for(Address address : addresses){
            url.append(URLEncoder.encode(address.getAddress() + ",", StandardCharsets.UTF_8));
        }

        InputStream input = new URL(url.toString()).openStream();

        Map<String, BlockchainInfoDTO> response =  new Gson().fromJson(new InputStreamReader(input, StandardCharsets.UTF_8), new TypeToken<Map<String, BlockchainInfoDTO>>(){}.getType());

        Object[] _privateKeys = Arrays.stream(privateKeys).distinct().toArray();

        final int[] index = {0};
        response.forEach((key, value) -> {
            response.get(key).setPrivateKey(_privateKeys[index[0]].toString());
            index[0] += 1;
        });



        return response;
    }
}
