package com.report.csv.writer;

import java.io.IOException;
import java.io.Writer;

/**
 * Interface Class.
 * 
 * @author VIGNESH R
 */
public interface CSVWriterTool
{

    /**
     * Object Type String
     */
    public static final String STRING = "String";

    /**
     * Object Type Character
     */
    public static final String CHARACTER = "Character";

    /**
     * Object Type Integer
     */
    public static final String INTEGER = "Integer";

    /**
     * Object Type Byte
     */
    public static final String BYTE = "Byte";

    /**
     * Object Type Short
     */
    public static final String SHORT = "Short";

    /**
     * Object Type Long
     */
    public static final String LONG = "Long";

    /**
     * Object Type Double
     */
    public static final String DOUBLE = "Double";

    /**
     * Object Type Float
     */
    public static final String FLOAT = "Float";

    /**
     * Object Type Boolean
     */
    public static final String BOOLEAN = "Boolean";

    /**
     * Object Type Date
     */
    public static final String DATE = "Date";

    /**
     * Writer Operation using FileWriter Stream.
     *      - Using StringValidator Class to Validate the String by it's type.
     *      - Otherwise String types, format into the String and write into CSVFile.
     * 
     * @param writer
     *      FileWriter Stream.
     * @throws IOException
     *  throws IOException for Write a data into File.
     */
    public abstract void write(Writer writer) throws IOException;
}
