package com.dellarosamarco.dormantbitcoinseeker.models;

import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinUtilsService;

public class PrivateKey {
    private String privateKey;
    private String wif;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
        this.wif = BitcoinUtilsService.hexToWif(privateKey);
    }

    public String getWif() {
        return wif;
    }
}
