import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class WordSorter {

  public static String @NotNull [] sortWords(@NotNull String textBlock) {
    String[] words = textBlock.toLowerCase().split(" ");
    Set<String> wordSet = new HashSet<>(Arrays.asList(words));
    String[] uniqueWords = wordSet.toArray(new String[0]);
    Arrays.sort(uniqueWords);
    return uniqueWords;
  }
}
