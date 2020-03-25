package com.leon.scaapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.*;

/**
 * Created by Leon on 8/7/2018.
 */

public class Writefile {
    private FileOutputStream filename;

    public Writefile(String inputfilename)
    {
        try
        {
            filename = new FileOutputStream(inputfilename,false);
        }
        catch (IOException e) {}
    }

    public void write(String item)
    {
        PrintStream output = new PrintStream(filename);
        output.println(item);
    }

    public void close()
    {
        try
        {
            filename.close();
        }
        catch (IOException e) {}
    }
}
