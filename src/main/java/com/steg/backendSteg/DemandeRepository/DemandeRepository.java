package com.steg.backendSteg.DemandeRepository; // Correction du package
import com.steg.backendSteg.steg.Demande;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.steg.backendSteg.steg.Demande.Status;
import java.util.List;

public interface DemandeRepository extends MongoRepository<Demande, String> {
        List<Demande> findByStatus(Demande.Status status);
    }
