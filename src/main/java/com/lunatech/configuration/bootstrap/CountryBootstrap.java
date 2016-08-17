package com.lunatech.configuration.bootstrap;

import com.lunatech.common.utils.CSVParser;
import com.lunatech.domain.commands.CountryCommand;
import com.lunatech.services.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author Justin Seyvecou
 */

@Component
public class CountryBootstrap implements Bootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryBootstrap.class);

    @Autowired
    CountryService countryService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        int i = -1;
        try {
            InputStream fis = this.getClass().getClassLoader().getResourceAsStream("csv/countries.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                if (i > 0) {
                    String splitCountry[] = CSVParser.parseCSVLine(strLine);

                    CountryCommand country = new CountryCommand(Long.parseLong(splitCountry[0]), splitCountry[1], splitCountry[2]);
                    countryService.save(country);
                }
                i++;
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("BOOTSTRAP -> Loading " + i + " countries from CSV");
    }
}
