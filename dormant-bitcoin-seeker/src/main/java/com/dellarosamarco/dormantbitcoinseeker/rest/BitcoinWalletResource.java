package com.dellarosamarco.dormantbitcoinseeker.rest;

import com.dellarosamarco.dormantbitcoinseeker.models.Wallet;
import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinWalletResource {

    @Autowired
    BitcoinWalletService bitcoinWalletService;

    @RequestMapping(value = "/randomWallet", method = RequestMethod.GET)
    public Wallet[] randomWallet(@RequestParam(required = false, name = "Total private keys", defaultValue = "1") int total){
        return bitcoinWalletService.randomWallet(total);
    }
}
