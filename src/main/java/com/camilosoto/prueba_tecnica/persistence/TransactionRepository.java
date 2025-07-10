package com.camilosoto.prueba_tecnica.persistence;

import com.camilosoto.prueba_tecnica.persistence.models.Transaction;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    private final DynamoDbTable<Transaction> transactionTable;

    public TransactionRepository(DynamoDbTable<Transaction> transactionTable) {
        this.transactionTable = transactionTable;
    }

    public void save(Transaction transaction) {
        transactionTable.putItem(transaction);
    }

    public Optional<Transaction> findById(String id) {
        return Optional.ofNullable(transactionTable.getItem(r -> r.key(k -> k.partitionValue(id))));
    }

    public List<Transaction> findAll() {
        return transactionTable.scan()
                .items()
                .stream()
                .collect(Collectors.toList());
    }
}
