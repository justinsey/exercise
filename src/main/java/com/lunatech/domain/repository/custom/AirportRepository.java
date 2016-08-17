package com.lunatech.domain.repository.custom;

import com.lunatech.domain.commands.AirportCommand;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Justin Seyvecou
 */
@Repository
public interface AirportRepository {
    List<AirportCommand> findAllByIso_Country(String iso_country, int page, int limit);

    long countAllByIso_Country(String iso_country);

    void save(List<AirportCommand> airportCommands);
}
