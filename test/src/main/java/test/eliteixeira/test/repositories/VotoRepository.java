package test.eliteixeira.test.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import test.eliteixeira.test.models.Voto;

public interface VotoRepository extends MongoRepository<Voto, String> {

	@Query("{'associado.id': ?0}")
	public List<Voto> getVotoByAssociado(String id);
	
	@Query("{'pauta.id': ?0}")
	public List<Voto> getVotoByPauta(String id);
	
	@Query("{ $and : [ {'associado.id': ?0}, {'pauta.id': ?1} ] }")
	public List<Voto> getVotoByAssociadoAndPauta(String associado, String pauta);
}
