package countwords;

/**
 * Created by vlechuk on 10/19/16.
 */
public class CoutnWordsWriterFactory {

    //use writerType method to get object of type writer

    public OutputWriter getWriter(String writerType){
        if(writerType == null){
            return null;
        }
        if(writerType.equalsIgnoreCase("db")){
            return new OutputDBWriter();

        } else if(writerType.equalsIgnoreCase("console")){
            return new OutputConsolWriter();
        }
        else return null;
    }
}