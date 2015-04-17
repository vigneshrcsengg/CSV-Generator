package com.report.csv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private static String stringData;

    /**
     *
     */
    public static CSVParser _parser;

    /**
     *
     */
    public static Date DATE = null;

    /**
     *
     * @param STRING_VAL
     */
    public StringValidator(String STRING_VAL)
    {
        if (STRING_VAL == null)
        {
            STRING_VAL = "";
        }
        this.stringData = STRING_VAL;
    }

    /**
     *
     * @return
     */
    public static String writeString()
    {
        if (!stringData.isEmpty())
        {
            if (!isEmail() && !isURL())
            {
                _parser = new CSVParser(stringData);
                if (isDate())
                {
                    stringData = _parser.parseDate(DATE);
                }
                else
                {
                    stringData = _parser.sanitizeCSV();
                }
            }
        }
        return stringData;
    }

    /**
     *
     * @return
     */
    public static boolean isEmail()
    {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(stringData);
        
        return matcher.matches();
    }

    /**
     *
     * @return
     */
    public static boolean isURL()
    {
        pattern = Pattern.compile(URL_PATTERN, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(stringData);

        return matcher.matches();
    }

    /**
     *
     * @return
     */
    public static boolean isDate()
    {
        for (SimpleDateFormat dFormats : DATE_FORMATS)
        {
            try
            {
                dFormats.setLenient(false);
                DATE = dFormats.parse(stringData);
            }
            catch (ParseException e)
            {
                return false;
            }
            if (DATE != null)
            {
                break;
            }
        }
        return true;
    }

}
