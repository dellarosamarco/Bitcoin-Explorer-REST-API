package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.dto.AddressDTO;
import com.dellarosamarco.dormantbitcoinseeker.dto.PrivateKeyDTO;
import com.dellarosamarco.dormantbitcoinseeker.utils.Base58CheckEncoding;
import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.stereotype.Service;

@Service
public class BitcoinService {
    public PrivateKeyDTO[] randomPrivateKey(int total){
        PrivateKeyDTO[] privateKeys = new PrivateKeyDTO[total];

        for(int i=0;i<total;i++){
            byte[] bytes = BitcoinUtils.randomBytes(32);
            PrivateKeyDTO privateKey = new PrivateKeyDTO();
            privateKey.setPrivateKey(BitcoinUtils.bytesToHex(bytes));
            privateKeys[i] = privateKey;
        }
        return privateKeys;
    }

    public AddressDTO[] randomAddress(int total){
        AddressDTO[] addresses = new AddressDTO[total];
        PrivateKeyDTO[] privateKeys = randomPrivateKey(total);

        for(int i=0;i<total;i++){
            addresses[i] = new AddressDTO();
            addresses[i].setAddress(generateAddress(privateKeys[i].getPrivateKey()));
            addresses[i].setPrivateKey(privateKeys[i].getPrivateKey());
        }

        return addresses;
    }

    public AddressDTO[] privateKeyToAddress(PrivateKeyDTO[] privateKeys){
        int total = privateKeys.length;
        AddressDTO[] addresses = new AddressDTO[total];

        for(int i=0;i<total;i++){
            addresses[i] = new AddressDTO();
            addresses[i].setAddress(generateAddress(privateKeys[i].getPrivateKey()));
            addresses[i].setPrivateKey(privateKeys[i].getPrivateKey());
        }

        return addresses;
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
}
