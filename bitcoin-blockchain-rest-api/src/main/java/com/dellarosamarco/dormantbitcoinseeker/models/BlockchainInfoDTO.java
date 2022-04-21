package com.dellarosamarco.dormantbitcoinseeker.models;

public class BlockchainInfoDTO {
    public double final_balance;
    public int n_tx;
    public double total_received;

    public double getFinal_balance() {
        return final_balance;
    }

    public int getN_tx() {
        return n_tx;
    }

    public double getTotal_received() {
        return total_received;
    }
}
