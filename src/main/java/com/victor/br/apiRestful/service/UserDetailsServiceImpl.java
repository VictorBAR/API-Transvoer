package com.victor.br.apiRestful.service;

import com.victor.br.apiRestful.model.Empresa;
import com.victor.br.apiRestful.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empresa empresa = Optional.ofNullable(empresaRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Empresa não encontrada"));
        return new org.springframework.security.core.userdetails.User
                (empresa.getUsername(), empresa.getSenha(), emptyList());
    }
}
