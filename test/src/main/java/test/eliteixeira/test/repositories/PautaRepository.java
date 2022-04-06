package test.eliteixeira.test.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import test.eliteixeira.test.models.Pauta;

public interface PautaRepository extends MongoRepository<Pauta, String> {
	
	@Query("{'status': 1}")
	public List<Pauta> getActivePauta();
	
}
