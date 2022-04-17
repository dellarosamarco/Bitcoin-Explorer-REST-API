package com.dellarosamarco.dormantbitcoinseeker.service;

import com.dellarosamarco.dormantbitcoinseeker.models.Address;
import com.dellarosamarco.dormantbitcoinseeker.models.PrivateKey;
import com.dellarosamarco.dormantbitcoinseeker.models.PublicKey;
import com.dellarosamarco.dormantbitcoinseeker.utils.Base58;
import com.dellarosamarco.dormantbitcoinseeker.utils.Base58CheckEncoding;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class BitcoinUtilsService
{
    public static byte[] randomBytes(final int length){
        byte[] bytes = new byte[length];

        for(int n=0;n<length;n++) {
            bytes[n] = (byte)(Math.random() * 255);
        }

        return bytes;
    }

    public static String bytesToHex(final byte[] bytes){
        StringBuilder hexString = new StringBuilder(2 * bytes.length);

        for (byte _byte : bytes) {
            String hex = Integer.toHexString(0xff & _byte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }

    public static byte[] sha256(byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(data);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] ripeMD160(byte[] data){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("RipeMD160");
            messageDigest.update(data);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String hexToWif(String privateKey){
        // EXTENDED KEY
        StringBuilder extended = new StringBuilder((BitcoinUtilsService.bytesToHex(new byte[]{(byte)0x80})) + privateKey);

        // SHA-256 OF THE EXTENDED KEY
        byte[] sha256extendedBytes = BitcoinUtilsService.sha256(BitcoinUtilsService.hexToBytes(extended.toString()));
        assert sha256extendedBytes != null;

        // SHA-256 OF SHA-256 OF THE EXTENDED KEY
        byte[] sha256extendedOfExtendedBytes = BitcoinUtilsService.sha256(sha256extendedBytes);
        assert sha256extendedOfExtendedBytes != null;
        String sha256extendedOfExtendedString = BitcoinUtilsService.bytesToHex(sha256extendedOfExtendedBytes);

        // CHECKSUM
        extended.append(sha256extendedOfExtendedString, 0, 8);

        // Convert into base58
        return Base58CheckEncoding.convertToBase58(extended.toString());
    }

    public static String publicKeyToAddress(String publicKey){
        // SHA-256 ON PUBLIC KEY
        byte[] publicKeyHashed = BitcoinUtilsService.sha256(BitcoinUtilsService.hexToBytes(publicKey));

        // RipeMD160 ON HASHED PUBLIC KEY
        byte[] ripeMD160 = BitcoinUtilsService.ripeMD160(publicKeyHashed);
        assert ripeMD160 != null;

        // ADD BITCOIN MAIN NET BYTE
        byte[] ripeMD160MainNetwork = new byte[ripeMD160.length + 1];
        ripeMD160MainNetwork[0] = (byte)0x00;

        int length = ripeMD160.length;
        System.arraycopy(ripeMD160, 0, ripeMD160MainNetwork, 1, length);

        // GENERATE CHECKSUM => SHA-256 TWICE
        byte[] hashedMainNet = BitcoinUtilsService.sha256(ripeMD160MainNetwork);
        byte[] hashedMainNet_ = BitcoinUtilsService.sha256(hashedMainNet);
        assert hashedMainNet_ != null;

        // ADD CHECKSUM
        byte[] addressBytes = new byte[25];
        System.arraycopy(ripeMD160MainNetwork, 0, addressBytes, 0, ripeMD160MainNetwork.length);
        System.arraycopy(hashedMainNet_, 0, addressBytes, 21, 4);

        // CONVERT TO BASE58
        return Base58.encode(addressBytes);
    }

    public static String wifToHex(String wif){
        byte[] decodedWif = Base58.decode(wif);
        decodedWif = Arrays.copyOfRange(decodedWif, 1, decodedWif.length-4);
        return BitcoinUtilsService.bytesToHex(decodedWif);
    }

    public static String compressPubKey(BigInteger pubKey) {
        String pubKeyYPrefix = pubKey.testBit(0) ? "03" : "02";
        String pubKeyHex = pubKey.toString(16);
        String pubKeyX = pubKeyHex.substring(0, 64);
        return pubKeyYPrefix + pubKeyX;
    }

    public static Address[] privateKeyToAddress(PrivateKey[] privateKeys){
        int total = privateKeys.length;
        Address[] addresses = new Address[total];

        for(int i=0;i<total;i++){
            String hex = privateKeys[i].getPrivateKey();

            addresses[i] = new Address();
            addresses[i].setPrivateKey(hex);
        }

        return addresses;
    }

    public static PublicKey privateKeyToPublicKey(String privateKey){
        PublicKey publicKey = new PublicKey();
        publicKey.setPrivateKey(privateKey);

        return publicKey;
    }
}
