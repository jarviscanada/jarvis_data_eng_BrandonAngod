package ca.jrvs.apps.test;
import ca.jrvs.apps.practice.grepapp;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

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
}
