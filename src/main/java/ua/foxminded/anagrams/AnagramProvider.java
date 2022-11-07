package ua.foxminded.anagrams;

import java.util.Arrays;

public class AnagramProvider {

    private static final String SPACE_DELIMITER = " ";

    public String provideAnagram(String sentence) {
        validateSentence(sentence);
        String[] words = sentence.split(SPACE_DELIMITER);

        for (int i = 0; i < words.length; i++) {
            words[i] = reverseWord(words[i]);
        }
        return String.join(SPACE_DELIMITER, words);
    }

    private String reverseWord(String word) {

        char[] copyOfWord = new char[word.length()];
        char[] wordAsCharArray = word.toCharArray();
        int indexOfLastLetter = word.length() - 1;

        Arrays.fill(copyOfWord, 'x');

        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(wordAsCharArray[i])){
                copyOfWord[i] = wordAsCharArray[i];
            }
        }

        for (int i = 0; i < copyOfWord.length; i++) {
            if (!Character.isLetter(copyOfWord[i])) {
                continue;
            }
            if (!Character.isLetter(wordAsCharArray[indexOfLastLetter])) {
                indexOfLastLetter--;
            }
            copyOfWord[i] = wordAsCharArray[indexOfLastLetter];
            indexOfLastLetter--;
        }

        return String.valueOf(copyOfWord);
    }

    private void validateSentence(String sentence){
        if (sentence == null) {
            throw new IllegalArgumentException("Sentence is null");
        }
        if (sentence.isEmpty()) {
            throw new IllegalArgumentException("Sentence is empty");
        }
        if (sentence.isBlank()) {
            throw new IllegalArgumentException("Sentence contains only tabs or/and spaces");
        }
    }
}
