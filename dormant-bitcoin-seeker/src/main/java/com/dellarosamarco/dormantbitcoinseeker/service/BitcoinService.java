package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.models.Address;
import com.dellarosamarco.dormantbitcoinseeker.models.PrivateKey;
import com.dellarosamarco.dormantbitcoinseeker.models.PublicKey;
import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Sign;

import java.math.BigInteger;

@Service
public class BitcoinService {
    public PrivateKey[] randomPrivateKey(int total){
        PrivateKey[] privateKeys = new PrivateKey[total];

        for(int i=0;i<total;i++){
            byte[] bytes = BitcoinUtils.randomBytes(32);
            String hex = BitcoinUtils.bytesToHex(bytes);

            PrivateKey privateKey = new PrivateKey();
            privateKey.setPrivateKey(hex);
            privateKey.setWif(BitcoinUtils.hexToWif(hex));
            privateKeys[i] = privateKey;
        }
        return privateKeys;
    }

    public Address[] randomAddress(int total){
        Address[] addresses = new Address[total];
        PrivateKey[] privateKeys = randomPrivateKey(total);

        for(int i=0;i<total;i++){
            String hex = privateKeys[i].getPrivateKey();

            addresses[i] = new Address();
            addresses[i].setPrivateKey(hex);
            addresses[i].setWif(BitcoinUtils.hexToWif(hex));
        }

        return addresses;
    }

    public Address[] privateKeyToAddress(PrivateKey[] privateKeys){
        int total = privateKeys.length;
        Address[] addresses = new Address[total];

        for(int i=0;i<total;i++){
            String hex = privateKeys[i].getPrivateKey();

            addresses[i] = new Address();
            addresses[i].setPrivateKey(hex);
            addresses[i].setWif(BitcoinUtils.hexToWif(hex));
        }

        return addresses;
    }

    public PublicKey privateKeyToPublicKey(String privateKey){
        BigInteger pubKey = Sign.publicKeyFromPrivate(new BigInteger(privateKey,16));

        PublicKey publicKey = new PublicKey();
        publicKey.setPrivateKey(privateKey);
        publicKey.setWif(BitcoinUtils.hexToWif(privateKey));
        publicKey.setPublicKey(BitcoinUtils.compressPubKey(pubKey));

        return publicKey;
    }
}
