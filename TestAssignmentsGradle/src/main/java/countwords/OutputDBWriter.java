package countwords;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by vlechuk on 10/19/16.
 */
public class OutputDBWriter implements OutputWriter {

    @Override
    public void writeOutput(List<Map.Entry<String, Integer>> wordsList, int inputSourceId) throws Exception {

        Properties property = new Properties();
        FileInputStream fis = new FileInputStream("build.properties");
        property.load(fis);

        try {
            String myDriver = property.getProperty("mysql_driver");
            String myUrl = property.getProperty("mysql_url");
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, property.getProperty("mysql_user"), property.getProperty("mysql_password"));

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
