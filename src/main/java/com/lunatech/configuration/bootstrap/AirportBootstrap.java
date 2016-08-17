package com.lunatech.configuration.bootstrap;

import com.lunatech.common.utils.CSVParser;
import com.lunatech.domain.commands.AirportCommand;
import com.lunatech.domain.commands.CountryCommand;
import com.lunatech.services.AirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Justin Seyvecou
 */
@Component
public class AirportBootstrap implements Bootstrap {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirportBootstrap.class);

    @Autowired
    AirportService airportService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        int i = -1;
        try {
            InputStream fis = this.getClass().getClassLoader().getResourceAsStream("csv/airports.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String strLine;
            List<AirportCommand> airports = new ArrayList<>();

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                if (i > 0) {
                    String splitAirport[] = CSVParser.parseCSVLine(strLine);

                    AirportCommand airport = new AirportCommand();
                    airport.setId(Long.parseLong(splitAirport[0]));
                    airport.setIdent(splitAirport[1]);
                    airport.setType(splitAirport[2]);
                    airport.setName(splitAirport[3]);
                    airport.setLatitude_deg(Double.parseDouble(splitAirport[4]));
                    airport.setLongitude_deg(Double.parseDouble(splitAirport[5]));
                    airport.setIso_country(splitAirport[8]);

                    airports.add(airport);
                }

                if((i % 5000) == 0) {
                    LOGGER.info("BOOTSTRAP -> Importing airports " + i);
                }
                i++;
            }
            br.close();

            airportService.save(airports);

        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("BOOTSTRAP -> " + i + " airports were imported from CSV");
    }
}
