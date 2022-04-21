package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.models.Address;
import com.dellarosamarco.dormantbitcoinseeker.models.Wallet;
import com.dellarosamarco.dormantbitcoinseeker.models.Wallets;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BitcoinWalletService {

    @Autowired
    public BitcoinService bitcoinService;

    public Wallets randomWallet(int total) throws IOException, ParseException {
        Wallet[] wallets = new Wallet[total];
        Address[] addresses = bitcoinService.randomAddress(total);
        Wallets walletsWrapper = new Wallets();

        for(int i=0;i<total;i++){
            wallets[i] = new Wallet();
            wallets[i].setPrivateKey(addresses[i].getPrivateKey());
        }

        walletsWrapper.setWallets(wallets);

        return walletsWrapper;
    }
}
