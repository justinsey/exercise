package com.lunatech.services;

import com.lunatech.domain.commands.AirportCommand;
import com.lunatech.domain.repository.custom.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Justin Seyvecou
 */
@Service
public class AirportService {
    private AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }


    public List<AirportCommand> getAllByCountryCode(String iso_country, int page, int limit) {
        return airportRepository.findAllByIso_Country(iso_country, page, limit);
    }

    public long countAllByCountryCode(String iso_country) {
        return airportRepository.countAllByIso_Country(iso_country);
    }

    public void save(List<AirportCommand> airportCommands) {
        airportRepository.save(airportCommands);
    }
}
