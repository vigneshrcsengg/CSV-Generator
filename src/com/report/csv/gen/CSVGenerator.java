package com.report.csv.gen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

/**
 * CSV Generator is used to assign the jobs to Header, Row and Writer.
 * <p>
 * CSV Header - Collect the Header Information.
 * <p>
 * CSV Row - Collect the Rows and Columns Information.
 * <p>
 * CSV Writer - Perform File Write Operation via Write Stream.
 * <p>
 * @author VIGNESH R
 */
public class CSVGenerator implements CSVTool
{

    /**
     * File Handler
     */
    private static File csvFile;
    /**
     * FileOutputStream
     */
    private static FileOutputStream fout;
    /**
     * IO Writer Stream
     */
    private final Writer csvWriter;
    /**
     * Line Separator or Line Delimeter '\n'
     */
    private char[] lineDelim = new char[]
    {
        '\n'
    };

    /**
     * CSVHeader used to collect header information
     */
    private CSVHeader _header;
    /**
     * List CSVRow used to collect and rows and index position of current row
     * information
     */
    private List<CSVRow> _listRows;
    /**
     * CSVRow used to collect only rows information
     */
    private CSVRow _csvRow;
    /**
     * Provide a status of Header
     */
    private boolean closed;
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">    
    /**
     * Constructor with Default Parameters.
     * <p>
     * 1.Construct the file path and file name for CSV.
     * <p>
     * 2.Generate the Directory path if not exist.
     * <p>
     * 3.Assign the FileWriter Stream in to Writer.
     * <p>
     * 4.Assign the File Permission.
     * <p>
     * @param csvFileName Name of the CSV File.
     * @param filePath    Path of the given CSV File.
     * <p>
     * Example: <b>C:\\CSVFiles\\Generated\\...</b>. If the path not exist it
     * will automatically created.
     * @param permit      To set the File permissions.<p>
     * If <b>true</b>, Strict the file <b>READONLY</b> file permission. So,
     * cannot overwrite or edit the file informations and contents.<p>
     * If <b>false</b>, Strict the file <b>READ and WRITE</b> permission.
     * @throws java.io.IOException throws IOException
     * @throws java.io.UnsupportedEncodingException
     * @throws com.report.csv.gen.CSVException
     */
    public CSVGenerator(String csvFileName, String filePath, boolean permit) throws IOException, UnsupportedEncodingException, CSVException
    {
        closed = false;

        this.csvWriter = fileHandler(filePath, csvFileName);

        setPermit(permit);

        _listRows = new LinkedList<CSVRow>();
    }

    /**
     * Constructor with Line Delimeter or Separator. 1.Construct the file path
     * and file name for CSV. 2.Generate the Directory path if not exist.
     * 3.Assign the FileWriter Stream in to Writer. 4.Assign the File
     * Permission. 5.Assign the delimiter for line separator.
     * <p>
     * @param csvFileName Name of the CSV File.
     * @param filePath    Path of the given CSV File.
     * <p>
     * Example: <b>C:\\CSVFiles\\Generated\\...</b>. If the path not exist it
     * will automatically created.
     * <p>
     * @param permit      To set the File permissions.<p>
     * If <b>true</b>, Strict the file <b>READONLY</b> file permission. So,
     * cannot overwrite or edit the file informations and contents.<p>
     * If <b>false</b>, Strict the file <b>READ and WRITE</b> permission.
     * @param lineDelim   The Default line delimiter is '\n' or else using
     *                    System Property to get the line.separator.
     * @throws java.io.IOException throws IOException
     * @throws java.io.UnsupportedEncodingException
     * @throws com.report.csv.gen.CSVException
     */
    public CSVGenerator(String csvFileName, String filePath, boolean permit, char... lineDelim) throws IOException, UnsupportedEncodingException, CSVException
    {
        this(csvFileName, filePath, permit);
        this.lineDelim = lineDelim;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="File Handlers Algorthims">
    /**
     * To handle the File Functions.
     * <p> - File Encoding
     * <p> - Check File Exist.
     * <p> - Check Directory path is exist and file extension name.
     * <p>
     * @param filePath File Path    
     */
    private static OutputStreamWriter fileHandler(String filePath, String csvFileName) throws UnsupportedEncodingException, CSVException
    {
        try
        {
            if (!new File(filePath).exists())
            {
                new File(filePath).mkdirs();
            }

            if (!csvFileName.endsWith(csvExt))
            {
                csvFile = new File(filePath + csvFileName + csvExt);
            }
            else
            {
                csvFile = new File(filePath + csvFileName);
            }

            fout = new FileOutputStream(csvFile);
        }
        catch (FileNotFoundException ex)
        {
            throw new CSVException("File Exception "+ex.getMessage());
        }

        return new OutputStreamWriter(fout, encoding);
    }
    
    /**
     * Set the File Permission.
     * <p>
     * @param permits To set the File permissions.<p>
     * If <b>true</b>, Strict the file <b>READONLY</b> file permission. So,
     * cannot overwrite or edit the file informations and contents.<p>
     * If <b>false</b>, Strict the file <b>READ and WRITE</b> permission.
     */
    private static void setPermit(boolean permits)
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Interface CSVTool Inherited Methods">
    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public void close() throws IOException
    {
        csvWriter.flush();
        csvWriter.close();
    }
    
    // </editor-fold>
}
