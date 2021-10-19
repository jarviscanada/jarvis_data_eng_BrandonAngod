package ca.jrvs.apps.test;
import ca.jrvs.apps.practice.grepapp;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class GrepTest {
    @Before
    public void init(){

    }
    @Test
    public void StrToFileArrTest(){
        grepapp test=new grepapp();
        File rootpath=new File("/home/centos/TestFiles/");
        File ExpectedOut[]=rootpath.listFiles();
        File[] output= test.StrToFileArr("/home/centos/TestFiles/");
        assertEquals(ExpectedOut,output);
    }
    @Test
    public void ConcatFileTest() throws FileNotFoundException {
        grepapp test=new grepapp();
        String ExpectedOut = "The Dog walked up the moon when he realized that there was another dog aiming for the" +
                             " same bone so they went at it and tried to find out who ate the bone";
        File file = new File("/home/centos/TestFiles/Random.txt");
        String output = test.ConcatTextFile(file);
        assertEquals(ExpectedOut,output);
    }
}
