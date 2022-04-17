package com.dellarosamarco.dormantbitcoinseeker.models;

import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinUtilsService;
import org.web3j.crypto.Sign;

import java.math.BigInteger;

public class PublicKey {
    private String publicKey;
    private String privateKey;
    private String wif;

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getWif() {
        return wif;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;

        this.wif = BitcoinUtilsService.hexToWif(privateKey);

        BigInteger pubKey = Sign.publicKeyFromPrivate(new BigInteger(privateKey,16));
        this.publicKey = BitcoinUtilsService.compressPubKey(pubKey);
    }
}
