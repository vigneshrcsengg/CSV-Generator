package com.report.csv.gen;

import com.report.csv.util.CSVParserUtil;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author VIGNESH R
 */
public class CSVHeader implements CSVParserUtil
{

    private List<String> columns;
    private HashMap<String, Integer> columnIndexes;

    /**
     * Constructor Initialize the LinkedList and HashMap.
     */
    public CSVHeader()
    {
        columns = new LinkedList<String>();
        columnIndexes = new HashMap<String, Integer>();
    }

    /**
     * Constructor
     * <p>
     * @param columns
     */
    public CSVHeader(String... columns)
    {
        this();
        for (int i = 0; i < columns.length; i++)
        {
            this.columns.add(columns[i]);
            columnIndexes.put(columns[i], i);
        }
    }

    /**
     * Column Index
     * <p>
     * @param column
     * @return Current specified column index.
     */
    public int getColumnIndex(String column)
    {
        Integer index = columnIndexes.get(column);
        if (index != null)
        {
            return index;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Column Count
     * <p>
     * @return columns size.
     */
    public int getColumnCount()
    {
        return columns.size();
    }

    /**
     * Perform a write function using Writer.
     * <p>
     * @param header
     */
    public void toWriter(Writer header)
    {
        try
        {
            boolean firstRow = true;

            for (String column : columns)
            {
                if (firstRow)
                {
                    firstRow = false;
                }
                else
                {
                    header.write(DELIMITER);
                }

                header.write(QUOTE);
                header.write(column);
                header.write(QUOTE);
            }
        }
        catch (IOException ex)
        {
            try
            {
                throw new CSVException("Error writing Header" + ex.getMessage());
            }
            catch (CSVException e)
            {

            }
        }
    }
}
