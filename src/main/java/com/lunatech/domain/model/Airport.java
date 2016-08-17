package com.lunatech.domain.model;

import com.lunatech.domain.commands.AirportCommand;
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
public class Airport {
    @Id
    private long id;

    @Column(nullable = false)
    private String ident;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double latitude_deg;

    @Column(nullable = false)
    private double longitude_deg;

    @Column(nullable = false)
    private String iso_country;

    public Airport() {
    }

    public Airport(AirportCommand airport) {
        if (airport != null) {
            this.id = airport.getId();
            this.ident = airport.getIdent();
            this.type = airport.getType();
            this.name = airport.getName();
            this.latitude_deg = airport.getLatitude_deg();
            this.longitude_deg = airport.getLongitude_deg();
            this.iso_country = airport.getIso_country();
        }
    }
}
