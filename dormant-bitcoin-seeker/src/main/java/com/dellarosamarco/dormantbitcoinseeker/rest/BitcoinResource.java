package com.dellarosamarco.dormantbitcoinseeker.rest;

import com.dellarosamarco.dormantbitcoinseeker.models.Address;
import com.dellarosamarco.dormantbitcoinseeker.models.PrivateKey;
import com.dellarosamarco.dormantbitcoinseeker.models.PublicKey;
import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinService;
import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BitcoinResource {

    @Autowired
    private BitcoinService bitcoinService;

    @RequestMapping(value = "/randomPrivateKey", method = RequestMethod.GET)
    public PrivateKey[] randomPrivateKey(@RequestParam(required = false, name = "Total private keys", defaultValue = "1") int total){
        return bitcoinService.randomPrivateKey(total);
    }

    @RequestMapping(value = "/randomAddress", method = RequestMethod.GET)
    public Address[] randomAddress(@RequestParam(required = false, name = "Total addresses", defaultValue = "1") int total) {
        return bitcoinService.randomAddress(total);
    }

    @RequestMapping(value = "/privateKeyToAddress", method = RequestMethod.POST)
    public Address[] privateKeyToWif(@RequestBody() PrivateKey[] privateKey){
        return bitcoinService.privateKeyToAddress(privateKey);
    }

    @RequestMapping(value = "/privateKeyToPublicKey", method = RequestMethod.GET)
    public PublicKey privateKeyToPublicKey(@RequestParam(name = "Private Key") String privateKey){
        return bitcoinService.privateKeyToPublicKey(privateKey);
    }

    @RequestMapping(value = "wifToPrivateKey", method = RequestMethod.GET)
    public String wifToPrivateKey(@RequestParam(name = "WIF") String wif){
        return BitcoinUtils.wifToHex(wif);
    }

    @RequestMapping(value = "privateKeyToWif", method = RequestMethod.GET)
    public String privateKeyToWif(@RequestParam(name = "Private key") String privateKey){
        return BitcoinUtils.hexToWif(privateKey);
    }
}


