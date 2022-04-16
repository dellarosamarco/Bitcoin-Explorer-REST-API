package com.dellarosamarco.dormantbitcoinseeker.rest;

import com.dellarosamarco.dormantbitcoinseeker.dto.AddressDTO;
import com.dellarosamarco.dormantbitcoinseeker.dto.PrivateKeyDTO;
import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class BitcoinResource {

    @Autowired
    private BitcoinService bitcoinService;

    @RequestMapping(value = "/randomPrivateKey", method = RequestMethod.GET)
    public PrivateKeyDTO[] randomPrivateKey(@RequestParam(required = false, name = "Total private keys", defaultValue = "1") int total){
        return bitcoinService.randomPrivateKey(total);
    }

    @RequestMapping(value = "/randomAddress", method = RequestMethod.GET)
    public AddressDTO[] randomAddress(@RequestParam(required = false, name = "Total addresses", defaultValue = "1") int total) {
        return bitcoinService.randomAddress(total);
    }

    @RequestMapping(value = "/privateKeyToAddress", method = RequestMethod.POST)
    public AddressDTO[] privateKeyToAddress(@RequestBody() PrivateKeyDTO[] privateKey){
        return bitcoinService.privateKeyToAddress(privateKey);
    }
}


