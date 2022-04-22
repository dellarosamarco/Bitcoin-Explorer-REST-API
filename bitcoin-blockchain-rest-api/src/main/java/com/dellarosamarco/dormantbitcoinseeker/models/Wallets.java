package com.dellarosamarco.dormantbitcoinseeker.models;

public class Wallets{
    private double totalBalance;
    private int totalTransactions;
    private double totalReceived;

    private Wallet[] wallets;

    public Wallet[] getWallets() {
        return wallets;
    }

    public void setWallets(Wallet[] wallets) {
        this.wallets = wallets;

        for (Wallet wallet : wallets) {
            BlockchainInfoDTO blockchainInfoDTO = wallet.getBlockchainInfo();
            totalBalance += blockchainInfoDTO.getFinal_balance();
            totalTransactions += blockchainInfoDTO.getN_tx();
            totalReceived += blockchainInfoDTO.getTotal_received();
        }
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public double getTotalReceived() {
        return totalReceived;
    }
}