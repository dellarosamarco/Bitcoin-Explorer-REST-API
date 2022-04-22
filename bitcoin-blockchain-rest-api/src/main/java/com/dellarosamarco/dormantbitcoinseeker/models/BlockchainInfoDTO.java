package com.dellarosamarco.dormantbitcoinseeker.models;

public class BlockchainInfoDTO {
    private double final_balance;
    private int n_tx;
    private double total_received;
    private String privateKey;


    public double getFinal_balance() {
        return final_balance;
    }

    public int getN_tx() {
        return n_tx;
    }

    public double getTotal_received() {
        return total_received;
    }

    public void setFinal_balance(double final_balance) {
        this.final_balance = final_balance;
    }

    public void setN_tx(int n_tx) {
        this.n_tx = n_tx;
    }

    public void setTotal_received(double total_received) {
        this.total_received = total_received;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
