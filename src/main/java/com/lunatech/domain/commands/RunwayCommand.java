package com.lunatech.domain.commands;

import com.lunatech.domain.model.Runway;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Justin Seyvecou
 */
@Getter
@Setter
public class RunwayCommand {
    private long id;
    private long airport_ref;
    private String airport_ident;
    private long length_ft;
    private long width_ft;
    private String surface;
    private long lighted;
    private long closed;
    private String le_ident;

    public RunwayCommand() {
    }

    public RunwayCommand(Runway runway) {
        if(runway != null) {
            this.id = runway.getId();
            this.airport_ref = runway.getAirport_ref();
            this.airport_ident = runway.getAirport_ident();
            this.length_ft = runway.getLength_ft();
            this.width_ft = runway.getWidth_ft();
            this.surface = runway.getSurface();
            this.lighted = runway.getLighted();
            this.le_ident = runway.getLe_ident();
        }
    }

}
