package br.insper.provaintermediaria.teste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;


import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(FerramentaController.class)
@ExtendWith(SpringExtension.class)
public class FerramentaControllerTests {

    @MockBean  // Usando @MockBean para injetar o mock do serviço no contexto do Spring
    private FerramentaService ferramentaService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        // A injeção do serviço será feita automaticamente pelo Spring devido ao uso de @MockBean
        mockMvc = MockMvcBuilders
                .standaloneSetup(new FerramentaController(ferramentaService)) // Passa o mock do serviço para o controlador
                .build();
    }

    @Test
    void testAdicionarFerramenta() throws Exception {
        Ferramenta ferramenta = new Ferramenta();
        ferramenta.setNome("Ferramenta1");
        ferramenta.setDescricao("Descrição da Ferramenta");

        // Simula o comportamento do serviço
        when(ferramentaService.adicionarFerramenta(ferramenta, "usuario@teste.com"))
                .thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/ferramenta")
                        .header("email", "usuario@teste.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Ferramenta1\", \"descricao\":\"Descrição da Ferramenta\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testRemoverFerramenta() throws Exception {
        // Simula o comportamento do serviço
        when(ferramentaService.removerFerramenta("123", "usuario@teste.com"))
                .thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/ferramenta/123")
                        .header("email", "usuario@teste.com"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testListarFerramentas() throws Exception {
        // Simula o comportamento do serviço
        when(ferramentaService.listarFerramentas()).thenReturn(List.of(new Ferramenta(), new Ferramenta()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/ferramenta"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
