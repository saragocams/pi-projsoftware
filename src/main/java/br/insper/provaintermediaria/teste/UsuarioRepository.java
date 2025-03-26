package br.insper.provaintermediaria.teste;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    // Método para buscar um usuário por email
    Optional<Usuario> findByEmail(String email);
}
