package Mottu.com.GEF.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import Mottu.com.GEF.model.User;

public interface AuthRepository extends JpaRepository<User, UUID> {    
}
