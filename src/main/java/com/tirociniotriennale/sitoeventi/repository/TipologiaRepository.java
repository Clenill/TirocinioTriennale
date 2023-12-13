package com.tirociniotriennale.sitoeventi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tirociniotriennale.sitoeventi.model.Tipologia;

@Repository
public interface TipologiaRepository extends JpaRepository<Tipologia, Integer> {

}
