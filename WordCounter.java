import java.util.*;
import java.util.regex.*;

public class WordCounter{

    public static int processText(StringBuffer text, String stopWord){

        Pattern pattern = Pattern.compile("[a-zA-Z0-9']");
        Matcher matcher = pattern.matcher(text);

        int totalCount = 0;
        while(matcher.find()){
            totalCount++;
        }
        
        if(totalCount < 5){
            throw new TooSmallText(totalCount);
        }

        if(stopWord == null){
            return totalCount;
        } else {
            boolean found = false;
            if(matcher.equals(stopWord)){
                found = true;
                break;
            }
            if(!found){
                throw new InvalidStopwordException("Couldn't find stopword: " + stopWord);
            }
        }
    }








}

