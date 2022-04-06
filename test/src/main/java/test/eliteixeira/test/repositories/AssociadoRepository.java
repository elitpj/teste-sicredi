package test.eliteixeira.test.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import test.eliteixeira.test.models.Associado;

public interface AssociadoRepository extends MongoRepository<Associado, String> {

}
