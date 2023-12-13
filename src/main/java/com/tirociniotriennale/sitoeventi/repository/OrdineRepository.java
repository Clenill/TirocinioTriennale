package com.tirociniotriennale.sitoeventi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tirociniotriennale.sitoeventi.model.Ordine;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {
}
