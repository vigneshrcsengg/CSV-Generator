package com.report.csv.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIGNESH R
 */
public interface CSVParserUtil
{

    /**
     * Delimiter presents the separator CSV cell data.
     */
    public static final String DELIMITER = ",";

    /**
     * Double quote
     */
    public static final String QUOTE = "\"";

    /**
     * New line Delimiter
     */
    public static final String NL = "\n";

    /**
     * Carriage Return. If its is present in String means its goto next to th line.
     */
    public static final String CR = "\r";

    /**
     * Space
     */
    public static final String SPACE = " ";

    /**
     * Tab Space.
     */
    public static final String TAB = "\t";

    /**
     * The CSV cell data present in POUND if the format is incorrect.
     */
    public static final String POUND = "#";

    /**
     * Backslash
     */
    public static final String BACKSLASH = "\\";

    /**
     * Null or Esc value.
     */
    public static final String NULL = "\0";
    
    /**
     * EMPTY VALUE
     */
    public static final String EMPTY = "";

    /**
     * Regular Expression present the EMAIL_PATTERN to validate the String is Email or not
     */
    public static final String EMAIL_PATTERN = "^[\\w‐_\\.+]*[\\w‐_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    /**
     * Regular Expression present the URL_PATTERN to validate the String is URL type or not.
     */
    public static final String URL_PATTERN = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    /**
     * Validate the String is Date or not using DateFormats.
     */
    public static List<SimpleDateFormat> DATE_FORMATS = new ArrayList<SimpleDateFormat>()
    {
        private static final long serialVersionUID = 1L;
        
        {
            add(new SimpleDateFormat("dd/MM/yy"));
            add(new SimpleDateFormat("dd/MM/yy HH:mm:ss a"));
            
            add(new SimpleDateFormat("dd-MM-yyyy"));
            add(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a"));            
        }
    };
    
    public static List<SimpleDateFormat> TIME_FORMATS = new ArrayList<SimpleDateFormat>()
    {
        private static final long serialVersionUID = 1L;
        {
            add(new SimpleDateFormat("HH:mm:ss"));
            add(new SimpleDateFormat("HH:mm:ss a"));
        }
    };

    /**
     * Represent the day of Date Object.
     */
    public static final String DAY = "%td";

    /**
     * Represent the Shorted Month format of Date Object.
     */
    public static final String ABV_MONTH = "%tb";

    /**
     * Represent the Year of Date Object.
     */
    public static final String YEAR = "%tY";

    /**
     * Represent the 24 Hour Time stamp of Date Object
     */
    public static final String _24HOUR = "%tT";

    /**
     * Represent the 12 Hour Time stamp of Date Object.
     */
    public static final String _12HOUR = "%tr";
}
