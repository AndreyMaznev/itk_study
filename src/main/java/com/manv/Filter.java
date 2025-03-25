package com.manv;

@FunctionalInterface
public interface Filter <T> {
    T apply(T input);
}
