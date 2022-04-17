package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.models.Address;
import com.dellarosamarco.dormantbitcoinseeker.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitcoinWalletService {

    @Autowired
    public BitcoinService bitcoinService;

    public Wallet[] randomWallet(int total){
        Wallet[] wallets = new Wallet[total];
        Address[] addresses = bitcoinService.randomAddress(total);

        for(int i=0;i<total;i++){
            wallets[i] = new Wallet();
            wallets[i].setAddress(addresses[i].getAddress());
            wallets[i].setPrivateKey(addresses[i].getPrivateKey());
            wallets[i].setBalance(10);
        }

        return wallets;
    }
}
