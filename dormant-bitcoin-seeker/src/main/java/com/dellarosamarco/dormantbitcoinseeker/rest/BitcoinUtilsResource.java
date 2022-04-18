package com.dellarosamarco.dormantbitcoinseeker.rest;

import com.dellarosamarco.dormantbitcoinseeker.models.Address;
import com.dellarosamarco.dormantbitcoinseeker.models.PrivateKey;
import com.dellarosamarco.dormantbitcoinseeker.models.PublicKey;
import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinUtilsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class BitcoinUtilsResource {
    @RequestMapping(value = "/privateKeyToAddress", method = RequestMethod.POST)
    public Address[] privateKeyToWif(@RequestBody() PrivateKey[] privateKey){
        return BitcoinUtilsService.privateKeyToAddress(privateKey);
    }

    @RequestMapping(value = "/privateKeyToPublicKey", method = RequestMethod.GET)
    public PublicKey privateKeyToPublicKey(@RequestParam(name = "privateKey") String privateKey){
        return BitcoinUtilsService.privateKeyToPublicKey(privateKey);
    }

    @RequestMapping(value = "/publicKeyToAddress", method = RequestMethod.GET)
    public String publicKeyToAddress(@RequestParam(name = "publicKey") String publicKey){
        return BitcoinUtilsService.publicKeyToAddress(publicKey);
    }

    @RequestMapping(value = "/wifToPrivateKey", method = RequestMethod.GET)
    public String wifToPrivateKey(@RequestParam(name = "wif") String wif){
        return BitcoinUtilsService.wifToHex(wif);
    }

    @RequestMapping(value = "/privateKeyToWif", method = RequestMethod.GET)
    public String privateKeyToWif(@RequestParam(name = "privateKey") String privateKey){
        return BitcoinUtilsService.hexToWif(privateKey);
    }
}
