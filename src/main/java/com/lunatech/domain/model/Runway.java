package com.lunatech.domain.model;

import com.lunatech.domain.commands.RunwayCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Justin Seyvecou
 */
@Entity
@Getter
@Setter
public class Runway {

    @Id
    private long id;

    @Column(nullable = false)
    private long airport_ref;

    @Column(nullable = false)
    private String airport_ident;

    @Column
    private long length_ft;

    @Column
    private long width_ft;

    @Column
    private String surface;

    @Column
    private long lighted;

    @Column
    private long closed;

    @Column
    private String le_ident;

    public Runway() {
    }

    public Runway(RunwayCommand runway) {
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
