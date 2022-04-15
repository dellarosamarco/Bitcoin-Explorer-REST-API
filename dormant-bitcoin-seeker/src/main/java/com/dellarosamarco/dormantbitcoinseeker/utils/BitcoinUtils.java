package com.dellarosamarco.dormantbitcoinseeker.utils;

public class BitcoinUtils
{
    public static int[] randomBytes(int length){
        int[] bytes = new int[length];

        for(int n=0;n<length;n++) {
            bytes[n] = (int) (Math.random() * 255);
        }

        return bytes;
    }
}
