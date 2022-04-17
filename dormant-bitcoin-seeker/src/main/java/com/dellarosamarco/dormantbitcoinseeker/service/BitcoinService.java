package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.dto.AddressDTO;
import com.dellarosamarco.dormantbitcoinseeker.dto.PrivateKeyDTO;
import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Sign;

import java.math.BigInteger;

@Service
public class BitcoinService {
    public PrivateKeyDTO[] randomPrivateKey(int total){
        PrivateKeyDTO[] privateKeys = new PrivateKeyDTO[total];

        for(int i=0;i<total;i++){
            byte[] bytes = BitcoinUtils.randomBytes(32);
            String hex = BitcoinUtils.bytesToHex(bytes);

            PrivateKeyDTO privateKey = new PrivateKeyDTO();
            privateKey.setPrivateKey(hex);
            privateKey.setWif(BitcoinUtils.hexToWif(hex));
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
            addresses[i].setWif(BitcoinUtils.hexToWif(hex));
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
            addresses[i].setWif(BitcoinUtils.hexToWif(hex));
        }

        return addresses;
    }

    public String privateKeyToPublicKey(String privateKey){
        BigInteger pubKey = Sign.publicKeyFromPrivate(new BigInteger(privateKey,16));
        return BitcoinUtils.compressPubKey(pubKey);
    }
}
