package com.mballem.demo_spring_repo_jpa.controller;

import com.mballem.demo_spring_repo_jpa.entity.Categoria;
import com.mballem.demo_spring_repo_jpa.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @PostMapping
    public List<Categoria> salvar(@RequestBody List<Categoria> categorias){
        return this.service.save(categorias);
    }

    @GetMapping("titulo/{titulo}")
    public Categoria getByTitulo(@PathVariable String titulo){
        return this.service.findByTitulo(titulo);
    }

    @GetMapping("titulo/inicio/{titulo}")
    public List<Categoria> getByInicioTitulo(@PathVariable String titulo){
        return this.service.findByInicioTitulo(titulo);
    }

    @GetMapping("titulos")
    public List<Categoria> getByTitulos(@RequestParam(name = "t") List<String> titulos){
        return this.service.findByTitulos(titulos);
    }

    @GetMapping()
    public List<Categoria> getCategoriasOrdenadasAsc(){
        return this.service.findAllOrderByTituloAsc();
    }
}
