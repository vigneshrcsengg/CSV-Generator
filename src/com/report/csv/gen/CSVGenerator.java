package com.report.csv.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author VIGNESH R
 */
public class CSVGenerator
{

    private final Writer csvWriter;
    private final File csvFile;
    private final FileWriter csvFileWriter;

    private char[] lineDelim = new char[]
    {
        '\n'
    };

    private CSVHeader _header;
    private List<CSVRow> _listRows;
    private CSVRow _csvRow;

    private boolean closed;

    /**
     *
     * @param csvFileName
     * @param filePath
     * @param permit
     * @throws java.io.IOException
     */
    public CSVGenerator(String csvFileName, String filePath, boolean permit) throws IOException
    {
        closed = false;

        if (!new File(filePath).exists())
        {
            new File(filePath).mkdirs();
        }

        csvFile = new File(filePath + csvFileName);
        csvFileWriter = new FileWriter(csvFile);

        setPermit(permit);

        this.csvWriter = csvFileWriter;
        _listRows = new LinkedList<CSVRow>();
    }

    /**
     *
     * @param csvFileName
     * @param filePath
     * @param permit
     * @param lineDelim
     * @throws java.io.IOException
     */
    public CSVGenerator(String csvFileName, String filePath, boolean permit, char... lineDelim) throws IOException
    {
        this(csvFileName, filePath, permit);
        this.lineDelim = lineDelim;
    }

    /**
     *
     * @param columns
     * @throws CSVException
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
     *
     * @param index
     * @param value
     * @throws CSVException
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
     *
     * @param column
     * @param value
     * @throws CSVException
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
     *
     */
    public void next()
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
     *
     * @throws IOException
     */
    public void writeData() throws IOException
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
     *
     * @throws IOException
     */
    public void flush() throws IOException
    {
        csvWriter.flush();
    }

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
}
