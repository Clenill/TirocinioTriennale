package com.tirociniotriennale.sitoeventi.service;

import com.tirociniotriennale.sitoeventi.model.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirociniotriennale.sitoeventi.model.Evento;
import com.tirociniotriennale.sitoeventi.repository.EventoRepository;

import java.util.Optional;
//--------------------------------------------La buisness logic non è fatta più nel controller-------------------------
// ------------------------------------------Viene implementata la buisness logic con add ,edit, get e delete-----------

@Service
public class EventoServiceImpl implements EventoService{

    private final EventoRepository eventoRepository;

    @Autowired
    public EventoServiceImpl(EventoRepository eventoRepository){

        this.eventoRepository = eventoRepository;
    }

    @Override
    public Evento createEvento(Evento event){

        return eventoRepository.save(event);

    }

    @Override // COntiente l'implementazione di find by id ho  modificato anche application.properties su none al posto di altro
    public Optional<Evento> findById(int id){// inettato con Override
        return  eventoRepository.findById(id);
    }
    @Override
    public Iterable<Evento> getEventiByidtipologia(int idtipologia){
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
