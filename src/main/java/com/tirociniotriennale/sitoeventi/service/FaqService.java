package com.tirociniotriennale.sitoeventi.service;
import com.tirociniotriennale.sitoeventi.model.Faq;

import java.util.Optional;

public interface FaqService {

    Faq createFaq(Faq faq);

    Optional<Faq> findById(int id);

    Iterable<Faq> getFaqs();

}
