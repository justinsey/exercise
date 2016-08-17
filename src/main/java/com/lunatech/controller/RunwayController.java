package com.lunatech.controller;

import com.lunatech.domain.commands.RunwayCommand;
import com.lunatech.services.RunwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Justin Seyvecou
 */
@RestController
public class RunwayController {

    @Autowired
    RunwayService runwayService;

    @RequestMapping(value = "/runways/{ident}", method = RequestMethod.GET)
    public List<RunwayCommand> index(@PathVariable String ident,
                                     @RequestParam int page,
                                     @RequestParam int limit) {
        return runwayService.getAllByAirport_Ident(ident, page, limit);
    }

    @RequestMapping(value = "/runways/count/{ident}", method = RequestMethod.GET)
    public long index(@PathVariable String ident) {
        return runwayService.countAllByAirport_Ident(ident);
    }

}
