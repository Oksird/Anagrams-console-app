package ua.foxminded.anagrams;

public class AnagramConsoleApplication {
    public static void main(String[] args) {
        AnagramProvider anagramProvider = new AnagramProvider();
        System.out.println(anagramProvider.provideAnagram("ds!fsdbv 23sdf5sD2v 52fssc#s"));
    }
}
