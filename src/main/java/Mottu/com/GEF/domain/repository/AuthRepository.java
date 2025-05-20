package Mottu.com.GEF.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import Mottu.com.GEF.domain.model.User;

public interface AuthRepository extends JpaRepository<User, UUID> {  
    Optional<User> findByUsername(String username);  
}
