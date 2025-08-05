package com.mballem.demo_spring_repo_jpa.repository;

import com.mballem.demo_spring_repo_jpa.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByTitulo(String titulo);

    List<Categoria> findByTituloStartsWith(String titulo);

    List<Categoria> findByTituloIn(List<String> titulo);

    List<Categoria> findByOrderByTituloAsc();
}
