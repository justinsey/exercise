package com.lunatech.domain.commands;

import com.lunatech.domain.model.Airport;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Justin Seyvecou
 */
@Getter
@Setter
public class AirportCommand {
    private Long id;
    private String ident;
    private String type;
    private String name;
    private double latitude_deg;
    private double longitude_deg;
    private String iso_country;

    public AirportCommand() {
    }

    public AirportCommand(Airport airport) {
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
