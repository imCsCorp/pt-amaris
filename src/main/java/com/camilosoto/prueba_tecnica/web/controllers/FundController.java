package com.camilosoto.prueba_tecnica.web.controllers;

import com.camilosoto.prueba_tecnica.domain.services.FundService;
import com.camilosoto.prueba_tecnica.domain.services.TransactionService;
import com.camilosoto.prueba_tecnica.domain.services.UserService;
import com.camilosoto.prueba_tecnica.persistence.models.Fund;
import com.camilosoto.prueba_tecnica.persistence.models.Transaction;
import com.camilosoto.prueba_tecnica.persistence.models.User;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/funds")
@CrossOrigin
public class FundController {
    private FundService fundService;
    private UserService userService;
    private TransactionService transactionService;


    @GetMapping
    public ResponseEntity<List<Fund>> getAllFunds() {
        return ResponseEntity.ok(fundService.findAll());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody FundActionRequest request) {
        Optional<User> optionalUser = userService.findById(request.getUserId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = optionalUser.get();
        Fund fund = fundService.findById(request.getFundId());

        if (user.getBalance() < fund.getMinimumAmount()) {
            return ResponseEntity.badRequest().body("No tiene saldo disponible para vincularse al fondo " + fund.getName());
        }

        userService.updateBalance(user.getId(), -fund.getMinimumAmount());

        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID().toString());
        transaction.setUserId(user.getId());
        transaction.setFundId(fund.getId());
        transaction.setAmount(fund.getMinimumAmount());
        transaction.setType("SUBSCRIBE");
        transaction.setDate(Instant.now().toString());

        transactionService.save(transaction);

        return ResponseEntity.ok("Suscripción realizada con éxito");
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(@RequestBody FundActionRequest request) {
        Optional<User> optionalUser = userService.findById(request.getUserId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = optionalUser.get();
        Fund fund = fundService.findById(request.getFundId());

        // Reembolsar el valor de vinculación
        userService.updateBalance(user.getId(), fund.getMinimumAmount());

        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID().toString());
        transaction.setUserId(user.getId());
        transaction.setFundId(fund.getId());
        transaction.setAmount(fund.getMinimumAmount());
        transaction.setType("CANCEL");
        transaction.setDate(Instant.now().toString());

        transactionService.save(transaction);

        return ResponseEntity.ok("Cancelación realizada con éxito");
    }

    @Data
    public static class FundActionRequest {
        private String userId;
        private int fundId;
    }
}
