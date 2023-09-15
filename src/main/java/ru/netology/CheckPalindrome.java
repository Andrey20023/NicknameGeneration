package ru.netology;

import java.util.Arrays;

import static ru.netology.Main.*;

public class CheckPalindrome implements Runnable {
    String[] strings;

    CheckPalindrome(String[] strings) {
        this.strings = strings;
    }

    @Override
    public void run() {
        Arrays.stream(strings)
                .sorted()
                .filter(s -> s.equals(new StringBuilder(s).reverse().toString()))
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
