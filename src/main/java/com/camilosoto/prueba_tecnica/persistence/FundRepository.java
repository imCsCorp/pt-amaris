package com.camilosoto.prueba_tecnica.persistence;


import com.camilosoto.prueba_tecnica.persistence.models.Fund;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FundRepository {
    private final DynamoDbTable<Fund> fundTable;

    public FundRepository(DynamoDbTable<Fund> fundTable) {
        this.fundTable = fundTable;
    }

    public void save(Fund fund) {
        fundTable.putItem(fund);
    }

    public Fund findById(int id) {
        return fundTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public List<Fund> findAll() {
        return fundTable.scan()
                .items()
                .stream()
                .collect(Collectors.toList());
    }
}
