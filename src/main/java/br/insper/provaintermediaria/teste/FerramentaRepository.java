package br.insper.provaintermediaria.teste;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FerramentaRepository extends MongoRepository<Ferramenta, String> {
}
