package com.lunatech.domain.repository;

import com.lunatech.domain.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Justin Seyvecou
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
