package Mottu.com.GEF.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import Mottu.com.GEF.domain.model.Moto;

public interface MotoRepository extends JpaRepository<Moto, UUID> {
    
    Optional<Moto> findByPlaca(String placa);
    
}
