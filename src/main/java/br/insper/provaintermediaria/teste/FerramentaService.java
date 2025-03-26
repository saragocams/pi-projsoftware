package br.insper.provaintermediaria.teste;
import br.insper.provaintermediaria.teste.Usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FerramentaService {

    @Autowired
    private FerramentaRepository ferramentaRepository;

    private static final String USUARIO_API_URL = "http://56.124.127.89:8080/api/usuario/";

    public ResponseEntity<?> adicionarFerramenta(Ferramenta ferramenta, String email) {
        Usuario usuario = validarUsuario(email);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
        if (!"ADMIN".equals(usuario.getPapel())) {
            return ResponseEntity.status(403).body("Acesso negado");
        }
        ferramenta.setNomeUsuario(usuario.getNome());
        ferramenta.setEmailUsuario(usuario.getEmail());
        ferramentaRepository.save(ferramenta);
        return ResponseEntity.ok(ferramenta);
    }

    public ResponseEntity<?> removerFerramenta(String id, String email) {
        Usuario usuario = validarUsuario(email);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
        if (!"ADMIN".equals(usuario.getPapel())) {
            return ResponseEntity.status(403).body("Acesso negado");
        }
        Optional<Ferramenta> ferramenta = ferramentaRepository.findById(id);
        if (ferramenta.isPresent()) {
            ferramentaRepository.deleteById(id);
            return ResponseEntity.ok("Ferramenta removida com sucesso");
        } else {
            return ResponseEntity.status(404).body("Ferramenta não encontrada");
        }
    }

    public List<Ferramenta> listarFerramentas() {
        return ferramentaRepository.findAll();
    }

    private Usuario validarUsuario(String email) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(USUARIO_API_URL + email, Usuario.class);
        } catch (Exception e) {
            return null;
        }
    }
}
