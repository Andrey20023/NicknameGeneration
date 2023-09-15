package ru.netology;

import java.util.Arrays;

import static ru.netology.Main.*;

public class CheckSameLetters implements Runnable {
    String[] strings;

    CheckSameLetters(String[] strings) {
        this.strings = strings;
    }

    @Override
    public void run() {
        Arrays.stream(strings)
                .filter(s -> {
                    if (s == null || s.length() == 0||s.equals(new StringBuilder(s).reverse().toString())) {
                        return false;
                    }
                    char c = s.charAt(0);
                    for (int i = 1; i < s.length(); i++) {
                        if (s.charAt(i) != c) {
                            return false;
                        }
                    }
                    return true;
                })
                .forEach(s -> {
                    System.out.println(s);
                    if (s.length() == 3)
                        counterThreeLetter.incrementAndGet();
                    if (s.length() == 4)
                        counterFourLetter.incrementAndGet();
                    if (s.length() == 5)
                        counterFiveLetter.incrementAndGet();
                });
    }
}
