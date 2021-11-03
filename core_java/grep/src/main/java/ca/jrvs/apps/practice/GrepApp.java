package ca.jrvs.apps.practice;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class GrepApp{
    private static String RegexPattern;
    private static String RootPath;
    private static String OutFile;

    /**
     * Sets the private RegexPattern Variable
     * @param regexpattern
     */
    public static void setRegexPattern(String regexpattern){
        RegexPattern=regexpattern;
    }

    /**
     * Sets the private Rootpath variable
     * @param rootpath
     */
    public static void setRootPath(String rootpath){
        RootPath = rootpath;
    }

    /**
     * Sets the private Outfile String variable
     * @param outfile
     */
    public static void setOutFile(String outfile){
        OutFile = outfile;
    }

    /**
     * Returns the RegexPattern
     * @return RegexPattern
     */
    private static String getRegexPattern(){
        return RegexPattern;
    }

    /**
     * Returns RootPath
     * @return RootPath
     */
    private static String getRootPath(){
        return RootPath;
    }

    /**
     * Returns Outfile
     * @return OutFile
     */
    private static String getOutFile(){
        return OutFile;
    }

    /**
     * Take input of a file and return the strings within it concatonated within one string
     * @param file
     * @return StringConcatinated
     * @throws FileNotFoundException
     */
    public static List<String> ConcatTextFile(File file) throws FileNotFoundException {
        List<String> concat=new ArrayList<>();
        Scanner scanner= new Scanner(file);
        while(scanner.hasNextLine()){
            concat.add(scanner.nextLine());
        }
        scanner.close();
        return(concat);
    }

    /**
     * Loads an array of files in a directory then creates a file writer.
     * Check to see if the concatonated files match.
     * If they do output the file's name to a text file.
     * @throws IOException
     */
    public static void StrRegexParse() throws IOException  {
        File fileArray[]=StrToFileArr(getRootPath());
        FileWriter fwrite=new FileWriter(getOutFile());
        for(File file : fileArray){
            //Easier route using regex patterns
            List<String> fileContents = ConcatTextFile(file);
            fileContents.forEach(x -> {
                if (Pattern.matches(getRegexPattern(), x)) {
                    try {
                        fwrite.write(file.getName() + "\n");
                    } catch (IOException ex) {
                        logger.error("Could not write to OutFile",ex);
                    }
                }
            });
        }
        //Cleanup
        fwrite.close();
    }

    /**
     * Takes the String provided and converts it into a File Type to process
     * @param rootpath
     * @return
     */
    public static File[] StrToFileArr(String rootpath){
        File folder = new File(rootpath);
        return (folder.listFiles());
    }

    /**
     * Takes 3 arguments and Emulates the effect of bash command grep
     * Any files matching the pattern within a directory will be outputted on the outfile indicated by the arguments
     * @param args [RegexPattern, RootPath, Outfile]
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        //initialize variables
        setRegexPattern(args[0]);
        setRootPath(args[1]);
        setOutFile(args[2]);
        //Iterate through files and Match the pattern
        StrRegexParse();

    }
}
