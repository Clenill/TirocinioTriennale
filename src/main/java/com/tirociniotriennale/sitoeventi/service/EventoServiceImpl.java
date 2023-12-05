package com.tirociniotriennale.sitoeventi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirociniotriennale.sitoeventi.model.Evento;
import com.tirociniotriennale.sitoeventi.repository.EventoRepository;

import java.util.Optional;
//--------------------------------------------La buisness logic non è fatta più nel controller-------------------------
// ------------------------------------------Viene implementata la buisness logic con add ,edit, get e delete-----------

@Service
public class EventoServiceImpl implements EventoService{

    private EventoRepository eventoRepository;

    @Autowired
    public EventoServiceImpl(EventoRepository eventoRepository){

        this.eventoRepository = eventoRepository;
    }

    @Override
    public Evento createEvento(Evento event){

        return eventoRepository.save(event);

    }

    @Override
    public Optional<Evento> getEventoById(long eventoId){
    // inizializzata in EventoService.java
        return  eventoRepository.findById(eventoId);
    }
    @Override
    public Iterable<Evento> getEventiByidtipologia(long idtipologia){
    // sempre inizializzata in EventoService.java
        return eventoRepository.findAllByIdtipologia(idtipologia);

    }
    @Override
    public Iterable<Evento> getEventiByuser(String user){
    // inizializzata in EventoService.java
        return eventoRepository.findAllByUser(user);

    }

    @Override
    public Iterable<Evento> getEventi() {
    // Sempre inizializzata in EventoService.java
        return eventoRepository.findAll();

    }




}
