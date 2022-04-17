package com.dellarosamarco.dormantbitcoinseeker.rest;

import com.dellarosamarco.dormantbitcoinseeker.models.Wallet;
import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinWalletService;
import com.dellarosamarco.dormantbitcoinseeker.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BitcoinWalletResource {

    @Autowired
    BitcoinWalletService bitcoinWalletService;

    @Autowired
    BlockchainService blockchainService;

    @RequestMapping(value = "/randomWallet", method = RequestMethod.GET)
    public Wallet[] randomWallet(@RequestParam(required = false, name = "Total private keys", defaultValue = "1") int total){
        return bitcoinWalletService.randomWallet(total);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String randomWallet() throws IOException {
        return blockchainService.getBalance("151iCA6g6UbVjqKw3rEbmaoT6Adhw4qCL3");
    }
}
