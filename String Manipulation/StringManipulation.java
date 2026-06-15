import java.util.*;

public class StringManipulation {

    // ── 1. Palindrome Check ──
    static boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String rev = new StringBuilder(s).reverse().toString();
        return s.equals(rev);
    }

    // ── 2. Anagram Check ──
    static boolean isAnagram(String a, String b) {
        char[] ca = a.toLowerCase().toCharArray();
        char[] cb = b.toLowerCase().toCharArray();
        Arrays.sort(ca);
        Arrays.sort(cb);
        return Arrays.equals(ca, cb);
    }

    // ── 3. Count vowels and consonants ──
    static void countVowelConsonant(String s) {
        int vowels = 0, consonants = 0;
        s = s.toLowerCase();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                if ("aeiou".indexOf(c) != -1) vowels++;
                else consonants++;
            }
        }
        System.out.printf("Vowels: %d | Consonants: %d%n", vowels, consonants);
    }

    // ── 4. Reverse words in a sentence ──
    static String reverseWords(String sentence) {
        String[] words = sentence.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(" ");
        }
        return sb.toString();
    }

    // ── 5. Caesar Cipher ──
    static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) ((c - base + shift) % 26 + base));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // Test Palindrome
        System.out.println(isPalindrome("A man a plan a canal Panama")); // true
        System.out.println(isPalindrome("hello"));                      // false

        // Test Anagram
        System.out.println(isAnagram("listen", "silent"));  // true
        System.out.println(isAnagram("hello",  "world"));   // false

        // Test vowel/consonant count
        countVowelConsonant("Hello World");  // Vowels: 3 | Consonants: 7

        // Reverse words
        System.out.println(reverseWords("Java is awesome")); // awesome is Java

        // Caesar Cipher (shift by 3)
        String encrypted = caesarCipher("Hello Java", 3);
        System.out.println(encrypted);         // Khoor Mdyd
        System.out.println(caesarCipher(encrypted, 23)); // Hello Java (decrypt)
    }
}