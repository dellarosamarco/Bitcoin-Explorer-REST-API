package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.stereotype.Service;

import java.util.HexFormat;

@Service
public class BitcoinService {
    public String randomPrivateKey(){
        byte[] bytes = BitcoinUtils.randomBytes(32);
        return bytesToString(bytes);
    }

    public String bytesToString(byte[] bytes){
        return HexFormat.of().formatHex(bytes);
    }
}
