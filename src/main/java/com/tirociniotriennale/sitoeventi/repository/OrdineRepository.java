package com.tirociniotriennale.sitoeventi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tirociniotriennale.sitoeventi.model.Ordine;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {
}
