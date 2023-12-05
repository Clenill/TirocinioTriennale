package com.tirociniotriennale.sitoeventi.service;

import java.util.Optional;

import com.tirociniotriennale.sitoeventi.model.Evento;

// -------------------------------------------------------Interfaccia del service------------------------------

public interface EventoService {

    Evento createEvento(Evento event); // CREA  EVENTO--------------------------------

    Optional<Evento> getEventoById(long eventoId); // GET EVENTO----------------------
//ittipologia è un numero a cui corrisponde un tipo di evento
    Iterable<Evento> getEventiByidtipologia(long idtipologia);
// il nome dell'organizzazione è user
    Iterable<Evento> getEventiByuser(String user);

    Iterable<Evento> getEventi();

    // void updateEvento (Long eventoId, Evento event);

    // void deleteEventoById(Long eventoId);

    // void deleteEvento();

}
