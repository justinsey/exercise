package com.lunatech.configuration.bootstrap;

import com.lunatech.common.utils.CSVParser;
import com.lunatech.domain.commands.RunwayCommand;
import com.lunatech.services.RunwayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Justin Seyvecou
 */
@Component
public class RunwayBootstrap implements Bootstrap {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunwayBootstrap.class);

    @Autowired
    RunwayService runwayService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        int i = -1;
        try {
            InputStream fis = this.getClass().getClassLoader().getResourceAsStream("csv/runways.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String strLine;
            List<RunwayCommand> runways = new ArrayList<>();

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                if (i > 0) {
                    String splitRunway[] = CSVParser.parseCSVLine(strLine);

                    RunwayCommand runway = new RunwayCommand();
                    runway.setId(Long.parseLong(splitRunway[0]));
                    runway.setAirport_ref(Long.parseLong(splitRunway[1]));
                    runway.setAirport_ident(splitRunway[2]);
                    runway.setLength_ft(splitRunway[3].matches("-?\\d+") ? Long.parseLong(splitRunway[3]) : 0);
                    runway.setWidth_ft(splitRunway[4].matches("-?\\d+") ? Long.parseLong(splitRunway[4]) : 0);
                    runway.setSurface(splitRunway[5]);
                    runway.setLighted(Long.parseLong(splitRunway[6]));
                    runway.setClosed(Long.parseLong(splitRunway[7]));
                    try {
                        runway.setLe_ident(splitRunway[8]);
                    } catch (IndexOutOfBoundsException ex) {
                        runway.setLe_ident("");
                    }

                    runways.add(runway);
                }

                if ((i % 5000) == 0) {
                    LOGGER.info("BOOTSTRAP -> Importing runways " + i);
                }
                i++;
            }
            br.close();

            runwayService.save(runways);

        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("BOOTSTRAP -> " + i + " runways were imported from CSV");
    }
}
