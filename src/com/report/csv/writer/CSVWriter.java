package com.report.csv.writer;

import com.report.csv.util.StringValidator;
import java.io.IOException;
import java.io.Writer;

/**
 * CSV Writer to write a row values into CSV cell implemented by CSVWriterTool
 * 
 * @author VIGNESH R
 */
public class CSVWriter implements CSVWriterTool
{

    /** Row Values */
    private static Object objValue;
    /** Object Type */
    private static String objName;
    /** Object values are format into String*/
    private static String _objValue;

    /** StringValidator class to find and parse the String from <b>String,Date,URL and Email</b> */
    private static StringValidator theString;

    /**
     * Constructor to get Object type.
     * 
     * @param obj
     *      Row Values.
     */
    public CSVWriter(Object obj)
    {
        this.objValue = obj;
        this.objName = obj.getClass().getSimpleName();

        this._objValue = String.valueOf(objValue).trim();
    }

    /**
     * Writer Operation using FileWriter Stream.
     *      - Using StringValidator Class to Validate the String by it's type.
     *      - Otherwise String types, format into the String and write into CSVFile.
     * 
     * @param csvWriter
     *      FileWriter Stream.
     * @throws IOException
     *  throws IOException for Write a data into File.
     */
    @Override
    public void write(Writer csvWriter) throws IOException
    {
        switch (objName)
        {
            case STRING:
                theString = new StringValidator(_objValue);
                csvWriter.write(theString.writeString());
                break;

            case INTEGER:
                csvWriter.write(_objValue);
                break;

            case SHORT:
                csvWriter.write(_objValue);
                break;

            case LONG:
                csvWriter.write(_objValue);
                break;

            case DOUBLE:
                csvWriter.write(_objValue);
                break;

            case FLOAT:
                csvWriter.write(_objValue);
                break;

            case BOOLEAN:
                csvWriter.write(_objValue);
                break;

            case BYTE:
                csvWriter.write(_objValue);
                break;

            case CHARACTER:
                csvWriter.write(_objValue);
                break;
        }
    }
}
