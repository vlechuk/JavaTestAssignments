package others;
/**
 * Created by vlechuk on 10/5/16.
 */

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class CountWordsCopy {

    /**
     * Reads the string (string object can be a plain text of a unicode file) and counts/prints top N words in it.
     * @param args - args[0] - the string object;
     *               args[1] - number of for top N words to print out;
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {

        // initialize the String (considering UTF_8 encoding characters string)
        String stringObj = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);

        // create of map of words (using "\\W+" regex)
        Map<String, Integer> wordsMap = convertTextToWordsMap(stringObj, "\\W+");

        // gey top N most frequently used words from a map
        getNMostUsedWords(wordsMap, Integer.parseInt(args[1])) .forEach(System.out::println);
    }

    public static List<Map.Entry<String, Integer>> getNMostUsedWords(Map<String, Integer> wordsMap, int topNWords) {
        //print out top N words

        return
                wordsMap
                        //.collect(Collectors.groupingBy(Function.<String>identity(), HashMap::new, counting()
                        .entrySet()
                        .stream()
                        //.sorted(comparing(map::get).reversed()) // sort by descending frequency
                        // implementaion of the Comparator interface \ "compare" method:
                        //   Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
                        //            @Override
                        //            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                        //                return b.getValue().compareTo(a.getValue());
                        //            }
                        //   });
                        .sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue())))
                        .limit(topNWords)
                        .collect(Collectors.toList());
    }

    public static Map<String, Integer> convertTextToWordsMap(String fileFullPath,
                                                             String wordSplitRegex) {
        // get file access and parse it into a Map(K,V)

        // get file access using Scanner
        // map words/count into "map"
        /*  Scanner scanner = new Scanner(new File("/Users/vlechuk/Documents/test2.txt")).useDelimiter("[^a-zA-Z]+");
            Map<String, Integer> map = new HashMap<String, Integer>();
            while (scanner.hasNext())
            {
                String word = scanner.next();
                if (map.containsKey(word))
                {
                    map.put(word, map.get(word)+1);
                }
                else
                {
                    map.put(word, 1);
                }
            }

            return map;
        */
        // some tests:
        //   (map::get); -> Function<? super T, ? extends U> keyExtractor
        //   List<Map.Entry<String, Integer>> list = map.entrySet().stream().collect(toList());
        //   System.out.println("Key: ".concat(list.get(1).getKey()).concat(" Value: ").concat(list.get(1).getValue().toString()));

        return Arrays.stream(fileFullPath.split(wordSplitRegex))
                .collect(toMap(
                        key -> key,     // key is the word
                        value -> 1,     // value is 1
                        Integer::sum)); // merge function counts the identical words;
    }
}