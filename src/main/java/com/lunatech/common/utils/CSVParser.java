package com.lunatech.common.utils;

import java.util.regex.Pattern;

/**
 * @author Justin Seyvecou
 */
public class CSVParser {
    public static String[] parseCSVLine(String line) {
        Pattern p =
                Pattern.compile(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
        String[] fields = p.split(line);

        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].replace("\"", "");
        }
        return fields;
    }
}
