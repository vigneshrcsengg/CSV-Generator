package com.report.csv.writer;

import com.report.csv.util.StringValidator;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author VIGNESH R
 */
public class CSVWriter implements CSVWriterTool
{

    private static Object objValue;
    private static String objName;
    private static String _objValue;

    private static StringValidator theString;

    /**
     *
     * @param obj
     */
    public CSVWriter(Object obj)
    {
        this.objValue = obj;
        this.objName = obj.getClass().getSimpleName();

        this._objValue = String.valueOf(objValue).trim();
    }

    /**
     *
     * @param csvWriter
     * @throws IOException
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
