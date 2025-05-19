package Mottu.com.GEF.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import Mottu.com.GEF.model.Moto;

public interface MotoRepository extends JpaRepository<Moto, UUID> {
    
    Optional<Moto> findByPlaca(String placa);
    
}
