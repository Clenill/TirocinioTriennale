package com.tirociniotriennale.sitoeventi.service;

import java.util.Optional;

import com.tirociniotriennale.sitoeventi.model.Evento;

// -------------------------------------------------------Interfaccia del service------------------------------

public interface EventoService {

    Evento createEvento(Evento event); // CREA  EVENTO--------------------------------

    Optional<Evento> findById(int id); // GET EVENTO---------------------- Tipo Long?


    Iterable<Evento> getEventi();

    // void updateEvento (Long eventoId, Evento event);

    // void deleteEventoById(Long eventoId);

    // void deleteEvento();

}
