package com.leon.scaapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Leon on 8/7/2018.
 */

public class Readfile {
    private FileReader inputfile;
    private BufferedReader input;
    public boolean fileExists;
    // Constructor
    public Readfile(String infilename)
    {
        try
        {
            inputfile= new FileReader(infilename);
            input = new BufferedReader(inputfile);
            fileExists = true;
        }
        catch (IOException e)
        {
            System.out.print("File open error: " + e.getMessage());
            fileExists = false;
        }
    }
    public String readRecord()
    {
        String line="";
        try
        {
            line=input.readLine();
        }
        catch (IOException e)
        {
            System.out.print("File read error: " + e.getMessage());
        }
        return line;
    }
    public void close()
    {
        try
        {
            inputfile.close();
        }
        catch (IOException e)
        {

        }
    }
}
