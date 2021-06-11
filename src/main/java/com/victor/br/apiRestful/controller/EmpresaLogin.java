package com.victor.br.apiRestful.controller;

import com.victor.br.apiRestful.model.Empresa;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpresaLogin {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmpresaLogin(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public void login(@RequestBody Empresa empresa){
        empresa.setSenha(bCryptPasswordEncoder.encode(empresa.getSenha()));
    }
}
