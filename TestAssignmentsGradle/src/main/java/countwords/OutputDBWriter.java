package countwords;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

/**
 * Created by vlechuk on 10/19/16.
 */
public class OutputDBWriter implements OutputWriter {

    @Override
    public void writeOutput(List<Map.Entry<String, Integer>> wordsList, int inputSourceId) {

        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/test";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            // the mysql query statements
            String insertWordsCountQuery = "insert into WordsCount (input_source_id, word, count) values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(insertWordsCountQuery);

            for (Map.Entry<String, Integer> entry : wordsList) {

                preparedStmt.setInt(1, inputSourceId);
                preparedStmt.setString(2, entry.getKey());
                preparedStmt.setInt(3, entry.getValue());

                // execute the prepared Statement
                preparedStmt.execute();
            }
            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }
}
