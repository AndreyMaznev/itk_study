package com.manv;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount implements Comparable<BankAccount> {
    private final UUID uuid;
    private BigDecimal balance;
    private final ReentrantLock lock = new ReentrantLock();
    private boolean locked = false; //флаг для проверки, чтобы акк не остался в блокировке при каких-то ошибках

    public BankAccount(BigDecimal balance) {
        this.uuid = UUID.randomUUID();
        this.balance = balance;
    }

    public UUID getUuid() {
        return uuid;
    }

    public BigDecimal getBalance () {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(BigDecimal amount) {
        lock.lock();
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive.");
        }
        try {
            if (balance.compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient funds.");
            }
            balance = balance.subtract(amount);
            System.out.println("Withdraw of " + amount + " from the account # " + uuid);
        } finally {
            lock.unlock();
        }
    }

    public void deposit(BigDecimal amount) {
        lock.lock();
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        try {
            balance = balance.add(amount);
            System.out.println("Refilling account # " + getUuid() + " to " + amount);
        } finally {
            lock.unlock();
        }
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void lockAccount() {
        lock.lock();
        locked = true;
    }

    public void unlockAccount() {
        lock.unlock();
        locked = false;
    }


    @Override
    public int compareTo(BankAccount other) {
        return this.uuid.compareTo(other.uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
