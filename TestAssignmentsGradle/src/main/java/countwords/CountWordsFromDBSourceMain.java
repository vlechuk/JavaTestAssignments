package countwords;
/**
 * Created by vlechuk on 10/5/16.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CountWordsFromDBSourceMain {
    /**
     * Reads the string (string object can be a plain text of a unicode file) and counts/writes top N words in it.
     * @param args - args[0] - writer source (console or db)
     *               args[1] - number of for top N words to count;
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {

        FileInputStream fis = null;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("build.properties");
            property.load(fis);
        }catch (Exception e){
            throw new FileNotFoundException(e.getMessage());
        }
        finally {
            if(fis != null){
                fis.close();
            }
        }

        // a WORD regex
        String wordSplitRegex = "\\W+";
        // file/string charset
        Charset charset = StandardCharsets.UTF_8;

        CountWords countWords = new CountWords();
        CoutnWordsWriterFactory coutnWordsWriterFactory = new CoutnWordsWriterFactory();

        try
        {
            // create a mysql database connection
            String myDriver = property.getProperty("mysql_driver");
            String myUrl = property.getProperty("mysql_url");
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, property.getProperty("mysql_user"), property.getProperty("mysql_password"));

            // the mysql query statements
            String getTextQuery = "select id, input_type, text_string from WordsTextStrings";

            // create the mysql statement
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(getTextQuery);
            while(result.next()){
                int inputSourceId = result.getInt("id");
                String inputType = result.getString("input_type");
                String text = result.getString("text_string");
                String str;

                if (inputType.equals("file")){
                    str = new String(Files.readAllBytes(Paths.get(text)), charset);
                }else {
                    str = new String(text);
                }

                Map<String, Integer> wordsMap = countWords.convertTextToWordsMap(str, wordSplitRegex);
                List<Map.Entry<String, Integer>> wordsList = countWords.getNMostUsedWords(wordsMap,Integer.parseInt(args[1]));

                OutputWriter outputWriter = coutnWordsWriterFactory.getWriter(args[0]);
                outputWriter.writeOutput(wordsList, inputSourceId);

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