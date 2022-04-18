package com.dellarosamarco.dormantbitcoinseeker.rest;

import com.dellarosamarco.dormantbitcoinseeker.models.BlockchainInfoDTO;
import com.dellarosamarco.dormantbitcoinseeker.models.Wallet;
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
    public Wallet[] randomWallet(@RequestParam(required = false, name = "totalWallet", defaultValue = "1") int total) throws IOException, ParseException {
        return bitcoinWalletService.randomWallet(total);
    }

    @RequestMapping(value = "/getBalance", method = RequestMethod.GET)
    public @ResponseBody Map<String, BlockchainInfoDTO> getBalance(@RequestParam(name = "address") String address) throws IOException, ParseException {
        return BlockchainService.getBalance(address);
    }
}
