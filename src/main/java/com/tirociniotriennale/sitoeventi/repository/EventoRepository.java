package com.tirociniotriennale.sitoeventi.repository;

import com.tirociniotriennale.sitoeventi.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface EventoRepository extends CrudRepository<Evento, Long> {
public interface EventoRepository extends JpaRepository<Evento, Long>{
    Iterable<Evento> findAllByUser(String user);
    Iterable<Evento> findAllByIdtipologia(long idtipologia);

}
