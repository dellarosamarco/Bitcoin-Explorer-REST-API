package com.dellarosamarco.dormantbitcoinseeker.models;

import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinUtilsService;
import org.web3j.crypto.Sign;

import java.math.BigInteger;

public class Wallet {
    private String address;
    private String privateKey;
    private String wif;
    private String publicKey;
    private double balance;
    private double totalReceived;
    private int totalTransactions;

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(double totalReceived) {
        this.totalReceived = totalReceived;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public String getWif() {
        return wif;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey){
        this.privateKey = "";

        this.publicKey = publicKey;

        this.address = BitcoinUtilsService.publicKeyToAddress(publicKey);
    }

    public void setAddress(String address) {
        this.privateKey = "";
        this.address = address;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;

        this.wif = BitcoinUtilsService.hexToWif(privateKey);

        BigInteger pubKey = Sign.publicKeyFromPrivate(new BigInteger(privateKey,16));
        this.publicKey = BitcoinUtilsService.compressPubKey(pubKey);

        this.address = BitcoinUtilsService.publicKeyToAddress(publicKey);
    }
}
