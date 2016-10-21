package others; /**
 * Created by vlechuk on 10/5/16.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ScannerTest
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File("/Users/vlechuk/Documents/test2.txt")).useDelimiter("[^a-zA-Z]+");
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

        List<Map.Entry<String, Integer>> entries = new ArrayList<Entry<String,Integer>>( map.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return b.getValue().compareTo(a.getValue());
            }
        });

        List<Map.Entry<String, Integer>> limitedEnties = entries.stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(10)
                .collect(Collectors.toList());

        for(int i = 0; i < limitedEnties.size(); i++){
            System.out.println(limitedEnties.get(limitedEnties.size() - i - 1).getKey()+" "+limitedEnties.get(limitedEnties.size() - i - 1).getValue());
        }
        //        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String,Integer>>( frequencyMap.entrySet());
//
//        List<Map.Entry<String, Integer>> newEntries = entries.stream()
//                .sorted(comparing(frequencyMap::get).reversed()) // sort by descending frequency
//                .limit(10)
//                .collect(Collectors.toList());

//        for(int i = 0; i < newEntries.size(); i++){
//            System.out.println(entries.get(newEntries.size() - i - 1).getKey()+" "+newEntries.get(newEntries.size() - i - 1).getValue());
//        }

//        Map<String, Integer> top10 = entries.stream()
//                .sorted(comparing(frequencyMap::get).reversed()) // sort by descending frequency
//                .distinct() // take only unique values
//                .limit(10)   // take only the first 10
//                .collect(toMap()); // put it in a returned list



//        List<String> words = Arrays.stream(new String(Files.readAllBytes(Paths.get("/Users/vlechuk/Documents/test2.txt")), StandardCharsets.UTF_8).split("\\W+"))//words.stream()
//                .distinct() // take only unique values
//                .collect(toList()); // put it in a returned list


// find the top 10
//        List<String> top10 = words.stream()
//                .sorted(comparing(frequencyMap::get).reversed()) // sort by descending frequency
//                .distinct() // take only unique values
//                .limit(10)   // take only the first 10
//                .collect(toList()); // put it in a returned list
//
//        System.out.println("top10 = " + top10);

//        StringReader sr = new StringReader("Hellow my friend");
//        FileInputStream fis = new FileInputStream("/Users/vlechuk/Documents/1.sql",);

//        FileReader fr = new FileReader ("/Users/vlechuk/Documents/1.sql");
//        BufferedReader br = new BufferedReader (fr);
//        String line = br.readLine();
//          int data = sr.read();


//        int count = 0;
//        while (line != null) {
//            String []parts = line.split(" ");
//            for( String w : parts)
//            {
//                count++;
//            }
//            line = br.readLine();
//        }
//        sr.close();
//        System.out.println(count);
    }
}