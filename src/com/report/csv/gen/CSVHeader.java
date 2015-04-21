package com.report.csv.gen;

import com.report.csv.util.CSVParserUtil;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Collect the Header Information from CSV Generator Class.Organize the header and write into CSV File.
 * 
 * @author VIGNESH R
 */
public class CSVHeader implements CSVParserUtil
{

    /** Store the Header Index Position*/
    private List<String> columns;
    /** Store the Header values with key pair*/
    private HashMap<String, Integer> columnIndexes;
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default Constructor Initialize the LinkedList and HashMap using String Object.
     */
    public CSVHeader()
    {
        columns = new LinkedList<String>();
        columnIndexes = new HashMap<String, Integer>();
    }

    /**
     * Constructor
     *  - Add the Header index position into LinkedList.
     *  - Add the Header Vales into Hash Map with keys.
     * 
     * @param columns
     *          Header Contents.
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
    //</editor-fold>

    /**
     * Get the Column Index position using Header Content.
     * 
     * @param column
     *          Header Content.
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
     * Count the Header.
     * 
     * @return Number of header presents by integer value in columns size.
     */
    public int getColumnCount()
    {
        return columns.size();
    }

    // <editor-fold defaultstate="collapsed" desc="Header Writer Algorthim">
    /**
     * Perform a Write Stream Operation using FileWriter.
     * 
     * @param header
     *          Write the header Values into CSV File.
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
    // </editor-fold>
}
