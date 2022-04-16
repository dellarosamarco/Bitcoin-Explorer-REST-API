package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.stereotype.Service;

@Service
public class BitcoinService {
    public String randomPrivateKey(){
        byte[] bytes = BitcoinUtils.randomBytes(32);
        return BitcoinUtils.bytesToHex(bytes);
    }

    public String randomAddress(){
        return "asd";
    }
}
