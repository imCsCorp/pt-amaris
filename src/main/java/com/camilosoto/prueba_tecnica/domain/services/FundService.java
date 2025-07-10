package com.camilosoto.prueba_tecnica.domain.services;

import com.camilosoto.prueba_tecnica.persistence.FundRepository;
import com.camilosoto.prueba_tecnica.persistence.models.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundService {
    @Autowired
    private FundRepository fundRepository;

    public Fund findById(int id) {
        return fundRepository.findById(id);
    }

    public List<Fund> findAll() {
        return fundRepository.findAll();
    }
}
