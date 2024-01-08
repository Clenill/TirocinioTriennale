package com.tirociniotriennale.sitoeventi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tirociniotriennale.sitoeventi.model.Faq;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> {


}
