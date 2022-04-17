package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.models.Address;
import com.dellarosamarco.dormantbitcoinseeker.models.PrivateKey;
import com.dellarosamarco.dormantbitcoinseeker.models.PublicKey;
import com.dellarosamarco.dormantbitcoinseeker.utils.Base58;
import com.dellarosamarco.dormantbitcoinseeker.utils.BitcoinUtils;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Sign;

import java.math.BigInteger;
import java.util.Objects;

@Service
public class BitcoinService {
    public PrivateKey[] randomPrivateKey(int total){
        PrivateKey[] privateKeys = new PrivateKey[total];

        for(int i=0;i<total;i++){
            byte[] bytes = BitcoinUtils.randomBytes(32);
            String hex = BitcoinUtils.bytesToHex(bytes);

            PrivateKey privateKey = new PrivateKey();
            privateKey.setPrivateKey(hex);
            privateKey.setWif(BitcoinUtils.hexToWif(hex));
            privateKeys[i] = privateKey;
        }
        return privateKeys;
    }

    public Address[] randomAddress(int total){
        Address[] addresses = new Address[total];
        PrivateKey[] privateKeys = randomPrivateKey(total);

        for(int i=0;i<total;i++){
            String hex = privateKeys[i].getPrivateKey();

            addresses[i] = new Address();
            addresses[i].setPrivateKey(hex);
            addresses[i].setWif(BitcoinUtils.hexToWif(hex));
        }

        return addresses;
    }

    public Address[] privateKeyToAddress(PrivateKey[] privateKeys){
        int total = privateKeys.length;
        Address[] addresses = new Address[total];

        for(int i=0;i<total;i++){
            String hex = privateKeys[i].getPrivateKey();

            addresses[i] = new Address();
            addresses[i].setPrivateKey(hex);
            addresses[i].setWif(BitcoinUtils.hexToWif(hex));
        }

        return addresses;
    }

    public PublicKey privateKeyToPublicKey(String privateKey){
        BigInteger pubKey = Sign.publicKeyFromPrivate(new BigInteger(privateKey,16));

        PublicKey publicKey = new PublicKey();
        publicKey.setPrivateKey(privateKey);
        publicKey.setWif(BitcoinUtils.hexToWif(privateKey));
        publicKey.setPublicKey(BitcoinUtils.compressPubKey(pubKey));

        return publicKey;
    }

    public String publicKeyToAddress(String publicKey){
        // SHA-256 ON PUBLIC KEY
        byte[] publicKeyHashed = BitcoinUtils.sha256(BitcoinUtils.hexToBytes(publicKey));

        // RipeMD160 ON HASHED PUBLIC KEY
        byte[] ripeMD160 = BitcoinUtils.ripeMD160(publicKeyHashed);
        assert ripeMD160 != null;

        // ADD BITCOIN MAIN NET BYTE
        byte[] ripeMD160MainNetwork = new byte[ripeMD160.length + 1];
        ripeMD160MainNetwork[0] = (byte)0x00;

        int length = ripeMD160.length;
        System.arraycopy(ripeMD160, 0, ripeMD160MainNetwork, 1, length);

        // GENERATE CHECKSUM => SHA-256 TWICE
        byte[] hashedMainNet = BitcoinUtils.sha256(ripeMD160MainNetwork);
        byte[] hasedMainNet_ = BitcoinUtils.sha256(hashedMainNet);
        assert hasedMainNet_ != null;

        // ADD CHECKSUM
        byte[] addressBytes = new byte[25];
        System.arraycopy(ripeMD160MainNetwork, 0, addressBytes, 0, ripeMD160MainNetwork.length);
        System.arraycopy(hasedMainNet_, 0, addressBytes, 21, 4);

        // CONVERT TO BASE58
        return Base58.encode(addressBytes);
    }
}
