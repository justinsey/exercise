package com.lunatech.services;

import com.lunatech.domain.commands.CountryCommand;
import com.lunatech.domain.model.Country;
import com.lunatech.domain.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Justin Seyvecou
 */
@Service
public class CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryCommand> getAll() {
        return countryRepository.findAll().stream().map(CountryCommand::new).collect(Collectors.toList());
    }

    public void save(CountryCommand countryCommand) {
        countryRepository.save(new Country(countryCommand));
    }

}
