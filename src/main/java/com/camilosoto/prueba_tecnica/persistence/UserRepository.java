package com.camilosoto.prueba_tecnica.persistence;

import com.camilosoto.prueba_tecnica.persistence.models.User;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final DynamoDbTable<User> userTable;

    public UserRepository(DynamoDbTable<User> userTable) {
        this.userTable = userTable;
    }

    public void save(User user) {
        userTable.putItem(user);
    }

    public List<User> findAll() {
        return userTable.scan().items().stream().toList();
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(userTable.getItem(r -> r.key(k -> k.partitionValue(id))));
    }

    public void deleteById(String id) {
        userTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}
