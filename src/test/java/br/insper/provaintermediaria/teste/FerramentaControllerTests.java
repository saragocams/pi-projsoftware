package br.insper.provaintermediaria.teste;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(FerramentaController.class)
@ExtendWith(SpringExtension.class)
public class FerramentaControllerTests {

    @MockBean  // Mock do FerramentaController
    private FerramentaController ferramentaController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        // Inicialização do MockMvc com o FerramentaController
        mockMvc = MockMvcBuilders
                .standaloneSetup(ferramentaController) // Passando o mock do FerramentaController
                .build();
    }

    @Test
    void testAdicionarFerramenta() throws Exception {
        Ferramenta ferramenta = new Ferramenta();
        ferramenta.setNome("Ferramenta1");
        ferramenta.setDescricao("Descrição da Ferramenta");

        // Simula o comportamento do FerramentaController
        when(ferramentaController.adicionarFerramenta(ferramenta, "usuario@teste.com"))
                .thenReturn(ResponseEntity.ok().build());

        // Realiza a requisição POST
        mockMvc.perform(MockMvcRequestBuilders.post("/api/ferramenta")
                        .header("email", "usuario@teste.com") // Adicionando o cabeçalho de email
                        .contentType(MediaType.APPLICATION_JSON) // Tipo de conteúdo da requisição
                        .content("{\"nome\":\"Ferramenta1\", \"descricao\":\"Descrição da Ferramenta\"}")) // Corpo da requisição
                .andExpect(MockMvcResultMatchers.status().isOk()); // Verifica se o status da resposta é 200 (OK)
    }

    @Test
    void testRemoverFerramenta() throws Exception {
        // Simula o comportamento do FerramentaController
        doNothing().when(ferramentaController).removerFerramenta("123", "usuario@teste.com");  // Simula a remoção sem erro

        // Realiza a requisição DELETE
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/ferramenta/123")
                        .header("email", "usuario@teste.com")) // Adicionando o cabeçalho de email
                .andExpect(MockMvcResultMatchers.status().isOk()); // Verifica se o status da resposta é 200 (OK)
    }

    @Test
    void testListarFerramentas() throws Exception {
        // Simula o comportamento do FerramentaController
        when(ferramentaController.listarFerramentas()).thenReturn(List.of(new Ferramenta(), new Ferramenta()));

        // Realiza a requisição GET
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ferramenta"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Verifica se o status da resposta é 200 (OK)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)); // Verifica se o tipo de conteúdo é JSON
    }
}
