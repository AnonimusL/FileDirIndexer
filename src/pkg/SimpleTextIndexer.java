package pkg;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class SimpleTextIndexer implements IndexerI{
    private Map<String, Set<String>> index = new HashMap<>();

    @Override
    public void indexFiles(String path) {
        Path dirPath = Paths.get(path);
        try (Stream<Path> paths = Files.walk(dirPath)) {
            paths.filter(Files::isRegularFile).forEach(this::indexFile);
        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
    }

    private void indexFile(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                tokenize(line).forEach(word ->
                        index.computeIfAbsent(word.toLowerCase(), k -> new HashSet<>()).add(filePath.toString())
                );
            }
            System.out.println("Indexed file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error indexing file: " + filePath + " - " + e.getMessage());
        }
    }

    @Override
    public Set<String> search(String word) {
        return index.getOrDefault(word.toLowerCase(), Collections.emptySet());
    }

    private List<String> tokenize(String text) {
        return Arrays.asList(text.split("\\W+"));
    }
}
