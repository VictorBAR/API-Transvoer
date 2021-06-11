package com.victor.br.apiRestful.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_empresa")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private String senha;

    private String razaoSocial;

    private String cnpj;

    private String rua;

    private String bairro;

    private String municipio;

    private String cep;

    private String complemento;

    private String inscricaoEstadual;

    private String inscricaoMunicipal;

    public Empresa(String username, String senha, String razaoSocial){
        this.username = username;
        this.senha = senha;
        this.razaoSocial = razaoSocial;
    }

}
