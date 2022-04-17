package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.dto.AddressDTO;
import com.dellarosamarco.dormantbitcoinseeker.dto.PrivateKeyDTO;
import com.dellarosamarco.dormantbitcoinseeker.utils.Base58;
import com.dellarosamarco.dormantbitcoinseeker.utils.Base58CheckEncoding;
import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class BitcoinService {
    public PrivateKeyDTO[] randomPrivateKey(int total){
        PrivateKeyDTO[] privateKeys = new PrivateKeyDTO[total];

        for(int i=0;i<total;i++){
            byte[] bytes = BitcoinUtils.randomBytes(32);
            String hex = BitcoinUtils.bytesToHex(bytes);

            PrivateKeyDTO privateKey = new PrivateKeyDTO();
            privateKey.setPrivateKey(hex);
            privateKey.setWif(hexToWif(hex));
            privateKeys[i] = privateKey;
        }
        return privateKeys;
    }

    public AddressDTO[] randomAddress(int total){
        AddressDTO[] addresses = new AddressDTO[total];
        PrivateKeyDTO[] privateKeys = randomPrivateKey(total);

        for(int i=0;i<total;i++){
            String hex = privateKeys[i].getPrivateKey();

            addresses[i] = new AddressDTO();
            addresses[i].setPrivateKey(hex);
            addresses[i].setWif(hex);
        }

        return addresses;
    }

    public AddressDTO[] privateKeyToAddress(PrivateKeyDTO[] privateKeys){
        int total = privateKeys.length;
        AddressDTO[] addresses = new AddressDTO[total];

        for(int i=0;i<total;i++){
            String hex = privateKeys[i].getPrivateKey();

            addresses[i] = new AddressDTO();
            addresses[i].setPrivateKey(hex);
            addresses[i].setWif(hexToWif(hex));
        }

        return addresses;
    }

    public String hexToWif(String privateKey){
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

    public String wifToHex(String wif){
        byte[] decodedWif = Base58.decode(wif);
        decodedWif = Arrays.copyOfRange(decodedWif, 0, decodedWif.length-4);
        return BitcoinUtils.bytesToHex(decodedWif);
    }

    public String privateKeyToPublicKey(String privateKey){
        return "a";
    }
}
