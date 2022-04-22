package com.dellarosamarco.dormantbitcoinseeker.rest;

import com.dellarosamarco.dormantbitcoinseeker.models.BlockchainInfoDTO;
import com.dellarosamarco.dormantbitcoinseeker.models.Wallet;
import com.dellarosamarco.dormantbitcoinseeker.models.Wallets;
import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinWalletService;
import com.dellarosamarco.dormantbitcoinseeker.service.BlockchainService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class BitcoinWalletResource {

    @Autowired
    BitcoinWalletService bitcoinWalletService;

    @RequestMapping(value = "/randomWallet", method = RequestMethod.GET)
    public Wallets randomWallet(@RequestParam(required = false, name = "totalWallet", defaultValue = "1") int total) throws IOException, ParseException {
        return bitcoinWalletService.randomWallet(total);
    }

    @RequestMapping(value = "/getWalletFromPrivateKey", method = RequestMethod.GET)
    public Wallet getWalletFromPrivateKey(@RequestParam() String privateKey) throws IOException, ParseException {
        return bitcoinWalletService.getWalletFromPrivateKey(privateKey);
    }

    @RequestMapping(value = "/getBalanceByAddress", method = RequestMethod.GET)
    public @ResponseBody Map<String, BlockchainInfoDTO> getBalanceByAddress(@RequestParam(name = "address") String[] address) throws IOException {
        return BlockchainService.getBalanceByAddress(address);
    }

    @RequestMapping(value = "/getBalanceByPrivateKey", method = RequestMethod.GET)
    public @ResponseBody Map<String, BlockchainInfoDTO> getBalanceByPrivateKey(@RequestParam(name = "privateKey") String[] privateKeys) throws IOException {
        return BlockchainService.getBalanceByPrivateKey(privateKeys);
    }
}
