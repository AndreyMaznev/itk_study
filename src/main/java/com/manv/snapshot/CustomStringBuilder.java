package com.manv.snapshot;

import java.util.Arrays;


public class CustomStringBuilder {
    char [] values = new char[0];
    int count;
    int capacity;

    public CustomStringBuilder() {
    }

    private CustomStringBuilder customStringBuilderCloneConstructor() {
        CustomStringBuilder clone = new CustomStringBuilder();
        clone.values = this.values;
        clone.count = this.count;
        clone.capacity = this.capacity;
        return clone;
    }

    public CustomStringBuilderSnapshot saveState () {
        return new CustomStringBuilderSnapshot(customStringBuilderCloneConstructor());
    }

    public CustomStringBuilder append (String str){
        if (str == null) {
            return append("null");
        }
        int inputLength = str.length();
        checkCapacityIsEnoughAndEnlarge(inputLength);
        str.getChars(0, inputLength, values, count);
        count += inputLength;
        return this;
    }

    private void checkCapacityIsEnoughAndEnlarge(int input) {
        if (capacity - values.length < input) {
            enlargeValues(input);
        }
    }

    private void enlargeValues(int input) {
        char [] newValues;
        if (capacity == 0) {
            newValues = new char[input];
        } else {
            newValues = new char[(capacity + input) * 2];
        }
        System.arraycopy(values, 0, newValues, 0, values.length);
        values = newValues;
        capacity = newValues.length;
    }

    @Override
    public String toString() {
        char [] onlyFilledValues = Arrays.copyOfRange(values, 0, count);
        return new String(onlyFilledValues);
    }
}
