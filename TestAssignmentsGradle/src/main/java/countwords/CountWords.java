package countwords; /**
 * Created by vlechuk on 10/5/16.
 */

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;
public class CountWords {

    private String wordSplitRegex = "\\W+";
    /**
     * Takes a string (string object can be a plain text of a unicode file) and a regex pattern in order to split text into words and their usage and put data into a map.
     * @param strObj - the string (string object can be a plain text of a unicode file) and counts/prints top N words in it.
     * @param wordSplitRegex - regex pattern for words splitting.
     * @return - a words/count map.
     */
    public Map<String, Integer> convertTextToWordsMap(String strObj,
                                                      String wordSplitRegex) {
        if (wordSplitRegex.isEmpty()) wordSplitRegex = this.wordSplitRegex;

        return Arrays.stream(strObj.split(wordSplitRegex))
                .collect(toMap(
                        key -> key,     // key is the word
                        value -> 1,     // value is 1
                        Integer::sum)); // merge function counts the identical words;
    }

    /**
     * Returns list of words/count for a top N most frequently used words.
     * @param wordsMap - a map with entire words/count data.
     * @param topNWords - number of a to N most frequently used words.
     * @return - an N words/count list.
     */
    public List<Map.Entry<String, Integer>> getNMostUsedWords(Map<String, Integer> wordsMap, int topNWords) {
        return wordsMap
                    .entrySet()
                    .stream()
                    .sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue())))
                    .limit(topNWords)
                    .collect(Collectors.toList());
    }
}