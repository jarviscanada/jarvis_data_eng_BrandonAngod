package ca.jrvs.apps.practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GrepAppInt {
    /**
     * Sets the private RegexPattern Variable
     * @param regexpattern
     */
    void setRegexPattern(String regexpattern);

    /**
     * Sets the private Rootpath variable
     * @param rootpath
     */
    void setRootPath(String rootpath);

    /**
     * Sets the private Outfile String variable
     * @param outfile
     */
    void setOutFile(String outfile);

    /**
     * Returns the RegexPattern
     * @return RegexPattern
     */
    String getRegexPattern();

    /**
     * Returns RootPath
     * @return RootPath
     */
    String getRootPath();

    /**
     * Returns Outfile
     * @return OutFile
     */
    String getOutFile();

    /**
     * Take input of a file and return the strings within it concatonated within one string
     * @param
     * @return StringConcatinated
     * @throws FileNotFoundException
     */
    Map<String, String> ConcatFile() throws IOException;

    /**
     * Loads an array of files in a directory then creates a file writer.
     * Check to see if the concatonated files match.
     * If they do output the file's name to a text file.
     * @throws IOException
     */
    void StrRegexParse() throws IOException;

    /**
     * Takes the String provided and converts it into a File Type to process
     * @param rootpath
     * @return
     */
    List<File> DirectoryStrtoFileList(String rootpath) throws IOException;
}
