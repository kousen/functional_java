package refactoring;

import java.util.*;

public class LoopsSortsAndIfs {
    public static void main(String[] args) {
        String[] strings = "this is an array of strings".split(" ");

        List<String> evenLengths = new ArrayList<>();
        for (String s : strings) {
            if (s.length() % 2 == 0) {
                evenLengths.add(s.toUpperCase(Locale.ROOT));
            }
        }

        Collections.sort(evenLengths, new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        for (String s : evenLengths) {
            System.out.println(s);
        }
    }
}