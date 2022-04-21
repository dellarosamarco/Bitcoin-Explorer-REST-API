package com.dellarosamarco.dormantbitcoinseeker.models;

import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinUtilsService;
import com.dellarosamarco.dormantbitcoinseeker.service.BlockchainService;
import org.apache.tomcat.util.json.ParseException;
import org.web3j.crypto.Sign;
import java.io.IOException;
import java.math.BigInteger;

public class Wallet {
    private String address;
    private String privateKey;
    private String wif;
    private String publicKey;

    public BlockchainInfoDTO getBlockchainInfo() {
        return blockchainInfo;
    }

    private BlockchainInfoDTO blockchainInfo;

    public String getAddress() {
        return address;
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

    public void setPrivateKey(String privateKey) throws IOException, ParseException {
        this.privateKey = privateKey;

        this.wif = BitcoinUtilsService.hexToWif(privateKey);

        BigInteger pubKey = Sign.publicKeyFromPrivate(new BigInteger(privateKey,16));
        this.publicKey = BitcoinUtilsService.compressPubKey(pubKey);

        this.address = BitcoinUtilsService.publicKeyToAddress(publicKey);

        this.blockchainInfo = BlockchainService.getBalance(this.address).get(this.address);
    }

    public BlockchainInfoDTO getBlockchainInfoDTO() {
        return blockchainInfo;
    }
}
