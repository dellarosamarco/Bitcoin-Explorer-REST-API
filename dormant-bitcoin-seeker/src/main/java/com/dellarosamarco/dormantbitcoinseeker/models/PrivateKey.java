package com.dellarosamarco.dormantbitcoinseeker.models;

public class PrivateKey {
    private String privateKey;
    private String wif;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getWif() {
        return wif;
    }

    public void setWif(String wif) {
        this.wif = wif;
    }
}