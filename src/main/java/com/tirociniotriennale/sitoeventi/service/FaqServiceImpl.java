package com.tirociniotriennale.sitoeventi.service;


import com.tirociniotriennale.sitoeventi.model.Faq;
import com.tirociniotriennale.sitoeventi.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FaqServiceImpl implements FaqService{

    private FaqRepository faqRepository;

    @Autowired
    public FaqServiceImpl(FaqRepository faqRepository){

        this.faqRepository = faqRepository;

    }

    @Override
    public Faq createFaq(Faq faq){

        return faqRepository.save(faq);

    }

    @Override
    public Optional<Faq> findById(int id){

        return faqRepository.findById(id);
    }

    @Override
    public Iterable<Faq> getFaqs() {
        // Sempre inizializzata in FaqService.java
        return faqRepository.findAll();

    }

}
