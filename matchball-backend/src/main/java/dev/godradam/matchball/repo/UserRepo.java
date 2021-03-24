package dev.godradam.matchball.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.godradam.matchball.model.ApplicationUser;

@Repository
public interface UserRepo extends MongoRepository<ApplicationUser, String> {
    public Optional<ApplicationUser> findByUsername(String username);
}
