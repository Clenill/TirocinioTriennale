package com.tirociniotriennale.sitoeventi.repository;

import com.tirociniotriennale.sitoeventi.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tirociniotriennale.sitoeventi.model.Evento;

import java.util.List;
import java.util.Optional;

@Repository
//Implementa le operazioni CRUD base, le restanti operazioni vengono gestiti nel service
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    // Query tramite nome del metodo non serve esplicare in Service!
    // spring tipizza il nome del metodo e fa una ricerca in base al parametro che gli passo! TOP!
//Derived Query utilizzate da Spring Data JPA
    Optional<Evento> findById(int id);
    //elenco degli eventi associati all'user
    //Iterable<Evento> findAllByUser(String user);
    //Serve solo verificare che ci siano eventi associati all'user
    //quindi se ne trovo uno va benissimo, qualsiasi sia
    //Optional<Evento> findFirstByUser(String user);

    Iterable<Evento> findByNomeevento(String nomeevento); // Tasto cerca, da modificare e
    // in modo che anche i risultati che combaciano parzialmente possono essere restituiti
    // però andrà in service.


    Optional<Evento> findFirstByUtenteevento(Utente utente);
    Iterable<Evento> findAllByUtenteevento(Utente utente);
}
