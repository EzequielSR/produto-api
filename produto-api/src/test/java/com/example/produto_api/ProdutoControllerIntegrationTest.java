package com.example.produto_api;

import com.example.produto_api.model.Produto;
import com.example.produto_api.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class ProdutoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // Teste para criar e listar um produto
    @Test
    public void deveCriarEListarProduto() throws Exception {
        Produto novoProduto = new Produto();
        novoProduto.setNome("Produto Teste");
        novoProduto.setPreco(99.99);

        // Converte o produto para JSON
        String produtoJson = objectMapper.writeValueAsString(novoProduto);

        // POST - Criar Produto
        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())  // Verifica se o ID foi gerado
                .andExpect(jsonPath("$.nome").value("Produto Teste"))
                .andExpect(jsonPath("$.preco").value(99.99));

        // GET - Listar Produtos
        mockMvc.perform(get("/api/produtos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))  // Verifica se existe um produto
                .andExpect(jsonPath("$[0].nome").value("Produto Teste"))
                .andExpect(jsonPath("$[0].preco").value(99.99));
    }

    // Teste para buscar um produto por ID
    @Test
    public void deveBuscarProdutoPorId() throws Exception {
        // Primeiro, cria um produto
        Produto produtoSalvo = produtoRepository.save(new Produto("Produto Teste", 100.00));

        // GET - Buscar Produto por ID
        mockMvc.perform(get("/api/produtos/{id}", produtoSalvo.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(produtoSalvo.getId()))
                .andExpect(jsonPath("$.nome").value("Produto Teste"))
                .andExpect(jsonPath("$.preco").value(100.00));
    }

    // Teste para retornar erro 404 quando o produto não for encontrado
    @Test
    public void deveRetornar404QuandoProdutoNaoEncontrado() throws Exception {
        // GET - Tentar buscar um produto inexistente
        mockMvc.perform(get("/api/produtos/{id}", 999L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());  // Espera um 404
    }
}
