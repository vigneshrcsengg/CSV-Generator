package com.report.csv.gen;

import com.report.csv.util.CSVParserUtil;
import com.report.csv.writer.CSVWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Collect the Row Values based on Header Position and write into CSV File using CSVWriter.
 * 
 * @author VIGNESH R
 */
public class CSVRow implements CSVParserUtil
{

    /** Performs a Writer operation using FileWriter Stream  */
    private CSVWriter _writer;
    /** To store the Row Values*/
    private Object[] _data;
    /** State of row values is parallel to corresponding header content or not*/
    private boolean[] isSet;

    /**
     * Constructor - Get the Object values using header column count and set the state of value is present or not.
     * 
     * @param header
     *          Header Values.
     * @throws CSVException 
     *      throw CSVException.
     */
    CSVRow(CSVHeader header) throws CSVException
    {
        if(header == null)
        {
            throw new CSVException("Header Content Values are Null.");
        }
        else
        {
            _data = new Object[header.getColumnCount()];
            isSet = new boolean[header.getColumnCount()];
        }
    }

    /**
     * Set the index position and Object Value.Set the state of data present.
     * 
     * @param index
     *          int - index position
     * @param value
     *          Object value maybe a primitive data types or String(includes String,Email,URL,Date)
     * @throws CSVException
     *      throw CSVException.
     */
    public void set(int index, Object value) throws CSVException
    {
        if (index < 0 || index >= _data.length)
        {
            throw new CSVException("index out of range: " + index);
        }

        _data[index] = value;
        isSet[index] = true;
    }

    /**
     * Write the row values into CSV File pass to the Writer Class CSVWriter.
     * 
     * @param csvWriter
     *      FileWriter Stream.
     */
    public void toWriter(Writer csvWriter) throws CSVException
    {
        try
        {
            for (int i = 0; i < _data.length; i++)
            {
                if (i != 0)
                {
                    csvWriter.write(DELIMITER);
                }

                if (isSet[i])
                {
                    if (_data[i] == null)
                    {
                        _data[i] = "";
                    }

                    _writer = new CSVWriter(_data[i]);
                    _writer.write(csvWriter);
                }
            }
        }
        catch (IOException ex)
        {
             throw new CSVException("IO Exception during CSVRow Writer - "+ex.getMessage());
        }
        catch (NullPointerException e)
        {
            throw new CSVException("NullPointerException during CSVRow Writer - "+e.getMessage());
        }
    }
}
