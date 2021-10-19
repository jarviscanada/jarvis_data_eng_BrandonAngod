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

    public static void main(String[] args) {
            if(args.length != 3) {
                grepapp Grep=new grepapp();
                Grep.setRegexPattern(args[0]);
                Grep.setRootPath(args[1]);
                Grep.setOutFile(args[2]);

                try{
                    Grep.StrRegexParse();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
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
    public Map<String, String> ConcatFile() throws IOException {
        List<File> dirList= DirectoryStrtoFileList(getRootPath());
        Map<String, String> concat = new HashMap<>();
        String contents="";

        for(File f : dirList) {
            Scanner sc1 = new Scanner(f);

            while (sc1.hasNextLine()) {
                contents += sc1.nextLine();
            }
            concat.put(f.getName(),contents);
            contents="";
            sc1.close();
        }
        return(concat);
    }

    @Override
    public void StrRegexParse() throws IOException {
        FileWriter fwrite=new FileWriter(getOutFile());
        Map<String, String> fileContents = ConcatFile();
        //Easier route using regex patterns
        Map<String,String> collection = fileContents.entrySet().parallelStream()
                                            .filter(e -> e.toString().matches(getRegexPattern()))
                                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        for (Map.Entry<String, String> entry : collection.entrySet()) {
            fwrite.write(entry.getKey());
        }
        //Cleanup
        fwrite.close();

    }

    @Override
    public List<File> DirectoryStrtoFileList(String rootpath) throws IOException {
        List<File> dirList = Arrays.asList(new File(rootpath).listFiles());
        return(dirList);
    }
}
