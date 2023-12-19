package com.tirociniotriennale.sitoeventi.repository;

import com.tirociniotriennale.sitoeventi.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//Implementa le operazioni CRUD base, le restanti operazioni vengono gestiti nel service
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    Iterable<Evento> findByUser(String user);// Query tramite nome del metodo non serve esplicare in Service!
    // spring tipizza il nome del metodo e fa una ricerca in base al parametro che gli passo! TOP!
    Iterable<Evento> findByIdtipologia(int idtipologia);// Stesso; mi serve per il for-each loop

    Iterable<Evento> findByNomeevento(String nomeevento); // Tasto cerca, da modificare e
    // in modo che anche i risultati che combaciano parzialmente possono essere restituiti
    // però andrà in service.

}
