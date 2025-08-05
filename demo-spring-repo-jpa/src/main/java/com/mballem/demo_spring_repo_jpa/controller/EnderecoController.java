package com.mballem.demo_spring_repo_jpa.controller;

import com.mballem.demo_spring_repo_jpa.entity.Endereco;
import com.mballem.demo_spring_repo_jpa.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public Endereco salvar(@RequestBody Endereco endereco){
        return this.enderecoService.save(endereco);
    }

    @GetMapping("/uf/{uf}/cidade/{cidade}")
    public List<Endereco> getByUfAndCidade(@PathVariable String uf, @PathVariable String cidade){
        return this.enderecoService.findByUfAndCidade(uf, cidade);
    }

    @GetMapping("/uf/{uf}/logradouro/{logradouro}")
    public List<Endereco> getByUfAndLogradouro(@PathVariable String uf, @PathVariable String logradouro){
        return this.enderecoService.findByUfAndLogradouro(uf, logradouro);
    }

    @GetMapping("/uf/{uf}/cidades/{cidades}")
    public List<Endereco> getByUfAndCidades(@PathVariable String uf, @RequestParam(name = "nomes") List<String> cidades){
        return this.enderecoService.findByUfAndCidades(uf, cidades);
    }

    @GetMapping("autores/nome/{nome}/sobrenome/{sobrenome}")
    public List<Endereco> getByAutoresNomeOrSobrenome(@PathVariable String nome, @PathVariable String sobrenome){
        return this.enderecoService.findByAutorNomeOrSobrenome(nome, sobrenome);
    }

    @GetMapping("autor/nome/{nome}/sobrenome/{sobrenome}")
    public Endereco getByAutorNomeAndSobrenome(@PathVariable String nome, @PathVariable String sobrenome){
        return this.enderecoService.findByAutorNomeAndSobrenome(nome, sobrenome);
    }

    @GetMapping("autores/total-posts")
    public List<Endereco> getByAutoresTotalDePostsPorCidades(@RequestParam long total, @RequestParam List<String> cidades){
        return this.enderecoService.findByAutorTotalDePostsAndCidades(total, cidades);
    }
}
