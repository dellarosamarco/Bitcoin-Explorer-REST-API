package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.interfaces.ECKey;

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
