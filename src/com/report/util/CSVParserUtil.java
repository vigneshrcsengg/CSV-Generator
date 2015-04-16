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
     *
     */
    public static final String DELIMITER = ",";

    /**
     *
     */
    public static final String QUOTE = "\"";

    /**
     *
     */
    public static final String NL = "\n";

    /**
     *
     */
    public static final String CR = "\r";

    /**
     *
     */
    public static final String SPACE = " ";

    /**
     *
     */
    public static final String TAB = "\t";

    /**
     *
     */
    public static final String POUND = "#";

    /**
     *
     */
    public static final String BACKSLASH = "\\";

    /**
     *
     */
    public static final String NULL = "\0";

    /**
     *
     */
    public static final String EMAIL_PATTERN = "^[\\w‐_\\.+]*[\\w‐_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    /**
     *
     */
    public static final String URL_PATTERN = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    /**
     *
     */
    public static List<SimpleDateFormat> DATE_FORMATS = new ArrayList<SimpleDateFormat>()
    {
        {
            add(new SimpleDateFormat("dd/MM/yyyy"));
            add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a"));
            
            add(new SimpleDateFormat("dd/MM/yy"));
            add(new SimpleDateFormat("dd/MM/yy HH:mm:ss a"));
        }
    };
}
