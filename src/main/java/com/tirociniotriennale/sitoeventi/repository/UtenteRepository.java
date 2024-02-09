package com.tirociniotriennale.sitoeventi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tirociniotriennale.sitoeventi.model.Utente;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {
    Optional<Utente> findByUser(String user);

}






