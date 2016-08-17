package com.lunatech.controller;

import com.lunatech.domain.commands.AirportCommand;
import com.lunatech.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Justin Seyvecou
 */
@RestController
public class AirportController {
    @Autowired
    private AirportService airportService;


    @RequestMapping(value = "/airports/{code}", method = RequestMethod.GET)
    public List<AirportCommand> index(@PathVariable String code,
                                      @RequestParam int page,
                                      @RequestParam int limit) {
        return airportService.getAllByCountryCode(code, page, limit);
    }

    @RequestMapping(value = "/airports/count/{code}", method = RequestMethod.GET)
    public long index(@PathVariable String code) {
        return airportService.countAllByCountryCode(code);
    }

}
