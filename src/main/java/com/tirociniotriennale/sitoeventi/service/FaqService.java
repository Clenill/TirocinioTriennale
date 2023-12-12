package com.tirociniotriennale.sitoeventi.service;


import com.tirociniotriennale.sitoeventi.model.Faq;

import java.util.Optional;

public interface FaqService {

    Faq createFaq(Faq faq);

    Optional<Faq> getFaqById(Long id);

    Iterable<Faq> getFaqs();

}
