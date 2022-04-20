package com.dellarosamarco.dormantbitcoinseeker.rest;

import com.dellarosamarco.dormantbitcoinseeker.models.Address;
import com.dellarosamarco.dormantbitcoinseeker.models.PrivateKey;
import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinService;
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
}


