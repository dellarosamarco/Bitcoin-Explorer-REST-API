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
        String privateKey = randomPrivateKey();

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
}


