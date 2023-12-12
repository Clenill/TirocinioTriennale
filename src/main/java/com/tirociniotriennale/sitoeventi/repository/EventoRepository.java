package com.tirociniotriennale.sitoeventi.repository;

import com.tirociniotriennale.sitoeventi.model.Evento;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//public interface EventoRepository extends JpaRepository<Evento, Integer> {
public interface EventoRepository extends CrudRepository<Evento, Integer> {
    Iterable<Evento> findAllByUser(String user);
    Iterable<Evento> findAllByIdtipologia(int idtipologia);


}
