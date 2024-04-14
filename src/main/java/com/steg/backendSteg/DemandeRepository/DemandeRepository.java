package com.steg.backendSteg.DemandeRepository; // Correction du package
import com.steg.backendSteg.steg.Demande;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemandeRepository extends MongoRepository<Demande, String> {
}
