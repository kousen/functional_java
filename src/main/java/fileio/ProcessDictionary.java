package fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ProcessDictionary {
    private final Path dictionary = Paths.get("src/main/resources/dict/words");
    private final int maxForFilter = maxLength() - 5;

    int maxLength() {
        try (Stream<String> words = Files.lines(dictionary)) {
            return words.max(Comparator.comparing(String::length)).orElse("").length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void printTenLongestWords() {
        System.out.println("\nTen Longest Words:");
        try (Stream<String> words = Files.lines(dictionary)) {
            words.filter(word -> word.length() > maxForFilter)
                    .sorted(Comparator.comparingInt(String::length).reversed()
                            //.thenComparing(Comparator.reverseOrder()))
                    )
                    .limit(10)
                    .forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printWordsOfEachLength() {
        System.out.println("\nList of words of each length:");
        try (var words = Files.lines(dictionary)) {
            words.filter(word -> word.length() > maxForFilter)
                    .collect(groupingBy(String::length)) // Map<Integer,List<String>>
                    .forEach((len, wordList) -> System.out.println(len + ": " + wordList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printNumberOfWordsOfEachLength() {
        System.out.println("\nNumber of words of each length:");
        try (Stream<String> words = Files.lines(dictionary)) {
            words.filter(word -> word.length() > maxForFilter)
                    .collect(groupingBy(String::length, counting())) // Map<Integer,Long>
                    .forEach((len, num) -> System.out.printf("%d: %d%n", len, num));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProcessDictionary processDictionary = new ProcessDictionary();
        processDictionary.printTenLongestWords();
        processDictionary.printWordsOfEachLength();
        processDictionary.printNumberOfWordsOfEachLength();
    }
}
