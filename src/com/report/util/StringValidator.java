package com.report.csv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author VIGNESH R
 */
public class StringValidator implements CSVParserUtil
{
    private static Pattern pattern;
    private static Matcher matcher;
    
    /**
     *
     * @param cellData
     * @return
     */
    public static boolean isEmail(StringBuilder cellData)
    {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(cellData);
        return matcher.matches();
    }
    
    /**
     *
     * @param cellData
     * @return
     */
    public static boolean isURL(StringBuilder cellData)
    {
        pattern = Pattern.compile(URL_PATTERN,Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(QUOTE);
        
        return matcher.matches();
    }
    
    /**
     *
     * @param cellData
     * @return
     */
    public static boolean isDate(String cellData)
    {
        if(cellData == null || cellData.toString().isEmpty())
        {
            return false;
        }
        
        for(SimpleDateFormat dFormats: DATE_FORMATS)
        {
            try 
            {
                dFormats.setLenient(false);
                CSVParser.DATE = dFormats.parse(cellData.toString());
            }
            catch (ParseException e)
            {
                return false;
            }
            if (CSVParser.DATE != null) 
            {
                break;
            }
        }
        
        return true;
    }
    
}
