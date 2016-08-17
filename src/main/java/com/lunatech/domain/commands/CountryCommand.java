package com.lunatech.domain.commands;

import com.lunatech.domain.model.Country;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Justin Seyvecou
 */
@Getter
@Setter
public class CountryCommand {
    private Long id;
    private String code;
    private String name;

    public CountryCommand() {
    }

    public CountryCommand(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public CountryCommand(Country country) {
        if (country != null) {
            this.id = country.getId();
            this.name = country.getName();
            this.code = country.getCode();
        }
    }
}
