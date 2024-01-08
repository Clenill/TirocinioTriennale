package com.tirociniotriennale.sitoeventi.service;

import com.tirociniotriennale.sitoeventi.model.Evento;

import java.util.Optional;

// -------------------------------------------------------Interfaccia del service------------------------------

public interface EventoService {

    Evento createEvento(Evento event); // CREA  EVENTO--------------------------------

    Optional<Evento> findById(int id); // GET EVENTO---------------------- Tipo Long?


    Iterable<Evento> getEventi();


}
