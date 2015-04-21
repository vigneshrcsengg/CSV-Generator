package com.report.csv.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVTestReader
{
    public static final String CSV_PATTERN = "\"([^\"]+?)\",?|([^,]+),?|,";
    private static Pattern csvRE;
    
    public CSVTestReader()
    {
        csvRE = Pattern.compile(CSV_PATTERN);
    }

    public static void main(String[] argv) throws IOException
    {
        System.out.println(CSV_PATTERN);
        new CSVTestReader().process(new BufferedReader(new FileReader("C:\\Users\\Prasanth R\\Desktop\\DirectoryCreated\\cellDatabase\\REPORT_FROM_DB.csv")));
    }
    
    public void process(BufferedReader in) throws IOException
    {
        String line;

        // For each line...
        while ((line = in.readLine()) != null)
        {
            System.out.println("line = " + line + "'");
            List l = parse(line);
            System.out.println("Found " + (l.size() + 1) + " items.");
            for (Object l1 : l)
            {
                System.out.print(l1 + ",");
            }
            System.out.println();
        }
    }
    
    @SuppressWarnings("unchecked")
    public List parse(String line)
    {
        List list = new ArrayList();
        Matcher m = csvRE.matcher(line);
        // For each field
        while (m.find())
        {
            String match = m.group();
            if (match == null)
            {
                break;
            }
            if (match.endsWith(","))
            {  // trim trailing ,
                match = match.substring(0, match.length() - 1);
            }
            if (match.startsWith("\""))
            { // assume also ends with
                match = match.substring(1, match.length() - 1);
            }
            if (match.length() == 0)
            {
                match = null;
            }
            list.add(match);
        }
        return list;
    }
}
