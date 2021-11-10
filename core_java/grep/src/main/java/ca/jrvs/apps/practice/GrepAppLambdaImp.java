package ca.jrvs.apps.practice;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GrepAppLambdaImp implements GrepAppInt{
    private static String RegexPattern;
    private static String RootPath;
    private static String OutFile;
    private static Logger logger = Logger.getLogger(JLinkedList.class.getName());


    public static void main(String[] args) {
        GrepAppLambdaImp Grep=new GrepAppLambdaImp();
        Grep.setRegexPattern(args[0]);
        Grep.setRootPath(args[1]);
        Grep.setOutFile(args[2]);
        try{
            Grep.strRegexParse();
        }catch(Exception ex){
            logger.setLevel(Level.SEVERE);
            logger.severe("Error Running strRegexParse",ex);
        }
    }

    @Override
    public void setRegexPattern(String regexpattern) {
        RegexPattern=regexpattern;

    }

    @Override
    public void setRootPath(String rootpath) {
        RootPath=rootpath;
    }

    @Override
    public void setOutFile(String outfile) {
        OutFile=outfile;
    }

    @Override
    public String getRegexPattern() {
        return RegexPattern;
    }

    @Override
    public String getRootPath() {
        return RootPath;
    }

    @Override
    public String getOutFile() {
        return OutFile;
    }

    @Override
    public HashMap<Integer, String> concatFile() throws IOException {
        List<File> dirList= DirectoryStrtoFileList(getRootPath());
        HashMap<Integer, String> concat = new HashMap<>();
        String contents="";
        int iterator=0;
        for(File f : dirList) {
            Scanner sc1 = new Scanner(f);

            while (sc1.hasNextLine()) {
                iterator +=1;
                contents = sc1.nextLine();
                concat.put(iterator,contents);
            }
            sc1.close();
        }
        return(concat);
    }

    @Override
    public void strRegexParse() throws IOException {
        FileWriter fwrite=new FileWriter(getOutFile());
        HashMap<Integer, String> fileContents = concatFile();
        //Easier route using regex patterns
        List<HashMap.Entry<Integer,String>> collection = fileContents.entrySet().stream()
                                            .filter(e -> e.getValue().matches(getRegexPattern()))
                                            .collect(Collectors.toList());

        collection.forEach(x-> {
            try {
                fwrite.write(x.getValue() + "\n");
            } catch (IOException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe("Failed to write to Outfile",e);
            }
        });

        //Cleanup
        fwrite.close();

    }

    @Override
    public List<File> DirectoryStrtoFileList(String rootpath) throws IOException {
        List<File> dirList = Arrays.asList(new File(rootpath).listFiles());
        return(dirList);
    }
}
