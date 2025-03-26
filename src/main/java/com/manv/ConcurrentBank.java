package com.manv;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ConcurrentBank {
    private final ConcurrentSkipListSet<BankAccount> accounts = new ConcurrentSkipListSet<>();
    private final ReentrantReadWriteLock globalLock = new ReentrantReadWriteLock();

    public ConcurrentBank() {
    }

    public BigDecimal getTotalBalance() {
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (BankAccount account : accounts) {
            account.lockAccount();
        }
        try {
            for (BankAccount account : accounts) {
                totalBalance = totalBalance.add(account.getBalance());
                account.unlockAccount();
            }
        } catch (Exception e) {
            for (BankAccount account : accounts) {
                if (account.isLocked()) {
                    account.unlockAccount();
                }
            }
        }
        return totalBalance;
    }


    public void transfer (BankAccount transferFrom, BankAccount transferTo, BigDecimal inputAmount) {
        if (transferFrom == null || transferTo == null) {
            throw new IllegalArgumentException("Incorrect accounts.");
        }
        if (transferFrom.getBalance().compareTo(inputAmount) < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        if (inputAmount == null || inputAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive number.");
        }


        //Для исключения deadlock'ов
        BankAccount firstAccount = transferFrom.getUuid().compareTo(transferTo.getUuid()) < 0 ? transferFrom : transferTo;
        BankAccount secondAccount = transferFrom.getUuid().compareTo(transferTo.getUuid()) < 0 ? transferTo : transferFrom;

        firstAccount.lockAccount();
        secondAccount.lockAccount();

        try {
            transferFrom.withdraw(inputAmount);
            transferTo.deposit(inputAmount);
        } finally {
            secondAccount.unlockAccount();
            firstAccount.unlockAccount();
        }
    }


    public BankAccount createAccount (BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        globalLock.writeLock().lock();
        BankAccount createdBankAccount;
        try {
            createdBankAccount = new BankAccount(balance);
        } finally {
            globalLock.writeLock().unlock();
        }
        accounts.add(createdBankAccount);
        return createdBankAccount;
    }
}
