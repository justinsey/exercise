package com.lunatech.controller;

import com.lunatech.domain.commands.CountryCommand;
import com.lunatech.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Justin Seyvecou
 */
@RestController
public class CountryController {

    @Autowired
    CountryService countryService;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public List<CountryCommand> index() {
        return countryService.getAll();
    }

}