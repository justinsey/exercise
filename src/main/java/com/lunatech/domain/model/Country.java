package com.lunatech.domain.model;

import com.lunatech.domain.commands.CountryCommand;
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
public class Country {
    @Id
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    public Country() {
    }

    public Country(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Country(CountryCommand country) {
        if (country != null) {
            this.id = country.getId();
            this.name = country.getName();
            this.code = country.getCode();
        }
    }
}