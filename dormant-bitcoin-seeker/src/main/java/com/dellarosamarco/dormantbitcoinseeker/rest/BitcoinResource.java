package com.dellarosamarco.dormantbitcoinseeker.rest;

import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinService;
import com.dellarosamarco.dormantbitcoinseeker.utils.Base58CheckEncoding;
import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinResource {

    @Autowired
    private BitcoinService bitcoinService;

    @RequestMapping(value = "/randomPrivateKey", method = RequestMethod.GET)
    public String randomPrivateKey(){
        return bitcoinService.randomPrivateKey();
    }

    @RequestMapping(value = "/randomAddress", method = RequestMethod.GET)
    public String randomAddress() {
        return bitcoinService.randomAddress();
    }

    @RequestMapping(value = "/privateKeyToAddress", method = RequestMethod.GET)
    public String privateKeyToAddress(String privateKey){
        return bitcoinService.privateKeyToAddress(privateKey);
    }
}


