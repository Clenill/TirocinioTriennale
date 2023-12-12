package com.tirociniotriennale.sitoeventi.repository;

import com.tirociniotriennale.sitoeventi.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {


}
