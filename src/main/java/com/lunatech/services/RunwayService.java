package com.lunatech.services;

import com.lunatech.domain.commands.RunwayCommand;
import com.lunatech.domain.repository.custom.RunwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Justin Seyvecou
 */
@Service
public class RunwayService {
    private RunwayRepository runwayRepository;

    @Autowired
    public RunwayService(RunwayRepository runwayRepository) {
        this.runwayRepository = runwayRepository;
    }

    public List<RunwayCommand> getAllByAirport_Ident(String airport_ident, int page, int limit) {
        return runwayRepository.findAllByAirport_Ident(airport_ident, page, limit);
    }

    public long countAllByAirport_Ident(String airport_ident) {
        return runwayRepository.countAllByAirport_Ident(airport_ident);
    }

    public void save(List<RunwayCommand> runwayCommands) {
        runwayRepository.save(runwayCommands);
    }
}
