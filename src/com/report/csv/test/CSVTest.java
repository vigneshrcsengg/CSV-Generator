package com.report.csv.test;

import com.report.csv.gen.CSVException;
import com.report.csv.gen.CSVGenerator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIGNESH R
 */
public class CSVTest
{

    /**
     * 
     * @param args
     * @throws IOException
     * @throws java.io.UnsupportedEncodingException
     * @throws com.report.csv.gen.CSVException
     */
    public static void main(String[] args) throws IOException, UnsupportedEncodingException, CSVException
    {
        String csvFilePath = "C:\\Users\\Prasanth R\\Desktop\\DirectoryCreated\\cell\\";
        String csvFileName = "REPORT_CSV_GENERATOR";

        CSVGenerator csv = new CSVGenerator(csvFileName, csvFilePath, false);

        try
        {   
            String usingFlow = "checkDatatype";

            String[] header =
            {
                "EMP_ID", "EMP_NAME", "EMP_DOB", "EMP_DESG", "EMP_SALARY"
            };
            String[] datatypeHeader =
            {
                "STRING_STRICT", "STRING", "INTEGER", "CHAR", "BYTE", "SHORT", "LONG", "DOUBLE", "FLOAT", "BOOLEAN", "URL", "EMAIL", "DATE","TIME"
            };

            String[] _1 =
            {
                "1", "2", "3", "4", "5", "6"
            };
            String[] _2 =
            {
                "Franklin", "Bruce", "Jhon", "Wilson", "Mansion", "Kelli"
            };
            String[] _3 =
            {
                "1992-09-09", "1991-08-23", "", "1989-08-16", "", "1977-09-12"
            };
            String[] _4 =
            {
                "Software Engineer", "CEO", "Manager", "Admin", "Sweeper", "Senior"
            };
            String[] _5 =
            {
                "2000", "2000", "2000", "2000", "2000", "3000"
            };

            Employe _1emp = new Employe(123, "Raja", "1991-09-16", "Software Developer", 12000);
            Employe _2emp = new Employe(124, "Mani", "1989-07-18", "Human Resource", 11000);
            Employe _3emp = new Employe(125, "Chola", "1992-08-05", "Manager", 20000);
            Employe _4emp = new Employe(126, "Pandi", "1991-09-09", "CEO", 20000);
            Employe _5emp = new Employe(127, "Chakara", "1985-03-14", "Administrator", 1000);
            Employe _6emp = new Employe(129, "Arokya", "2001-09-09", "IT Manager", 9000);

            List<Employe> employ = new ArrayList<>();

            employ.add(_1emp);
            employ.add(_2emp);
            employ.add(_3emp);
            employ.add(_4emp);
            employ.add(_5emp);
            employ.add(_6emp);

            String[] STRING_STRICT =
            {
                "random \" doublequote", "comma,separated", "Two\n" + "lines", " leading space, and a comma", "\"leading quote, comma", "_space, comma\n" + "second line, and double quote\""
            };
            String[] STRING =
            {
                "String1", "String2", "String3", "String4", "String5", null
            };
            int[] INT =
            {
                1, 12, 123, 1234, 12345, 123_456
            };
            char[] CHAR =
            {
                '@', '#', 'A', '\r', '\n', '\0'
            };
            byte[] BYTE =
            {
                -127, -1, 0, 1, 56, 127
            };
            short[] SHORT =
            {
                -1233, 19, 23, 9832, -9282, 923
            };
            long[] LONG =
            {
                123, 123_23L, 23, 939_923_223, 12, -12_09
            };
            double[] DOUBLE =
            {
                12.44d, -273.238d, 2378283982932.823672368238d, 2837.9, 4576723.928263753623232873623d, 0
            };
            float[] FLOAT =
            {
                9182.4f, 2323.232323f, 23f, -92723723.792826823f, -2773889222.872672f, 28328282.8f
            };
            boolean[] BOOL =
            {
                true, false, true, false, true, false
            };
            
            String[] URL =
            {
                "http://www.google.com/", "http://www.google.co.in", "https://codejava.org", "ftp://journaldev.in", "http://github.com/", "www.freelancer.com"
            };
            String[] EMAIL =
            {
                "vignesh.prasanth@gmail.com", "vigneshr.csengg", "123x344@gmail.com", "vig@gmail.com", "90j_89@gmail.com", "six@gmail.com"
            };

            String[] DATE =
            {
                "16/09/2014", "15/04/1991", "20/4/1989", "13/09/1989", "22/12/2045", "28/02/2014"
            };
            
            String[] TIME = 
            {
                "01:23:12","23:09:23","02:34:22","12:33:21","00:09:12","13:55:09"
            };

            switch (usingFlow)
            {
                case "List":
                    csv.setHeader(header);
                    for (Employe _employ : employ)
                    {
                        csv.set("EMP_ID", _employ.getempId());
                        csv.set("EMP_NAME", _employ.getempName());
                        csv.set("EMP_DOB", _employ.getempDOB());
                        csv.set("EMP_DESG", _employ.getempDesg());
                        csv.set("EMP_SALARY", _employ.getempSalary());
                        csv.next();
                    }
                    break;
                case "Array":
                    csv.setHeader(header);
                    for (int i = 0; i < _1.length; i++)
                    {
                        csv.set(0, _1[i]);
                        csv.set(1, _2[i]);
                        csv.set(2, _3[i]);
                        csv.set(3, _4[i]);
                        csv.set(4, _5[i]);
                        csv.next();
                    }
                    break;
                case "checkDatatype":
                    csv.setHeader(datatypeHeader);
                    for (int i = 0; i < STRING.length; i++)
                    {
                        csv.set(0, STRING_STRICT[i]);
                        csv.set(1, STRING[i]);
                        csv.set(2, INT[i]);
                        //csv.set(3, CHAR[i]);
                        csv.set(4, BYTE[i]);
                        csv.set(5, SHORT[i]);
                        csv.set(6, LONG[i]);
                        csv.set(7, DOUBLE[i]);
                        csv.set(8, FLOAT[i]);
                        csv.set(9, BOOL[i]);
                        csv.set(10, URL[i]);
                        csv.set(11, EMAIL[i]);
                        csv.set(12, DATE[i]);
                        csv.set(13, TIME[i]);
                        
                        csv.next();
                    }
                    break;
            }

            csv.writeData();
        }
        catch (CSVException ex)
        {
        }
        finally
        {
            csv.close();
        }
    }
}
