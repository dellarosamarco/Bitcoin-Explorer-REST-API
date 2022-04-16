package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.utils.Base58CheckEncoding;
import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.stereotype.Service;

@Service
public class BitcoinService {
    public String randomPrivateKey(){
        byte[] bytes = BitcoinUtils.randomBytes(32);
        return BitcoinUtils.bytesToHex(bytes);
    }

    public String randomAddress(){
        return generateAddress(randomPrivateKey());
    }

    public String generateAddress(String privateKey){
        // EXTENDED KEY
        StringBuilder extended = new StringBuilder((BitcoinUtils.bytesToHex(new byte[]{(byte)0x80})) + privateKey);

        // SHA-256 OF THE EXTENDED KEY
        byte[] sha256extendedBytes = BitcoinUtils.sha256(BitcoinUtils.hexToBytes(extended.toString()));
        assert sha256extendedBytes != null;

        // SHA-256 OF SHA-256 OF THE EXTENDED KEY
        byte[] sha256extendedOfExtendedBytes = BitcoinUtils.sha256(sha256extendedBytes);
        assert sha256extendedOfExtendedBytes != null;
        String sha256extendedOfExtendedString = BitcoinUtils.bytesToHex(sha256extendedOfExtendedBytes);

        // CHECKSUM
        extended.append(sha256extendedOfExtendedString, 0, 8);

        // Convert into base58
        return Base58CheckEncoding.convertToBase58(extended.toString());
    }

    public String privateKeyToAddress(String privateKey){
        return generateAddress(privateKey);
    }
}
