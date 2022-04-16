package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.dto.AddressDTO;
import com.dellarosamarco.dormantbitcoinseeker.dto.WalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitcoinWalletService {

    @Autowired
    public BitcoinService bitcoinService;

    public WalletDTO[] randomWallet(int total){
        WalletDTO[] wallets = new WalletDTO[total];
        AddressDTO[] addresses = bitcoinService.randomAddress(total);

        for(int i=0;i<total;i++){
            wallets[i] = new WalletDTO();
            wallets[i].setAddress(addresses[i].getAddress());
            wallets[i].setPrivateKey(addresses[i].getPrivateKey());
            wallets[i].setBalance(10);
        }

        return wallets;
    }
}
