package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.stereotype.Service;

@Service
public class BitcoinService {
    public void randomPrivateKey(){
        int[] bytes = BitcoinUtils.randomBytes(32);

        for(int n=0;n<32;n++){
            System.out.println(bytes[n]);
        }

    }
}
