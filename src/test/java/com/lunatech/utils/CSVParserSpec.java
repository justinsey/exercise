package com.lunatech.utils;

import com.lunatech.common.utils.CSVParser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author Justin Seyvecou
 */
public class CSVParserSpec {

    @Test
    public void parseCSVLineTest() {
        String line = "6523,\"00A\",\"heliport\",\"Total Rf Heliport\",40.07080078125,-74.93360137939453,11,\"NA\",\"US\",\"US-PA\",\"Bensalem\",\"no\",\"00A\",,\"00A\",,,";
        String parsedline[] = CSVParser.parseCSVLine(line);

        then:
        assertEquals(parsedline[0], "6523");
        assertEquals(parsedline[1], "00A");
        assertEquals(parsedline[2], "heliport");
    }
}
