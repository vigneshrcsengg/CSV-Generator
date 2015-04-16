package com.report.csv.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author VIGNESH R
 */
public class CSVParser implements CSVParserUtil
{
    private static StringValidator theString;
    private static StringBuilder resultBuilder;
    
    private static String cellData;
    
    /**
     *
     */
    public static Date DATE = null;
    /**
     *
     * @param cellData
     */
    @SuppressWarnings("static-access")
    public CSVParser(String cellData)
    {
        this.cellData = cellData;
        resultBuilder = new StringBuilder(cellData.trim());
    }
    
    /**
     *
     * @return
     */
    public static String sanitizeCSV()
    {   
        theString = new StringValidator();
        
        if(!theString.isEmail(resultBuilder) && !theString.isURL(resultBuilder) && !cellData.isEmpty())
        {
            if(theString.isDate(cellData))
            {
                cellData = formatDate(DATE);
            }
            else
            {
                resultBuilder = parseString(cellData);
            }
        }
        
        return resultBuilder.toString();
    }
    
    
    public static StringBuilder parseString(String cellData)
    {
        int lastIndex = 0;

        while (resultBuilder.indexOf(QUOTE, lastIndex) >= 0)
        {
            int quoteInd = resultBuilder.indexOf(QUOTE, lastIndex);
            resultBuilder = replace(resultBuilder,quoteInd);
            lastIndex = quoteInd + 2;
        }
        
        char firstChar = cellData.charAt(0);
        char lastChar = cellData.charAt(cellData.length()-1);
        
        if (cellData.contains(DELIMITER) || cellData.contains(NL)
                || Character.isWhitespace(firstChar) || Character.isWhitespace(lastChar)) 
        {
            resultBuilder.insert(0, QUOTE).append(QUOTE);
        }
        
        return resultBuilder;
    }
    
    public static StringBuilder replace(StringBuilder resultBuilder, int quoteInd)
    {
        resultBuilder.replace(quoteInd, quoteInd + 1, "\"\"");
        
        return resultBuilder;
    }
    
    public static String formatDate(Date DATE)
    {
        DateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
        
        return sdf.format(DATE);
    }
}

