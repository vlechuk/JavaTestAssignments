package test.java

import main.java.CountWords
import org.junit.Test

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

/**
 * Created by vlechuk on 10/9/16.
 */
class CountWordsTest extends GroovyTestCase {

    @Test
    void testGetNMostUsedWords() {

        //String str = new String(Files.readAllBytes(Paths.get(<file_path>)), StandardCharsets.UTF_8);
        String str = new String("This is the END, the END !!!!");

        Map<String, Integer> map = new HashMap<>();
        map.put("the",2);
        map.put("END",2);

        CountWords cw = new CountWords();

        assertEquals(map.entrySet().stream().collect(Collectors.toList()), cw.getNMostUsedWords(cw.convertTextToWordsMap(str,"\\W+"), 2));
    }

    @Test
    void testConvertTextToWordsMap() {

        //String str = new String(Files.readAllBytes(Paths.get(<file_path>)), StandardCharsets.UTF_8);
        String str = new String("This is the END, the END !!!!");

        Map<String, Integer> map = new HashMap<>();
        map.put("This",1);
        map.put("is",1);
        map.put("the",2);
        map.put("END",2);

        CountWords cw = new CountWords();

        assertEquals(map, cw.convertTextToWordsMap(str,"\\W+"));
    }

    @Test
    void testConvertFileTextToWordsMap() {

        String filePath = new String("/tmp/fileForWordsCount.txt");
        File f  = new File(filePath);
        OutputStream outputStream       = new FileOutputStream(f);
        Writer       outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

        outputStreamWriter.write("This is the END, the END !!!!");
        outputStreamWriter.close();

        String str = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);

        Map<String, Integer> map = new HashMap<>();
        map.put("This",1);
        map.put("is",1);
        map.put("the",2);
        map.put("END",2);

        CountWords cw = new CountWords();

        assertEquals(map, cw.convertTextToWordsMap(str,"\\W+"));
        f.delete();
    }
}
