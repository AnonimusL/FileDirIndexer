package pkg;

import java.util.Scanner;
import java.util.Set;

public class TextIndexerApp {

    public static void main(String[] args) {
        IndexerI indexer = new SimpleTextIndexer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Text Indexer Console.");

        while (true) {
            System.out.println("\nSelect an option:\n1. Index Files\n2. Search\n3. Exit");
            System.out.print("Enter option number: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter file or directory path to index: ");
                    String path = scanner.nextLine();
                    indexer.indexFiles(path);
                    break;
                case 2:
                    System.out.print("Enter a word to search for: ");
                    String word = scanner.nextLine();
                    Set<String> results = indexer.search(word);
                    if (results.isEmpty()) {
                        System.out.println("No files found containing the word: " + word);
                    } else {
                        System.out.println("Files containing the word '" + word + "':");
                        results.forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
