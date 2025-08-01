package com.mballem.tarefas.jpa.service;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.dto.AlunoArmarioDto;
import com.mballem.tarefas.jpa.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository repository;

    /**
     * TESTE 9 - Complete o metodo com a operação solicitada
     */
    @Transactional(readOnly = true)
    public List<Aluno> findByIds(List<Long> ids) {
        return this.repository.findAllById(ids);
    }

    /**
     * TESTE 10 - Complete o metodo com a operação solicitada
     */
    @Transactional(readOnly = true)
    public boolean isExists(Long id) {
        return this.repository.existsById(id);
    }

    /**
     * TESTE 11 - Complete o metodo com a operação solicitada
     */
    @Transactional(readOnly = true)
    public List<Aluno> findByPrimeiroNome(String nome, String order) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), "nome");
        return this.repository.findByPrimeiroNome(nome, sort);
    }

    /**
     * TESTE 12 - Complete o metodo com a operação solicitada
     */
    @Transactional(readOnly = true)
    public List<AlunoArmarioDto> findByMatriculas(List<String> matriculas) {
        return this.repository.findByMatriculas(matriculas);
    }

    /**
     * TESTE 13 - Complete o metodo com a operação solicitada
     */
    @Transactional(readOnly = false)
    public List<Aluno> findAndRemoveAlunosSemArmarios() {
        List<Aluno> alunos = this.repository.findAndRemoveAlunosSemArmarios();
        this.repository.deleteAllInBatch(alunos);
        return alunos;
    }


}
