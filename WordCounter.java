import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;

public class WordCounter{
    public static int processText(StringBuffer text, String stopWord) throws TooSmallText, InvalidStopwordException {

        Pattern pattern = Pattern.compile("[a-zA-Z0-9']+");
        Matcher matcher = pattern.matcher(text);

        int totalCount = 0;
        int stopCount = 0;
        boolean found = false;

        while(matcher.find()){
            totalCount++;
            if(!found){
                stopCount++;
            }
            if(stopWord != null && matcher.group().equals(stopWord)){
                found = true;
            }
            
        }
        
        if(totalCount < 5){
            throw new TooSmallText("Only found " + totalCount + " words.");
        }

        if(stopWord == null){
            return totalCount;
        }

        if(!found){
            throw new InvalidStopwordException("Couldn't find stopword: " + stopWord);
        }  

        return stopCount;
    }

    public static StringBuffer processFile(String path) throws EmptyFileException {

        Scanner input = new Scanner(System.in);
        File file = new File(path);
        
        while(!file.exists() || !file.canRead()){
            System.out.println("Failed to open.");
            path = input.nextLine();
            file = new File(path);                                                                                          
        }

        StringBuffer sb = new StringBuffer();

        try{
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine()){
                sb.append(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e){
            System.out.println("could not open");
        }

        if (sb.toString().length() == 0) {
            throw new EmptyFileException(path + " was empty");
        }
        
        return sb;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuffer text = new StringBuffer();
        String stopWord = null;

        String option = "";
        while(!option.equals("1") && !option.equals("2")){
            System.out.println("Enter 1 or 2");
            option = input.nextLine();
        }

        if(args.length > 1){
            stopWord = args[1];
        }

        if(option.equals("1")){
            try {
                text = processFile(args[0]);
            } catch (EmptyFileException e) {
                System.out.println(e);
                text = new StringBuffer("");
            }
        } else {
            text = new StringBuffer(args[0]);
        }

        while(true){
            try {
                int count = processText(text, stopWord);
                System.out.println("Found " + count + " words.");
                break;
            } catch (InvalidStopwordException e){
                System.out.println(e);
                System.out.println("Enter new stopword:");
                stopWord = input.nextLine();
            } catch (TooSmallText e){
                System.out.println(e);
                break;
            }
        }
    }
}