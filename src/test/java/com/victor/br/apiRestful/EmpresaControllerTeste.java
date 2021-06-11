package com.victor.br.apiRestful;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.br.apiRestful.model.Empresa;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.client.RestTemplate;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmpresaControllerTeste {

    Empresa empresa = new Empresa("teste@teste.com", "123456", "teste");

    @Autowired
    MockMvc mockMvc;

    //Instância do ObjectMapper para trabalhar com JSON
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void listarEmpresas() throws Exception {
        mockMvc.perform(get("/api/empresa"))
                .andExpect(status().isOk());
    }

    @Test
    public void listarEmpresaPorId() throws Exception {
        mockMvc.perform(get("/api/empresa/50"))
                .andExpect(status().isOk());
    }

    @Test
    public void inserirEmpresa() throws Exception {

        mockMvc.perform(post("/api/empresa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(empresa)))
                .andExpect(status().isOk());
    }


    @Test
    public void AcessarAplicacao() throws Exception {

        JSONObject login = new JSONObject();

        login.put("username", "flavio@gmail.com");
        login.put("senha","123456");

        mockMvc.perform(post("/login")
                .contentType("application/json")
                .content(String.valueOf(login)))
                .andExpect(status().isOk());

    }


    @Test
    @WithMockUser(username = "flavio@gmail.com", password = "123")
    public void deletarEmpresa() throws Exception {
        mockMvc.perform(delete("/api/empresa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(empresa)))
                .andExpect(status().isOk());
    }

}    
