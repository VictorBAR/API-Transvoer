package com.victor.br.apiRestful.controller;

import com.victor.br.apiRestful.model.Empresa;
import com.victor.br.apiRestful.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpresaController {

//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping("/empresa")
    public List<Empresa> listarEmpresas(){
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas;
    }

    @GetMapping("/empresa/{id}")
    public Empresa buscarEmpresaID(@PathVariable long id){
        Empresa empresa = empresaRepository.findById(id).get();
        return empresa;
    }

    @GetMapping("/empresa/find/{username}")
    public Empresa buscarEmpresaPorUsername(@PathVariable String username){
        Empresa empresa = empresaRepository.findByUsername(username);
        return empresa;
    }

    @PostMapping("/empresa")
    public Empresa inserirEmpresa(@RequestBody Empresa empresa){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        empresa.setSenha(bCryptPasswordEncoder.encode(empresa.getSenha()));
        return empresaRepository.save(empresa);
    }

    @DeleteMapping("/empresa")
   // @PreAuthorize("hasAuthority('USER')") //so autoriza para quem tem a role admin
    public void deletarEmpresa(@RequestBody Empresa empresa){
        empresaRepository.delete(empresa);
    }

    @PutMapping("/empresa")
    public void atualizarDados(@RequestBody Empresa empresa){ //não é obrigatorio fazer o return
        empresaRepository.save(empresa);
    }

}
