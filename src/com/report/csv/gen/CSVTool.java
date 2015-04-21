package com.report.csv.gen;

import java.io.IOException;

/**
 *
 * @author VIGNESH R
 */
public interface CSVTool
{
    /**
     * Extension of CSV File (.csv)
     */
    public static final String csvExt = ".csv";
    
    public static final String encoding = System.getProperty("file.encoding");

     /**
     * Set the Header values into CSV File.
     * <p>
     * @param columns Using String[] array.
     * @throws CSVException throws CSVException
     */
    public abstract void setHeader(String... columns) throws CSVException;

    /**
     * Set the row values using header index position.The content is based on
     * header content.
     * <p>
     * @param index Integer value presents column position or index position
     *              based on header content.
     * @param value The value is declared as object.<p>
     * So the input values will be any <b>Primitive Data types and
     * String</b>.<p>
     * char,byte,short,int,long,double,float,boolean and String.
     * <b> Date, Email, URL </b> is parsed by String Values using
     * com.report.csv.util.CSVParser.
     * @throws CSVException throws CSVException
     */
    public abstract void set(int index, Object value) throws CSVException;

    /**
     * Set the row values using header column content.
     * <p>
     * @param column String represent the Header Values.
     * @param value The value is declared as object.<p>
     * So the input values will be any <b>Primitive Data types and
     * String</b>.<p>
     * char,byte,short,int,long,double,float,boolean and String.
     * <b> Date, Email, URL </b> is parsed by String Values using
     * com.report.csv.util.CSVParser.
     * @throws CSVException throws CSVException
     */
    public abstract void set(String column, Object value) throws CSVException;

    /**
     * Organize the Row values based on Header Content.
     * <p>
     * @throws CSVException throws CSVException with Message.
     */
    public abstract void next() throws CSVException;

    /**
     * Performs the Write Operation using FileWriter Stream.
     * <p>
     * @throws IOException throws IOException
     * @throws CSVException throws CSVException with exception message.
     */
    public abstract void writeData() throws IOException, CSVException;

    /**
     * Close the File and Writer Stream.
     * <p>
     * @throws IOException throws IOException
     */
    public abstract void close() throws IOException;
}
