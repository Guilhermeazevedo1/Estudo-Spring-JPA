package com.mballem.demo_spring_repo_jpa.service;

import com.mballem.demo_spring_repo_jpa.entity.Endereco;
import com.mballem.demo_spring_repo_jpa.repository.EnderecoRepository;
import com.mballem.demo_spring_repo_jpa.specification.EnderecoSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Transactional
    public Endereco save(Endereco endereco){
        return this.repository.save(endereco);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUfAndCidade(String uf, String cidade){
        Specification<Endereco> spec = Specification.where(
                EnderecoSpecifications.likeUf(uf).and(EnderecoSpecifications.likeCidade(cidade))
        );
        return this.repository.findAll(spec);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUfAndLogradouro(String uf, String logradouro){
        Specification<Endereco> spec = Specification.where(
                EnderecoSpecifications.likeUf(uf).and(EnderecoSpecifications.likeLogradouro(logradouro))
        );
        return this.repository.findAll(spec);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUfAndCidades(String uf, List<String> cidades){
        Specification<Endereco> spec = Specification.where(
                EnderecoSpecifications.likeUf(uf).and(EnderecoSpecifications.inCidades(cidades))
        );
        return this.repository.findAll(spec);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByAutorNomeOrSobrenome(String nome, String sobrenome){
        Specification<Endereco> spec = Specification.where(
                EnderecoSpecifications.likeAutorNome(nome).or(EnderecoSpecifications.likeAutorSobrenome(sobrenome))
        );
        return this.repository.findAll(spec);
    }

    @Transactional(readOnly = true)
    public Endereco findByAutorNomeAndSobrenome(String nome, String sobrenome){
        return this.repository.findOne(EnderecoSpecifications.likeAutorNomeAndAutorSobrenome(nome, sobrenome))
                .orElseGet(Endereco::new);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByAutorTotalDePostsAndCidades(Long total, List<String> cidades){
        Specification<Endereco> spec = Specification.where(
                EnderecoSpecifications.inCidades(cidades).and(EnderecoSpecifications.byGreaterThanEqualToPosts(total))
        );
        return this.repository.findAll(spec);
    }

}
