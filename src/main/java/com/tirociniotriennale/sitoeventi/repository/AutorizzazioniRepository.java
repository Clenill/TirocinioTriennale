package com.tirociniotriennale.sitoeventi.repository;

import com.tirociniotriennale.sitoeventi.model.Autorizzazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorizzazioniRepository extends JpaRepository<Autorizzazioni, Integer> {
    Optional<Autorizzazioni> getByRuolo(String ruolo);

    Iterable<Autorizzazioni> getAllByRuolo(String ruolo);


}
