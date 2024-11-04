package pkg;

import java.util.Set;

public interface IndexerI {
    void indexFiles(String path);
    Set<String> search(String word);
}
