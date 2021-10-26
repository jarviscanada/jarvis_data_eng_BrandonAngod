# Introduction
This application utilizes Java 8 to recreate the same function as the Linux command "grep". To do this I used methods from Java's util libraries such as Scanners and Regex.pattern(). I also used FileWriter to record the findings within separate files and the .io's methods to read a file. To build the files I am using Maven. This project also features another method of running the program using lambda functions and stream APIs to increase performance for larger files. Deployment of the project was done using Docker and the code was written using Intellij.

# Quick Start
To start the application with the code you can simply build it using the command mvn clean install.
Afterward, it will create a .jar file within the target/ file. So simply run:

java -jar target/grep-1.0-SNAPSHOT.jar <RegexPattern> <RootPath> <Outfile>

If you want to use the non-Lambda version and opt for the version using a scanner change the pom.xml from:

ca.jrvs.apps.practice.GrepAppLambdaImp -> ca.jrvs.apps.practice.grepapp

# Implemenation
## Pseudocode
write `process` method pseudocode.
First we find we take our arguments and convert them into proper variables
```java
    File[] Directory = RootPath
    File OutFile = OutFile
```

Now we can look at each file within the array Directory and get the contents of the file to do so we make a function to concatonate the file contents into a string
```java
    String FileContents;
    Scanner sc;
    while(sc.hasNextLine()){
        FileContents += sc.nextLine();
    }
    return(FileContents)
```

This will feed all of the lines within the file to a single string variable. Now we can compare the contents with the regex pattern and output if its a match
```java
   if(FileContent.matches(RegexPattern)){
       FileWriter FW = new File(Outfile);
       FW.write(FileName);
   } 
```

This will output all of our results towards our output file. The lambda functions a bit differently compared to the non-lambda version. Instead, it uses a list instead of an array of a file array and saves all the content of the files into a Map<String, String> instead of using the filename as a key and contents as a value. Afterward, the map is filtered by anything that matches the regex pattern and outputs to the Outfile using the same method.
```java
        FileWriter fwriter=new FileWriter(OutFile);
        Map<String, String> fileContents = getText();
        List<Map.Entry<String,String>> collection = fileContents.filter(Value.matches(RegexPattern))
                                            .collect(Collectors.toList());

        For Each entry in collection {
            fwrite.write(entry.key)
        }
            }
```
## Performance Issue
The current issue with this project is the requirement of memory. To alleviate this I used lambda functions and stream APIs to help improve how much information it can hold. In the future for improvements, I would have to use more efficient algorithms to decrease the memory issues.

# Test
To test individual functions I used JUnit to unit test each function and see if it produced the expected output.
To test the entire application function I first created a test file with a few words to see if the program was working. Afterward, I used a test file that contained the entire script of a Shakespeare play since that had a lot of words for the application to work through.
Once my program passed the first test I proceeded to the second test to measure its performance.

# Deployment
To dockerize my application I packaged the program using maven and then created a docker image using the package as its frame.

# Improvement
List three things you can improve in this project.
1. Memory Issues.
2. Commenting and documentation.
3. Optimization of methods.
