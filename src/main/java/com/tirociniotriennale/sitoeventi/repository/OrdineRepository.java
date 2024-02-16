package com.tirociniotriennale.sitoeventi.repository;

import com.tirociniotriennale.sitoeventi.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tirociniotriennale.sitoeventi.model.Ordine;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {
    Iterable<Ordine> findByUtente(Utente utente);
    Iterable<Ordine> findByUtenteAndPagatoFalse(Utente utente);
    Iterable<Ordine> findByUtenteAndPagatoTrue(Utente utente);
}


