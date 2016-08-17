package com.lunatech.domain.repository.custom;

import com.lunatech.domain.commands.RunwayCommand;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Justin Seyvecou
 */
@Repository
public interface RunwayRepository {
    List<RunwayCommand> findAllByAirport_Ident(String airport_ident, int page, int limit);

    long countAllByAirport_Ident(String airport_ident);

    void save(List<RunwayCommand> runwayCommands);
}
