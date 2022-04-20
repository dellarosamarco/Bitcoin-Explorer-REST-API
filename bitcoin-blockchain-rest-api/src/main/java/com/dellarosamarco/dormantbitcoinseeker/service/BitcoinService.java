package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.models.Address;
import com.dellarosamarco.dormantbitcoinseeker.models.PrivateKey;
import org.springframework.stereotype.Service;

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
}
