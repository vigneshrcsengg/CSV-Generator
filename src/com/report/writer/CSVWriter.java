package com.report.csv.writer;

import com.report.csv.util.CSVParser;
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
    private static CSVParser _parser;
    
    /**
     *
     * @param obj
     */
    public CSVWriter(Object obj)
    {
        this.objValue = obj;
        this.objName = obj.getClass().getSimpleName();
    }
    
    /**
     *
     * @param csvWriter
     * @throws IOException
     */
    @Override
    public void write(Writer csvWriter) throws IOException
    {
        switch(objName)
        {
            case STRING:        _parser = new CSVParser(String.valueOf(objValue));
                                csvWriter.write(_parser.sanitizeCSV());
                                break;
            
            case INTEGER:       csvWriter.write(String.valueOf(objValue));
                                break;
                
            case SHORT:         csvWriter.write(String.valueOf(objValue));
                                break;
                
            case LONG:          csvWriter.write(String.valueOf(objValue));
                                break;
                
            case DOUBLE:        csvWriter.write(String.valueOf(objValue));
                                break;
                
            case FLOAT:         csvWriter.write(String.valueOf(objValue));
                                break;
                
            case BOOLEAN:       csvWriter.write(String.valueOf(objValue));
                                break;
                
            case BYTE:          csvWriter.write(String.valueOf(objValue));
                                break;
                
            case CHARACTER:     csvWriter.write(String.valueOf(objValue));
                                break;
        }
    }
}
