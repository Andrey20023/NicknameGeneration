package ru.netology;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    final public static AtomicInteger counterThreeLetter = new AtomicInteger(0);
    final public static AtomicInteger counterFourLetter = new AtomicInteger(0);
    final public static AtomicInteger counterFiveLetter = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        Thread checkPalindrome = new Thread(new CheckPalindrome(texts));
        Thread checkSameLetters = new Thread(new CheckSameLetters(texts));
        Thread checkLettersIncreasing = new Thread(new CheckLettersIncreasing(texts));
        checkPalindrome.start();
        checkSameLetters.start();
        checkLettersIncreasing.start();
        try {
            checkPalindrome.join();
            checkSameLetters.join();
            checkLettersIncreasing.join();
        } catch (InterruptedException e) {
            System.out.println("Один из потоков был прерван  " + e);
        }

        System.out.printf("Красивых слов с длиной 3: %s шт\n" +
                        "Красивых слов с длиной 4: %s шт\n" +
                        "Красивых слов с длиной 5: %s  шт",
                counterThreeLetter, counterFourLetter, counterFiveLetter);
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}