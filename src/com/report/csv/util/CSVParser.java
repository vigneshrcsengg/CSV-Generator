package com.report.csv.util;

import java.util.Date;

/**
 *
 * @author VIGNESH R
 */
public class CSVParser implements CSVParserUtil
{

    private static StringBuilder resultBuilder;
    private static String cellData;

    /**
     *
     * @param cellData
     */
    @SuppressWarnings("static-access")
    public CSVParser(String cellData)
    {
        this.cellData = cellData;
        resultBuilder = new StringBuilder(cellData);
    }

    /**
     *
     * @return
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
     *
     * @param resultBuilder
     * @param quoteInd
     * @return
     */
    public static StringBuilder replace(StringBuilder resultBuilder, int quoteInd)
    {
        resultBuilder.replace(quoteInd, quoteInd + 1, "\"\"");

        return resultBuilder;
    }

    /**
     *
     * @param DATE
     * @return
     */
    public static String parseDate(Date DATE)
    {
        //return String.format(ABV_MONTH, DATE)+"-"+String.format(DAY, DATE)+"-"+String.format(YEAR, DATE);
        //return String.format(YEAR, DATE)+"-"+String.format(ABV_MONTH, DATE)+"-"+String.format(DAY, DATE);
        //return "  "+String.format(DAY, DATE)+"-"+String.format(ABV_MONTH, DATE)+"-"+String.format(YEAR, DATE);
        return String.format(DAY, DATE) + "-" + String.format(ABV_MONTH, DATE) + "-" + String.format(YEAR, DATE);
    }
}
