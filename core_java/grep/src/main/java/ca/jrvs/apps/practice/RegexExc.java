package ca.jrvs.apps.practice;
import java.util.regex.*;
public interface RegexExc{

    public boolean matchJpeg(String filename);

    public boolean matchIp(String ip);

    public boolean isEmptyLine(String line);
}
class RegexExe implements RegexExc{

    public boolean matchJpeg(String filename){ return Pattern.matches("[\\S]+([.](jpg|jpeg))$", filename);}

    public boolean matchIp(String ip){ return Pattern.matches("[0-9]+[.]+[0-9]+[.]+[0-9]+[.]+[0-9]+", ip);}

    public boolean isEmptyLine(String line) {return Pattern.matches("^\\s*$", line);}
}
