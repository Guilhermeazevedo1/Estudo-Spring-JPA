package com.mballem.demo_spring_repo_jpa.service;

import com.mballem.demo_spring_repo_jpa.entity.Autor;
import com.mballem.demo_spring_repo_jpa.entity.Categoria;
import com.mballem.demo_spring_repo_jpa.entity.Post;
import com.mballem.demo_spring_repo_jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepositoryepository;

    @Autowired
    private AutorService autorService;

    @Autowired
    private CategoriaService categoriaService;

    @Transactional
    public Post save(Post post){
        Autor autor = this.autorService.findById(post.getAutor().getId());
        post.setAutor(autor);

        List<String> titulos = post.getCategorias().stream().map(Categoria::getTitulo).toList();
        List<Categoria> categorias = this.categoriaService.findByTitulos(titulos);
        post.setCategorias(categorias);

        return this.postRepositoryepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByCategoriaAndAutorId(String categoria, Long autorId){
        return this.postRepositoryepository.findByCategoriasTituloAndAutorId(categoria, autorId);
    }

    @Transactional(readOnly = true)
    public List<Post> findByAutor(String autorNome, String autorSobrenome){
        return this.postRepositoryepository.findByAutorNomeOrAutorSobrenome(autorNome, autorSobrenome);
    }

    @Transactional(readOnly = true)
    public List<Post> findByTitulo(String titulo){
        return this.postRepositoryepository.findByTituloContainsOrderByAutorNomeAsc(titulo);
    }

    @Transactional(readOnly = true)
    public List<Post> findByDataPublicacaoMaiorOuIgual(LocalDate data){
        return this.postRepositoryepository.findByDataPublicacaoIsGreaterThanEqual(data);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllBySemDataPublicacao(){
        return this.postRepositoryepository.findByDataPublicacaoIsNull();
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllPagination(Pageable pageable){
        return this.postRepositoryepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllByAno(int ano, int page, int size, String sort, String dir){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(dir), sort);
        return this.postRepositoryepository.findByAno(ano, pageable);
    }

}
