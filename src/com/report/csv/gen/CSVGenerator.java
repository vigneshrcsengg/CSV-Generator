package com.report.csv.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

/**
 * CSV Generator is used to assign the jobs to Header, Row and Writer.
 * <p>
 * CSV Header - Collect the Header Information.
 * <p>
 * CSV Row    - Collect the Rows and  Columns Information.
 * <p>
 * CSV Writer - Perform File Write Operation via Write Stream.
 * 
 * @author VIGNESH R
 */
public class CSVGenerator
{
    /** Extension of CSV File (.csv)*/
    private static final String csvExt = ".csv";
    /** IO Writer Stream */
    private final Writer csvWriter;
    /** File Handler*/
    private final File csvFile;
    /** FileWriter */
    private final FileWriter csvFileWriter;
    /**Line Separator or Line Delimeter '\n'*/
    private char[] lineDelim = new char[]
    {
        '\n'
    };
    
    
    /** CSVHeader used to collect header information */
    private CSVHeader _header;
    /** List CSVRow used to collect and rows and index position of current row information*/
    private List<CSVRow> _listRows;
    /** CSVRow used to collect only rows information*/
    private CSVRow _csvRow;
    /** Provide a status of Header*/
    private boolean closed;

    /**
     * Constructor with Default Parameters.
     *      1.Construct the file path and file name for CSV.
     *      2.Generate the Directory path if not exist.
     *      3.Assign the FileWriter Stream in to Writer.
     *      4.Assign the File Permission.
     * 
     * @param csvFileName
     *          Name of the CSV File.
     * @param filePath
     *          Path of the given CSV File.
     *          <p>
     *          Example: <b>C:\\CSVFiles\\Generated\\...</b>.
     *          If the path not exist it will automatically created.
     * @param permit
     *          To set the File permissions.<p>
     *          If <b>true</b>, Strict the file <b>READONLY</b> file permission.
     *                          So, cannot overwrite or edit the file informations and contents.<p>
     *          If <b>false</b>, Strict the file <b>READ and WRITE</b> permission.
     * @throws java.io.IOException
     *          throws IOException
     */
    public CSVGenerator(String csvFileName, String filePath, boolean permit) throws IOException
    {
        closed = false;
        
        fileHandler(filePath);
                
        if(!csvFileName.endsWith(csvExt))
        {
            csvFile = new File(filePath + csvFileName+csvExt);
        }
        else
        {
            csvFile = new File(filePath + csvFileName);
        }
        csvFileWriter = new FileWriter(csvFile);

        setPermit(permit);

        this.csvWriter = csvFileWriter;
        _listRows = new LinkedList<CSVRow>();
    }

    /**
     * Constructor with Line Delimeter or Separator.
     *      1.Construct the file path and file name for CSV.
     *      2.Generate the Directory path if not exist.
     *      3.Assign the FileWriter Stream in to Writer.
     *      4.Assign the File Permission.
     *      5.Assign the delimiter for line separator.
     * 
     * @param csvFileName
     *          Name of the CSV File.
     * @param filePath
     *          Path of the given CSV File.
     *          <p>
     *          Example: <b>C:\\CSVFiles\\Generated\\...</b>.
     *          If the path not exist it will automatically created.
     * 
     * @param permit
     *          To set the File permissions.<p>
     *          If <b>true</b>, Strict the file <b>READONLY</b> file permission.
     *                          So, cannot overwrite or edit the file informations and contents.<p>
     *          If <b>false</b>, Strict the file <b>READ and WRITE</b> permission.
     * @param lineDelim
     *          The Default line delimiter is '\n' or else using System Property to get the line.separator.
     * @throws java.io.IOException
     *          throws IOException
     */
    public CSVGenerator(String csvFileName, String filePath, boolean permit, char... lineDelim) throws IOException
    {
        this(csvFileName, filePath, permit);
        this.lineDelim = lineDelim;
    }

    /**
     * Set the Header values into CSV File.
     * 
     * @param columns
     *        Using String[] array.
     * @throws CSVException
     *      throws CSVException
     */
    public void setHeader(String... columns) throws CSVException
    {
        if (closed)
        {
            throw new CSVException("cannot set header: CSVWriter is already closed");
        }
        else
        {
            _header = new CSVHeader(columns);
        }
    }

    /**
     * Set the row values using header index position.The content is based on header content.
     * 
     * @param index
     *        Integer value presents column position or index position based on header content.
     * @param value
     *        The value is declared as object.<p> 
     *        So the input values will be any <b>Primitive Data types and String</b>.<p>
     *        char,byte,short,int,long,double,float,boolean and String.
     *        <b> Date, Email, URL </b> is parsed by String Values using com.report.csv.util.CSVParser.
     * @throws CSVException
     *      throws CSVException
     */
    public void set(int index, Object value) throws CSVException
    {
        if (_csvRow == null)
        {
            _csvRow = new CSVRow(_header);
            _listRows.add(_csvRow);
            closed = true;
        }

        _csvRow.set(index, value);
    }

   /**
     * Set the row values using header column content.
     * 
     * @param column
     *        String represent the Header Values.
     * @param value
     *        The value is declared as object.<p> 
     *        So the input values will be any <b>Primitive Data types and String</b>.<p>
     *        char,byte,short,int,long,double,float,boolean and String.
     *        <b> Date, Email, URL </b> is parsed by String Values using com.report.csv.util.CSVParser.
     * @throws CSVException
     *      throws CSVException
     */
    public void set(String column, Object value) throws CSVException
    {
        int index = _header.getColumnIndex(column);

        if (index < 0)
        {
            throw new CSVException("unknown column: " + column);
        }
        else
        {
            set(index, value);
        }
    }

    /**
     * Organize the Row values based on Header Content.
     * @throws CSVException
     *  throws CSVException with Message.
     */
    public void next() throws CSVException
    {
        if (_csvRow == null)
        {
            _csvRow = new CSVRow(_header);
            _listRows.add(_csvRow);
            closed = true;
        }
        _csvRow = new CSVRow(_header);
        _listRows.add(_csvRow);
    }

    /**
     * Performs the Write Operation using FileWriter Stream.
     * 
     * @throws IOException
     *  throws IOException
     * @throws CSVException
     *  throws CSVException with exception message.
     */     
    public void writeData() throws IOException, CSVException
    {
        _header.toWriter(csvWriter);
        csvWriter.write(lineDelim);

        boolean firstRow = true;
        for (CSVRow row : _listRows)
        {
            if (firstRow)
            {
                firstRow = false;
            }
            else
            {
                csvWriter.write(lineDelim);
            }

            row.toWriter(csvWriter);
        }
    }

    /**
     * Close the File and Writer Stream.
     * 
     * @throws IOException
     *  throws IOException
     */
    public void flush() throws IOException
    {
        csvWriter.flush();
        csvWriter.close();
    }
    
    /**
     * Set the File Permission.
     * 
     * @param permits 
     *          To set the File permissions.<p>
     *          If <b>true</b>, Strict the file <b>READONLY</b> file permission.
     *                          So, cannot overwrite or edit the file informations and contents.<p>
     *          If <b>false</b>, Strict the file <b>READ and WRITE</b> permission.
     */
    private void setPermit(boolean permits)
    {
        if (permits == true)
        {
            csvFile.setReadOnly();
        }
        else if (permits == false)
        {
            csvFile.setReadable(true);
            csvFile.setWritable(true);
        }
    }
    /**
     * Check the directory path is exist or not.
     * 
     * @param filePath 
     *      File Path
     */
    private void fileHandler(String filePath)
    {
        if (!new File(filePath).exists())
        {
            new File(filePath).mkdirs();
        }
    }
}
