package com.steg.backendSteg.DemandeRepository;

import com.steg.backendSteg.steg.Authmodel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<Authmodel, String> {
}
