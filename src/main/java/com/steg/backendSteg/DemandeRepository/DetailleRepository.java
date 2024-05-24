package com.steg.backendSteg.DemandeRepository;
import com.steg.backendSteg.steg.Detaille;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DetailleRepository extends MongoRepository<Detaille, String> {
}

