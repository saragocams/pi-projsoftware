package br.insper.provaintermediaria.teste;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FerramentaServiceTests {

    @InjectMocks
    private FerramentaService ferramentaService;

    @Mock
    private FerramentaRepository ferramentaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks antes de cada teste
    }

//    @Test
//    void testAdicionarFerramenta() {
//        Ferramenta ferramenta = new Ferramenta();
//        ferramenta.setNome("Ferramenta1");
//        ferramenta.setDescricao("Descrição da Ferramenta");
//
//        // Simula a adição da ferramenta no repositório
//        when(ferramentaRepository.save(any(Ferramenta.class))).thenReturn(ferramenta);
//
//        // Chama o método do serviço para adicionar a ferramenta
//        ResponseEntity<?> resposta = ferramentaService.adicionarFerramenta(ferramenta, "usuario@teste.com");
//
//        // Acessa o objeto Ferramenta do ResponseEntity
//        Ferramenta resultado = (Ferramenta) resposta.getBody();
//
//        // Verifica se o método save foi chamado corretamente
//        verify(ferramentaRepository, times(1)).save(ferramenta);
//
//        // Verifica o resultado
//        assertEquals("Ferramenta1", resultado.getNome());
//        assertEquals("Descrição da Ferramenta", resultado.getDescricao());
//    }

//    @Test
//    void testRemoverFerramenta() {
//        // Prepara os dados
//        String idFerramenta = "123";
//        Ferramenta ferramenta = new Ferramenta();
//        ferramenta.setId(idFerramenta);
//        ferramenta.setNome("Ferramenta1");
//
//        // Simula a busca da ferramenta no repositório
//        when(ferramentaRepository.findById(idFerramenta)).thenReturn(Optional.of(ferramenta));
//
//        // Chama o método do serviço para remover a ferramenta
//        ResponseEntity<?> resposta = ferramentaService.removerFerramenta(idFerramenta, "usuario@teste.com");
//
//        // Verifica se o repositório foi chamado para remover a ferramenta
//        verify(ferramentaRepository, times(1)).delete(ferramenta);
//
//        // Verifica o resultado
//        assertEquals("Ferramenta removida com sucesso", resposta.getBody());
//    }

//    @Test
//    void testRemoverFerramentaNaoExistente() {
//        // Prepara os dados
//        String idFerramenta = "123";
//
//        // Simula que a ferramenta não foi encontrada no repositório
//        when(ferramentaRepository.findById(idFerramenta)).thenReturn(Optional.empty());
//
//        // Chama o método do serviço para remover a ferramenta
//        ResponseEntity<?> resposta = ferramentaService.removerFerramenta(idFerramenta, "usuario@teste.com");
//
//        // Verifica se o repositório não foi chamado para remover a ferramenta
//        verify(ferramentaRepository, never()).delete(any(Ferramenta.class));
//
//        // Verifica que o resultado foi uma resposta com erro
//        assertEquals("Ferramenta não encontrada", resposta.getBody());
//    }

    @Test
    void testListarFerramentas() {
        // Prepara as ferramentas
        Ferramenta ferramenta1 = new Ferramenta();
        ferramenta1.setNome("Ferramenta1");

        Ferramenta ferramenta2 = new Ferramenta();
        ferramenta2.setNome("Ferramenta2");

        List<Ferramenta> ferramentas = Arrays.asList(ferramenta1, ferramenta2);

        // Simula a busca das ferramentas no repositório
        when(ferramentaRepository.findAll()).thenReturn(ferramentas);

        // Chama o método do serviço para listar as ferramentas
        List<Ferramenta> resultado = ferramentaService.listarFerramentas();

        // Verifica se o repositório foi chamado para buscar todas as ferramentas
        verify(ferramentaRepository, times(1)).findAll();

        // Verifica se as ferramentas retornadas são as esperadas
        assertEquals(2, resultado.size());
        assertEquals("Ferramenta1", resultado.get(0).getNome());
        assertEquals("Ferramenta2", resultado.get(1).getNome());
    }
}
