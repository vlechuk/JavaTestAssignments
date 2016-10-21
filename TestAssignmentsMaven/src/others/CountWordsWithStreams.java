package others; /**
 * Created by vlechuk on 10/5/16.
 */

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.counting;


public class CountWordsWithStreams {

    public static void main (String args[]) throws Exception {

//        Arrays.stream(new String(Files.readAllBytes(Paths.get("/Users/vlechuk/Documents/test.txt")), StandardCharsets.UTF_8).split("\\W+"))
//                .collect(Collectors.groupingBy(Function.<String>identity(), HashMap<String, Integer>::new, counting()))
//                .entrySet()
//                .stream()
//                .sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue())))
//                .limit(10)
//                .forEach(System.out::println);
    }
}