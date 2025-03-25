package com.manv;


import com.manv.snapshot.CustomStringBuilder;
import com.manv.snapshot.CustomStringBuilderSnapshot;
import com.manv.snapshot.History;

public class Main {
    public static void main(String[] args) {
        CustomStringBuilder csb = new CustomStringBuilder();
        History history = new History();
        csb.append("This is the root of the StringBuilder.");
        System.out.println(csb);
        System.out.println("------------------------------");
        history.saveSnapshot(csb.saveState());
        System.out.println("First (root) snapshot successfully made.");

        csb.append(" + the second one");
        history.saveSnapshot(csb.saveState());
        System.out.println("Second snapshot successfully made.");
        csb.append(" + the third one");

        history.saveSnapshot(csb.saveState());
        System.out.println("Third snapshot successfully made.");
        csb.append(" + the fourth one");

        history.saveSnapshot(csb.saveState());
        System.out.println("Fourth snapshot successfully made.");
        System.out.println("------------------------------");
        System.out.println("Getting all the snapshots :");
        System.out.println("------------------------------");
        for (CustomStringBuilderSnapshot snapshot : history.getAllSnapshots()) {
            System.out.println("Getting snapshot with index: " + history.getAllSnapshots().indexOf(snapshot));
            System.out.println("Snapshot: " + snapshot.getCsb() );
            System.out.println("------------------------------");
        }
        System.out.println("Here we are testing \"undo()\" method with more than 5 times: ");
        System.out.println("returning to previous snapshot: " +  history.undo());
        System.out.println("returning to previous snapshot: " +  history.undo());
        System.out.println("returning to previous snapshot: " +  history.undo());
        System.out.println("returning to previous snapshot: " +  history.undo());
        System.out.println("returning to previous snapshot: " +  history.undo());
        System.out.println("returning to previous snapshot: " +  history.undo());
    }
}