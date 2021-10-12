package ca.jrvs.apps.practice;
import java.io.*;
import java.util.Scanner;


public class grepapp {
    private static String ConcatFile(File file) throws FileNotFoundException {
        String concat="";
        Scanner sc1= new Scanner(file);
        while(sc1.hasNextLine()){
            concat=concat+sc1;
        }
        sc1.close();
        return(concat);
    }
    
    private static void StringRegexParser(){

    }
    public static void main(String args[]) throws IOException  {
        //initialize variables
        String regexPattern=args[0];
        String rootPath=args[1];
        String outFile=args[2];
        FileWriter fwrite=new FileWriter(outFile);


        //Convert to File type
        File path = new File(rootPath);
        File fileArray[] = path.listFiles();


        //Iterate through files and Match the pattern
        for(File file : fileArray){
            //Easier route using regex patterns
            String fileContents = ConcatFile(file);
            if(fileContents.matches(regexPattern)){
                fwrite.write(file.getName() + "\n");
            }
        }
        //Cleanup
        fwrite.close();
    }
}
