package com.tirociniotriennale.sitoeventi.repository;

import com.tirociniotriennale.sitoeventi.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tirociniotriennale.sitoeventi.model.Evento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
//Implementa le operazioni CRUD base, le restanti operazioni vengono gestiti nel service
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    // Query tramite nome del metodo non serve esplicare in Service!
    // spring tipizza il nome del metodo e fa una ricerca in base al parametro che gli passo!
//Derived Query utilizzate da Spring Data JPA
    Optional<Evento> findById(int id);

    Iterable<Evento> findByNomeevento(String nomeevento);

    Iterable<Evento> findAllByOrderByLocalDateAsc();
    Optional<Evento> findFirstByUtenteevento(Utente utente);
    Iterable<Evento> findAllByUtenteevento(Utente utente);

    Iterable<Evento> findByTipologiaIdtipologia(int idtipologia);

    @Query("SELECT e FROM Evento e WHERE LOWER(e.nomeevento) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Iterable<Evento> searchByNomeeento(@Param("searchTerm") String searchTerm);

}
