package countwords;

import java.util.List;
import java.util.Map;

/**
 * Created by vlechuk on 10/19/16.
 */
public class OutputConsolWriter implements OutputWriter {

    @Override
    public void writeOutput(List<Map.Entry<String, Integer>> wordsList, int inputSourceId){
        wordsList.forEach(System.out::println);
    }
}
