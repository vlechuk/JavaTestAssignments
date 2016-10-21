package countwords;
/**
 * Created by vlechuk on 10/5/16.
 */

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class CountWordsDBMain{

    /**
     * Reads the string (string object can be a plain text of a unicode file) and counts/prints top N words in it.
     * @param args - args[0] - number of for top N words to count;
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {

        CountWords countWords = new CountWords();

        try
        {
            // create a mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/test";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            // the mysql query statements
            String getTextQuery = "select * from WordsTextStrings";
            String insertWordsCountQuery = "insert into WordsCount (input_text_id, word, count) values (?, ?, ?)";

            // create the mysql insert preparedstatement
            Statement statement = conn.createStatement();
            PreparedStatement preparedStmt = conn.prepareStatement(insertWordsCountQuery);

            ResultSet result = statement.executeQuery(getTextQuery);
            while(result.next()){
                int id = result.getInt("id");
                String inputType = result.getString("input_type");
                String text = result.getString("text_string");
                String str;

                if (inputType.equals("file")){
                    str = new String(Files.readAllBytes(Paths.get(text)), StandardCharsets.UTF_8);
                }else {
                    str = new String(text);
                }

                Map<String, Integer> wordsMap = countWords.convertTextToWordsMap(str,"\\W+");
                List<Map.Entry<String, Integer>> wordsList = countWords.getNMostUsedWords(wordsMap,Integer.parseInt(args[0]));

                for(Map.Entry<String , Integer> entry : wordsList ) {

                    preparedStmt.setInt (1, id);
                    preparedStmt.setString (2, entry.getKey());
                    preparedStmt.setInt (3, entry.getValue());

                    // execute the prepared Statement
                    preparedStmt.execute();
                }
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