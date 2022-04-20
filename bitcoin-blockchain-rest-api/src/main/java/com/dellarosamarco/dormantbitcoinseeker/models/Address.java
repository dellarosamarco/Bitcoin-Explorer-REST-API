package com.dellarosamarco.dormantbitcoinseeker.models;

import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinUtilsService;
import org.web3j.crypto.Sign;
import java.math.BigInteger;

public class Address {
    private String address;
    private String publicKey;
    private String privateKey;
    private String wif;

    public String getPrivateKey() {
        return privateKey;
    }

    public String getAddress() {
        return address;
    }

    public String getWif() {
        return wif;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;

        this.wif = BitcoinUtilsService.hexToWif(privateKey);

        BigInteger pubKey = Sign.publicKeyFromPrivate(new BigInteger(privateKey,16));
        this.publicKey = BitcoinUtilsService.compressPubKey(pubKey);

        this.address = BitcoinUtilsService.publicKeyToAddress(publicKey);
    }

    public void setPublicKey(String publicKey){
        this.privateKey = "";

        this.publicKey = publicKey;

        this.address = BitcoinUtilsService.publicKeyToAddress(publicKey);
    }
}
