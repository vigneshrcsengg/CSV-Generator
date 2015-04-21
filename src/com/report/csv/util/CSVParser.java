package com.report.csv.util;

import java.util.Date;

/**
 * CSV Parser to parse the String data type with validate the Special Characters.
 * 
 * @author VIGNESH R
 */
public class CSVParser implements CSVParserUtil
{
    /** StringBuilder to construct the cellData with remove special or unwanted characters */
    private static StringBuilder resultBuilder;
    /** Contains cellData*/
    private static String cellData;

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Constructor
     * 
     * @param cellData
     *     String - Contains the Row of cell value.
     */
    @SuppressWarnings("static-access")
    public CSVParser(String cellData)
    {
        this.cellData = cellData;
        resultBuilder = new StringBuilder(cellData);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="String Parse Algorthim">
    /**
     * To parse the String with Special Characters.
     *
     * @return
     *      CSV Formatted Cell Value.
     */
    public static String sanitizeCSV()
    {
        int lastIndex = 0;

        while (resultBuilder.indexOf(QUOTE, lastIndex) >= 0)
        {
            int quoteInd = resultBuilder.indexOf(QUOTE, lastIndex);
            resultBuilder = replace(resultBuilder, quoteInd);
            lastIndex = quoteInd + 2;
        }

        char firstChar = cellData.charAt(0);
        char lastChar = cellData.charAt(cellData.length() - 1);

        if (cellData.contains(DELIMITER) || cellData.contains(NL)
                || Character.isWhitespace(firstChar) || Character.isWhitespace(lastChar))
        {
            resultBuilder.insert(0, QUOTE).append(QUOTE);
        }

        return resultBuilder.toString();
    }

    /**
     * Replace the Special Character or Error with the DOUBLE_QUOTE ("")
     *
     * @param resultBuilder
     *      The constructed cell value.
     * @param quoteInd
     *      Index position of error char or special char.
     * @return
     *      The Cell data.
     */
    public static StringBuilder replace(StringBuilder resultBuilder, int quoteInd)
    {
        resultBuilder.replace(quoteInd, quoteInd + 1, "\"\"");

        return resultBuilder;
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="DateTime Parse Algorthim">
    /**
     * Parse the Date into particular Formats.
     *
     * @param DATE
     *      Date object from StringValidator.
     * @return
     *      Parsed Date Format.
     */
    public static String parseDate(Date DATE)
    {
        //return String.format(ABV_MONTH, DATE)+"-"+String.format(DAY, DATE)+"-"+String.format(YEAR, DATE);
        //return String.format(YEAR, DATE)+"-"+String.format(ABV_MONTH, DATE)+"-"+String.format(DAY, DATE);
        //return "  "+String.format(DAY, DATE)+"-"+String.format(ABV_MONTH, DATE)+"-"+String.format(YEAR, DATE);
        return String.format(DAY, DATE) + "-" + String.format(ABV_MONTH, DATE) + "-" + String.format(YEAR, DATE);
    }

    /**
     * Parse the Date into particular Formats.
     *
     * @param TIME
     *      Convert the time format into 12 Hours time Stamp
     * @return
     *      Parsed Time Stamp Format.
     */
    public static String parseTime(Date TIME)
    {
        return String.format(_12HOUR, TIME);
    }
    
    //</editor-fold>
}
