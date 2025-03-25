package com.manv.snapshot;


public class CustomStringBuilderSnapshot {
    CustomStringBuilder csb;

    public CustomStringBuilderSnapshot(CustomStringBuilder csb) {
        this.csb = csb;
    }
    public CustomStringBuilder getCsb() {
        return csb;
    }

    @Override
    public String toString() {
        return csb.toString();
    }
}
