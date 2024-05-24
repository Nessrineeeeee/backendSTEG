package com.steg.backendSteg.DemandeRepository;
import com.steg.backendSteg.steg.Agent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AgentRepository extends MongoRepository<Agent, String> {

    List<Agent> findByMatricule(String matricule);


}
