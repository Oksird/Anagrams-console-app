package ua.foxminded.anagrams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnagramProviderTest {
    private final AnagramProvider anagramProvider = new AnagramProvider();
    @Test
    void provideAnagram_shouldThrowIllegalArgumentExceptionSentenceIsNull_whenSentenceIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            anagramProvider.provideAnagram(null);
        });

        String expectedMessage = "Sentence is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void provideAnagram_shouldThrowIllegalArgumentExceptionSentenceIsEmpty_whenSentenceIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            anagramProvider.provideAnagram("");
        });

        String expectedMessage = "Sentence is empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void provideAnagram_shouldThrowIllegalArgumentExceptionSentenceIsBlank_whenSentenceContainsOnlyOneSpace() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            anagramProvider.provideAnagram(" ");
        });

        String expectedMessage = "Sentence contains only tabs or/and spaces";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void provideAnagram_shouldThrowIllegalArgumentExceptionSentenceIsBlank_whenSentenceContainsSeveralSpaces() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            anagramProvider.provideAnagram("                 	 ");
        });

        String expectedMessage = "Sentence contains only tabs or/and spaces";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void provideAnagram_shouldReturnSameCharacter_whenSentenceContainsOnlyOneCharacter() {
        assertEquals("a", anagramProvider.provideAnagram("a"));
    }

    @Test
    void provideAnagram_shouldReturnUnchangedSentence_whenSentenceContainsSeveralIdenticalLetters() {
        assertEquals("nnnnnnnnnn", anagramProvider.provideAnagram("nnnnnnnnnn"));
    }

    @Test
    void provideAnagram_shouldReturnReversedSentenceWithSameLettersInLowerAndUpperCasesMixed_whenSentenceContainsSameLetterInLowerAndUpperCasesMixed() {
        assertEquals("bBbbBBbbbbBbBbbbBBBBbbbBBbbbBBbbBBBbBbbb", anagramProvider.provideAnagram("bbbBbBBBbbBBbbbBBbbbBBBBbbbBbBbbbbBBbbBb"));
    }

    @Test
    void provideAnagram_shouldReturnReversedWord_whenSentenceContainsOneWordWithDifferentLetters() {
        assertEquals("mysksaM", anagramProvider.provideAnagram("Masksym"));
    }

    @Test
    void provideAnagram_shouldReturnNotReversedSymbols_whenSentenceContainsOnlySymbols() {
        assertEquals("*!^$*(@#%^@#&(%^@#(($", anagramProvider.provideAnagram("*!^$*(@#%^@#&(%^@#(($"));
    }

    @Test
    void provideAnagram_shouldReturnSentenceWithReversedWords_whenSentenceContainsSeveralWords() {
        assertEquals("erehT era a tol fo gnimmargorp segaugnal", anagramProvider.provideAnagram("There are a lot of programming languages"));
    }

    @Test
    void provideAnagram_shouldReturnReversedWordsWhereNonLetterSymbolsAreOnSamePlaces_whenSentenceContainsWordsWithSymbols() {
        assertEquals("kd@gfnxA* 5ijs#$1!1#j52 010sj899dj3#@1@38@Ss", anagramProvider.provideAnagram("Ax@nfgdk* dnsj#$1!sji52 010fmf99sS3#@1jd8@js"));
    }
}