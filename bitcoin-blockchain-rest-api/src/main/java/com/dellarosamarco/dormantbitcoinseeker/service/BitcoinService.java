package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.models.Address;
import com.dellarosamarco.dormantbitcoinseeker.models.PrivateKey;
import org.springframework.stereotype.Service;
import org.web3j.crypto.MnemonicUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

@Service
public class BitcoinService {
    public PrivateKey[] randomPrivateKey(int total){
        PrivateKey[] privateKeys = new PrivateKey[total];

        for(int i=0;i<total;i++){
            byte[] bytes = BitcoinUtilsService.randomBytes(32);
            String hex = BitcoinUtilsService.bytesToHex(bytes);

            PrivateKey privateKey = new PrivateKey();
            privateKey.setPrivateKey(hex);
            privateKeys[i] = privateKey;
        }
        return privateKeys;
    }

    public Address[] randomAddress(int total){
        Address[] addresses = new Address[total];
        PrivateKey[] privateKeys = randomPrivateKey(total);

        for(int i=0;i<total;i++){
            String privateKey = privateKeys[i].getPrivateKey();

            addresses[i] = new Address();
            addresses[i].setPrivateKey(privateKey);
        }

        return addresses;
    }

    public String[] randomMnemonic(int total) throws URISyntaxException, IOException {

        URL resource = getClass().getClassLoader().getResource("static/english.txt");
        assert resource != null;
        File file = new File(resource.toURI());
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] words = new String[2048];
        int n = 0;
        String line;

        while((line=bufferedReader.readLine())!=null)
        {
            words[n] = line;
            n ++;
        }
        fileReader.close();

        String[] mnemonics = new String[total];

        for(int x=0;x<total;x++){
            StringBuilder mnemonic = new StringBuilder();

            Random random = new Random();
            for(int i=0;i<12;i++){
                mnemonic.append(words[random.nextInt(words.length)]).append(" ");
            }

            mnemonics[x] = mnemonic.toString();

        }

        return mnemonics;
    }
}
