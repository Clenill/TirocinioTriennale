package com.tirociniotriennale.sitoeventi.service;

import com.tirociniotriennale.sitoeventi.model.Evento;
import com.tirociniotriennale.sitoeventi.model.Tipologia;
import com.tirociniotriennale.sitoeventi.repository.TipologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirociniotriennale.sitoeventi.repository.EventoRepository;

import java.util.Optional;
//--------------------------------------------La buisness logic non è fatta più nel controller-------------------------
// ------------------------------------------Viene implementata la buisness logic con add ,edit, get e delete-----------

@Service
public class EventoServiceImpl implements EventoService{
    //@Autowired
    private final EventoRepository eventoRepository;

    @Autowired
    private TipologiaRepository tipologiaRepository;

    @Autowired
    public EventoServiceImpl(EventoRepository eventoRepository){

        this.eventoRepository = eventoRepository;
    }

    @Override
    public Evento createEvento(Evento event){

        return eventoRepository.save(event);

    }

    public Iterable<Evento> getEventiPerIdTipologia(int idtipo){

        //cerco l'ultimo numero di idtipologia
        long numtutteletipologie = tipologiaRepository.count();
        Iterable<Evento> tipologiaricerca = null;

        //prelevo i tipi di eventi in base alla tipologia scelta
        if(idtipo > 0 && idtipo <= numtutteletipologie){
            tipologiaricerca = eventoRepository.findByTipologiaIdtipologia(idtipo);
        }

        return tipologiaricerca;

    }

    @Override // COntiente l'implementazione di find by id ho  modificato anche application.properties su none al posto di altro
    public Optional<Evento> findById(int id){// inettato con Override

        return  eventoRepository.findById(id);
    }

    @Override
    public Iterable<Evento> getEventi() {
    // Sempre inizializzata in EventoService.java
        return eventoRepository.findAll();

    }



}
