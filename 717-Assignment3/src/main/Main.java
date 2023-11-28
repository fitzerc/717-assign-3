package main;

import java.util.Scanner;

public class Main {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final int SHIFT = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Introduction
        System.out.println("Welcome to the CSCI717 Encryption Machine Construction");
        System.out.println("The program lets you encrypt a message");
        System.out.println("with a key for your recipient to decrypt!");
        
        // Enter key and encrypt it
        System.out.print("Enter key: ");
        String key = scanner.next();
        String encryptedKey = encryptWord(key);
        System.out.println("\"" + key + "\" has been encrypted to: " + encryptedKey);
        

        // Enter the number of words in the message
        System.out.print("\nHow many words is your message?: ");
        int wordCount = scanner.nextInt();
        
        // Encrypt each word in the message
        for (int i = 0; i < wordCount; i++) {
            System.out.print("Next word: ");
            String word = scanner.next();
            String encryptedWord = encryptWord(word);
            System.out.println("\"" + word + "\" has been encrypted to: " + encryptedWord);
        }
        
        // Close the scanner
        scanner.close();
        
        System.out.println("\nMessage fully encrypted. Happy secret messaging!");
    }

    // Encrypt a single letter
    public static char encryptLetter(char letter) {
        int index = ALPHABET.indexOf(letter);
        if (index == -1) {
            // Letter not found in the alphabet
            return letter;
        }
        int encryptedIndex = (index + SHIFT) % ALPHABET.length();
        return ALPHABET.charAt(encryptedIndex);
    }

    // Encrypt a single word
    public static String encryptWord(String word) {
        StringBuilder encryptedWord = new StringBuilder();
        for (char letter : word.toCharArray()) {
            char encryptedLetter = encryptLetter(letter);
            encryptedWord.append(encryptedLetter);
        }
        return encryptedWord.toString();
    }
}
