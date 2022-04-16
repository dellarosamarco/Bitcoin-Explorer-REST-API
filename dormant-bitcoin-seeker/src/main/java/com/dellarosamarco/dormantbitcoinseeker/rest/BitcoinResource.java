package com.dellarosamarco.dormantbitcoinseeker.rest;

import com.dellarosamarco.dormantbitcoinseeker.service.BitcoinService;
import com.dellarosamarco.dormantbitcoinseeker.utils.Base58CheckEncoding;
import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.bitcoinj.core.Base58;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

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
        String privateKey = "0C28FCA386C7A227600B2FE50B7CAE11EC86D3BF1FBE471BE89827E19D72AA1D";

        // EXTENDED KEY
        StringBuilder extended = new StringBuilder((BitcoinUtils.bytesToHex(new byte[]{(byte)0x80})) + privateKey);

        // SHA-256 OF THE EXTENDED KEY
        byte[] sha256extendedBytes = BitcoinUtils.sha256(extended.toString());
        assert sha256extendedBytes != null;
        String sha256extended = BitcoinUtils.bytesToHex(sha256extendedBytes);

        // SHA-256 OF SHA-256 OF THE EXTENDED KEY
        byte[] sha256extendedOfExtendedBytes = BitcoinUtils.sha256(sha256extendedBytes);
        assert sha256extendedOfExtendedBytes != null;
        String sha256extendedOfExtendedString = BitcoinUtils.bytesToHex(sha256extendedOfExtendedBytes);

        // CHECKSUM
        byte[] bytes4 = new byte[4];
        System.arraycopy(sha256extendedOfExtendedBytes, 0, bytes4, 0, 4);
        extended.append(BitcoinUtils.bytesToHex(bytes4));

        // Convert into base58
        return Base58CheckEncoding.convertToBase58("800C28FCA386C7A227600B2FE50B7CAE11EC86D3BF1FBE471BE89827E19D72AA1D507A5B8D");

    }
}


