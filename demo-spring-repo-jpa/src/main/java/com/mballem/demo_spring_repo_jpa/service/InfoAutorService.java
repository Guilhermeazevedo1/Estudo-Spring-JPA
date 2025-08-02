package com.mballem.demo_spring_repo_jpa.service;

import com.mballem.demo_spring_repo_jpa.entity.InfoAutor;
import com.mballem.demo_spring_repo_jpa.repository.InfoAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InfoAutorService {
    @Autowired
    private InfoAutorRepository repository;

    public InfoAutor findById(Long id){
        InfoAutor info = new InfoAutor();
        info.setId(id);
        return this.repository.findOne(Example.of(info)).orElseGet(InfoAutor::new);
    }

    //Consulta para só uma coluna
    public List<InfoAutor> findAllContainsCargo(String cargo){
        InfoAutor info = new InfoAutor();
        info.setCargo(cargo);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("cargo", ExampleMatcher.GenericPropertyMatchers.contains());

        return this.repository.findAll(Example.of(info, matcher));
    }

    //Consulta para mais de uma coluna (AND)
    public List<InfoAutor> findAllCargoAndEmpresa(String cargo, String empresa){
        InfoAutor info = new InfoAutor();
        info.setCargo(cargo);
        info.setBio(empresa);

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("cargo", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("bio", ExampleMatcher.GenericPropertyMatchers.contains());

        return this.repository.findAll(Example.of(info, matcher));
    }

    public InfoAutor findFromBio(String bio){
        InfoAutor info = new InfoAutor();
        info.setBio(bio);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("bio", ExampleMatcher.GenericPropertyMatchers.contains());

        return this.repository.findBy(
                Example.of(info, matcher),
                query -> query.sortBy(Sort.by("cargo").descending()).first()
        ).orElseGet(InfoAutor::new);
    }
}
