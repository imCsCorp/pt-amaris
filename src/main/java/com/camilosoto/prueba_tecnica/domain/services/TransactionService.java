package com.camilosoto.prueba_tecnica.domain.services;

import com.camilosoto.prueba_tecnica.persistence.TransactionRepository;
import com.camilosoto.prueba_tecnica.persistence.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public Optional<Transaction> findById(String id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
