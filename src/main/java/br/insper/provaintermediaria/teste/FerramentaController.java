package br.insper.provaintermediaria.teste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/ferramenta")
public class FerramentaController {

    @Autowired
    private FerramentaService ferramentaService;

    @PostMapping
    public ResponseEntity<?> adicionarFerramenta(@RequestBody Ferramenta ferramenta, @RequestHeader(name = "email") String email) {
        return ferramentaService.adicionarFerramenta(ferramenta, email);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerFerramenta(@PathVariable String id, @RequestHeader(name = "email") String email) {
        return ferramentaService.removerFerramenta(id, email);
    }

    @GetMapping
    public List<Ferramenta> listarFerramentas() {
        return ferramentaService.listarFerramentas();
    }
}
