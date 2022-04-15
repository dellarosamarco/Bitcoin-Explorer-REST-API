package com.dellarosamarco.dormantbitcoinseeker.utils;

import java.util.HexFormat;

public class BitcoinUtils
{
    public static byte[] randomBytes(int length){
        byte[] bytes = new byte[length];

        for(int n=0;n<length;n++) {
            bytes[n] = (byte)(Math.random() * 255);
        }

        return bytes;
    }
}
