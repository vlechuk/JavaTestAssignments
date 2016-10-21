package countwords;

import java.util.List;
import java.util.Map;

/**
 * Created by vlechuk on 10/19/16.
 */
public interface OutputWriter {
    void writeOutput(List<Map.Entry<String, Integer>> wordsList,int inputSourceId);
}
