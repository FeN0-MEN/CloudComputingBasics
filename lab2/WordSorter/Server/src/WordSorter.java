import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordSorter {

  public static String [] sortWords(String textBlock) {
    String[] words = textBlock.toLowerCase().split(" ");
    Set<String> wordSet = new HashSet<>(Arrays.asList(words));
    String[] uniqueWords = wordSet.toArray(new String[0]);
    Arrays.sort(uniqueWords);
    return uniqueWords;
  }
}
