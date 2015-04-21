package com.report.csv.util;

import java.text.DateFormat;
import java.text.ParseException;
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

    /**
     * Cell Value
     */
    private static String stringData;

    /**
     * CSVParser
     */
    public static CSVParser _parser;

    /**
     * Date Object
     */
    public static Date DATE = null;

    /**
     * Time
     */
    public static Date TIME = null;

    /**
     * Constructor.Validate the String is null or not.
     * <p>
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
     * Pass the String into CSVWriter after String Validation with <b>Email,URL
     * and Date</b>
     * <p>
     * @return cellData
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
                else if (isTime())
                {
                    stringData = _parser.parseTime(TIME);
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
     * Validate the String is Email type using EMAIL_PATTERN
     * <p>
     * @return true - Email, false - Normal String
     */
    public static boolean isEmail()
    {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(stringData);

        return matcher.matches();
    }

    /**
     * Validate the String is URL Type using URL_PATTERN
     * <p>
     * @return true - URL, false - Normal String
     */
    public static boolean isURL()
    {
        pattern = Pattern.compile(URL_PATTERN, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(stringData);

        return matcher.matches();
    }

    /**
     * Validate the String is Date using DATE_FORMATS.If String is date set the
     * value into DATE Object.
     * <p>
     * @return <b>true</b> - Date, <b>false</b> - Normal String
     */
    public static boolean isDate()
    {
        for (DateFormat dFormats : DATE_FORMATS)
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

    private static boolean isTime()
    {
        for (DateFormat tFormats : TIME_FORMATS)
        {
            try
            {
                tFormats.setLenient(false);
                TIME = tFormats.parse(stringData);
            }
            catch (ParseException e)
            {
                return false;
            }
            if (TIME != null)
            {
                break;
            }
        }
        return true;
    }

}
