package com.manv.snapshot;

import java.util.ArrayList;
import java.util.List;

public class History {
    private final List<CustomStringBuilderSnapshot> history = new ArrayList<>();

    public void saveSnapshot (CustomStringBuilderSnapshot customStringBuilderSnapshot) {
        history.add(customStringBuilderSnapshot);
    }

    public CustomStringBuilderSnapshot getCustomStringBuilderSnapshot (int index) {
        return history.get(index);
    }

    public List <CustomStringBuilderSnapshot> getAllSnapshots () {
        return history;
    }

    public CustomStringBuilderSnapshot undo () {
        if (history.isEmpty()) {
            return null;
        }
        if (history.size() == 1) {
            return history.get(0);
        } else {
            history.remove(history.size() - 1);
            return history.get(history.size() -1);
        }
    }
}
